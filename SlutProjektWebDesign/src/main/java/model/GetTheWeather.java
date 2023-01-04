package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class GetTheWeather {
    public static void getWeather(WeatherBean wBean) throws IOException {

        String API_KEY = "b904383e0cb531fe313068de52a606de";
        // Build the API call URL by adding city+country into a URL
        String URLtoSend = "http://api.openweathermap.org/data/2.5/weather?q=" + wBean.checkForSpaceInString(wBean.getCityStr()) + ","
                + wBean.checkForSpaceInString(wBean.getCountryStr()) + "&APPID=" + API_KEY + "&mode=xml";

        // print and test in a browser
        System.out.println("URL TO SEND: " + URLtoSend);

        // Set the URL that will be sent
        URL line_api_url = new URL(URLtoSend);

        // Create a HTTP connection to sent the GET request over
        HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();
        linec.setDoInput(true);
        linec.setDoOutput(true);
        linec.setRequestMethod("GET");

        // Make a Buffer to read the response from the API
        BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

        // a String to temp save each line in the response
        String inputLine;

        // a String to save the full response to use later
        String ApiResponse = "";

        // loop through the whole response
        while ((inputLine = in.readLine()) != null) {

            System.out.println(inputLine);
            // Save the temp line into the full response
            ApiResponse += inputLine;
        }
        in.close();

        // print the response
        System.out.println(ApiResponse);

        // Call a method to make a XMLdoc out of the full response
        Document doc = convertStringToXMLDocument(ApiResponse);

        // check that the XML response is OK by getting the Root element
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        // Create a Node list that gets everything in and under the "clouds" tag
        NodeList nList = doc.getElementsByTagName("clouds");
        NodeList nListTemp = doc.getElementsByTagName("temperature");
        NodeList nListCityID = doc.getElementsByTagName("city");
        NodeList nodeListTimeZone = doc.getElementsByTagName("timezone");


        // loop through the content of the tag
        for (int temp = 0; temp < nList.getLength(); temp++) {
            // Save a node of the current list id
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                // set the current node as an Element
                Element eElement = (Element) node;
                // get the content of an attribute in element
                String XMLclouds = eElement.getAttribute("name");



                // and print it
                System.out.println(wBean.getCityStr() + " is now a " + XMLclouds);
                // save it
                wBean.setCloudsStr(XMLclouds);

            }
        }

        for (int temp = 0; temp < nListTemp.getLength(); temp++) {
            // Save a node of the current list id
            Node node = nListTemp.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                // set the current node as an Element
                Element eElement = (Element) node;
                // get the content of an attribute in element
                String XMLtemperature = eElement.getAttribute("value");
                // and print it
                System.out.println(wBean.getCityStr() + "temperature is now " + XMLtemperature);
                // save it
                wBean.setTemperature(XMLtemperature);

            }
        }

        for (int temp = 0; temp < nListCityID.getLength(); temp++) {
            // Save a node of the current list id
            Node node = nListCityID.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                // set the current node as an Element
                Element eElement = (Element) node;
                // get the content of an attribute in element
                String XMLcityID = eElement.getAttribute("id");
                // and print it
                System.out.println(wBean.getCityStr() + " city id is :  " + XMLcityID);
                // save it
                wBean.setCityID(XMLcityID);

            }
        }

        for (int temp = 0; temp < nodeListTimeZone.getLength(); temp++) {
            // Save a node of the current list id
            Node node = nodeListTimeZone.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                // set the current node as an Element
                Element eElement = (Element) node;
                // get the content of an attribute in element
                String XMLtimeZone = eElement.getTextContent();
                System.out.println("XMLtimeZone: " + XMLtimeZone);

                ZoneOffset timeZone = ZoneOffset.ofTotalSeconds(Integer.parseInt(XMLtimeZone));
                ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of(timeZone.toString()));
                System.out.println("DateTime: " + dateTime);
                wBean.setGlobalTimeInt(dateTime);
                System.out.println("THE DATE: " + wBean.getGlobalDateInt());
                System.out.println("THE TIME: " + wBean.getGlobalTimeInt());

            }
        }

    }

    // Method the makes a XML doc out of a string, if it is in a XML format.
    static Document convertStringToXMLDocument(String xmlString) {
        // Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            // Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            // Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

