package com.freelanceapps33.weather.models;


public class Weather {

    private String day ;
    private String month ;
    private String year ;
    private String monthname ;
    private String weekday ;
    private String high_celsius ;
    private String low_celsius ;

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    private String icon_url ;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonthname() {
        return monthname;
    }

    public void setMonthname(String monthname) {
        this.monthname = monthname;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getHigh_celsius() {
        return high_celsius;
    }

    public void setHigh_celsius(String high_celsius) {
        this.high_celsius = high_celsius;
    }

    public String getLow_celsius() {
        return low_celsius;
    }

    public void setLow_celsius(String low_celsius) {
        this.low_celsius = low_celsius;
    }
}
