package com.travelmate.data;

import java.util.ArrayList;

/**
 * Created by Betim on 5/20/2018.
 */

public class Restaurants {
    //https://fake-hotel-api.herokuapp.com/api/hotels
    //Hotels

    private String Name,Country,City,Address,UrlImage,Phone,Price;
    private double Lat,Log,Rating;

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getUrlImage() {
        return UrlImage;
    }

    public void setUrlImage(String urlImage) {
        UrlImage = urlImage;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLog() {
        return Log;
    }

    public void setLog(double log) {
        Log = log;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public double getPhone() {
        return Rating;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Restaurants(String name, String country, String city, String address,String phone, double lat, double log, double rating, String urlImage,String price) {
        Name = name;
        Country = country;
        City = city;
        Address = address;
        Phone = phone;
        Lat = lat;
        Log = log;
        Rating = rating;
        UrlImage = urlImage;
        Price = price;
    }

}
