package model;

import java.time.ZonedDateTime;
import java.util.Arrays;

public class WeatherBean {


    public ZonedDateTime globalTimeInt;
    private String cityStr;
    private String countryStr;
    private String cloudsStr;
    private String temperature;
    private String cityIDStr;

    public void setCityStr(String cityStr) {
        this.cityStr = cityStr;
    }

    public void setCountryStr(String countryStr) {
        this.countryStr = countryStr;
    }

    private int widgetIDInt;

    public WeatherBean() {

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

    public int getWidgetIDInt() {
        if(!(widgetIDInt > 0)) {
            return 1;
        } else {
            return widgetIDInt;
        }
    }

    public void setWidgetIDInt(int widgetIDInt) {
        this.widgetIDInt = widgetIDInt;
    }

    public WeatherBean(String cityStr, String countryStr) {

        this.cityStr = cityStr;
        this.countryStr = countryStr;

    }
    public String getGlobalDateInt() {
        return Arrays.asList(String.valueOf(globalTimeInt).split("T",0)).get(0);
    }
    public String getGlobalTimeInt() {
        return Arrays.asList(String.valueOf(globalTimeInt).split("[T.]",0)).get(1);
    }

    public void setGlobalTimeInt(ZonedDateTime timeZoneInt) {
        this.globalTimeInt = timeZoneInt;
    }
    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {

        this.temperature = String.format("%.1f", (Double.parseDouble(temperature) -273.15));
    }
    public String getCityStr() {

        return cityStr.substring(0,1).toUpperCase() + cityStr.substring(1);
    }

    public String getCountryStr() {

        return  countryStr.substring(0,1).toUpperCase() + countryStr.substring(1);
    }

    public String getCloudsStr() {
        return cloudsStr;
    }

    public void setCloudsStr(String cloudsStr) {
        this.cloudsStr = cloudsStr;
    }

    public void setCityID(String cityIDStr) { this.cityIDStr = cityIDStr; }
    public String getCityID() {
        return cityIDStr; }

    public String checkForSpaceInString(String userInput){
        if(userInput.contains("\\s")){
            userInput.replaceAll("\s", "" );
        }
        return userInput;
    }

}
