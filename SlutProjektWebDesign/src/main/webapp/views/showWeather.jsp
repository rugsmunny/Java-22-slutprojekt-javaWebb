<%@ page import="model.WeatherBean" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../stylesheets/style2.css"/>
    <link rel="stylesheet" href="./stylesheets/style2.css">
    <script src="../scripts/scripts.js"></script>
    <script src='/openweathermap.org/themes/openweathermap/assets/vendor/owm/js/d3.min.js'></script>


    <title>Title</title>

    <%
        WeatherBean wBean = (WeatherBean) request.getAttribute("wBean");
        if (wBean == null) {
            response.sendRedirect("/SlutProjektWebDesign_war_exploded/");
        }
        assert wBean != null;%>

    <script>

    (window.myWidgetParam = window.myWidgetParam ? window.myWidgetParam : [])

    window.myWidgetParam.push({
        id: 2, cityid: '<%=wBean.getCityID()%>', appid: 'b904383e0cb531fe313068de52a606de',
        units: 'metric', containerid: 'openweathermap-widget-2',
    });

    (function () {
        const script = document.createElement('script');
        script.async = true;

        script.src = "//openweathermap.org/themes/openweathermap/assets/vendor/owm/js/weather-widget-generator.js";
        const s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(script, s);
    })();</script>


</head>
<body>
<div class="br-cover-img">
    <div class="container">
        <div class="weather-container">
            <div class="left-container">
                <iframe clocktype="html5"
                        style="overflow:hidden;border:0;padding:0;width:240px;height:80px;"
                        src="https://www.clocklink.com/html5embed.php?clock=043&timezone=<%=wBean.getCountryStr()%>_<%=wBean.getCityStr()%>
                        &color=gray&size=240&Title=&Message=&Target=&From=2022,1,1,0,0,0&Color=gray">

                </iframe>
                <p class="main-text shadow">
                    The temperature right now in <%=wBean.getCityStr()%> is <%=wBean.getTemperature()%>°C
                    with <%=wBean.getCloudsStr()%>.
                </p>
                <div class="search-container">
                    <div id="inputform2">

                        <form class="shadow" action="OWServlet" id="OWServlet" method="get">
                            <input type="text" name="city" id="city"
                                   placeholder="City" required autocomplete="off"/><br/>
                            <input type="text" name="country" id="country"
                                   placeholder="Country" required autocomplete="off"/><br/>
                            <button><span onclick="formAction2()">SUBMIT</span></button>
                        </form>
                        <br>
                        <div class="searchHistory">
                            <img class="shadow recent-text" src="https://fontmeme.com/permalink/221222/44ffa25d7abcc6151b0f07cf48d57a7e.png">
                            <br>
                            <div id="scroll-bar-style" class="recent-searches">
                                <%
                                    for (Cookie c : request.getCookies()) {
                                        if (c.getName().equals("acceptCookie")) {
                                            String[] values = c.getValue().split("-");
                                            List<String> stringList = Arrays.stream(values).distinct().toList();
                                            for (String value : stringList) {
                                                if (!value.equals("")) {
                                                    String[] strings = value.split("/");
                                                    out.print("<a class=\"recent-search-text hover-underline-animation shadow\" href=\"OWServlet?city=" + strings[0] + "&country=" + strings[1] +
                                                            "\">" + strings[0].replaceAll("%20", " ") + ", " + strings[1].replaceAll("%20", " ") + "</a><br>");
                                                }
                                            }
                                        }
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="widget-container">
            <div class="shadow btn-img" id="openweathermap-widget-2"></div>

        </div>
    </div>

</div>


</body>
</html>
