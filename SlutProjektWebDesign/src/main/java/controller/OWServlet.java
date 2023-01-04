package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GetTheWeather;
import model.WeatherBean;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "OWServlet", value = "/OWServlet")
public class OWServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
        String cityStr = request.getParameter("city");

        String countryStr = request.getParameter("country");

        WeatherBean wBean = new WeatherBean(cityStr, countryStr);

        GetTheWeather.getWeather(wBean);
        request.setAttribute("wBean", wBean);



            for (Cookie c : request.getCookies()) {
                System.out.println(c.getName());
                if (c.getName().equals("acceptCookie")){

                    String cityString = cityStr.substring(0,1).toUpperCase() + cityStr.substring(1).toLowerCase();
                    String countryString = countryStr.substring(0,1).toUpperCase() + countryStr.substring(1).toLowerCase();


                    c.setValue(c.getValue() + "-" + cityString.replaceAll(" ", "%20") + "/" + countryString.replaceAll(" ", "%20"));
                    c.setPath("/");
                    response.addCookie(c);
                }
            }

            RequestDispatcher rd = request.getRequestDispatcher("views/showWeather.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            WeatherBean wBean = new WeatherBean();
            wBean.setErrorMessage(Arrays.asList(String.valueOf(e).split(":",0)).get(0));
            System.out.println("outprint exCEPTION: " + Arrays.asList(String.valueOf(e).split(":",0)).get(0));
            System.out.println(wBean.getErrorMessage());
            Cookie errorCookie = new Cookie("errorCookie", Arrays.asList(String.valueOf(e).split(":",0)).get(0));
            response.addCookie(errorCookie);
            response.sendRedirect("/SlutProjektWebDesign_war_exploded/");

        }

    }
}
