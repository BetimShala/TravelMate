package com.travelmate.data;

import java.util.ArrayList;

/**
 * Created by Betim on 5/20/2018.
 */

public class Hotels {
    //https://fake-hotel-api.herokuapp.com/api/hotels
    //Hotels

    private String Name,Country,City,Description;
    private double Price,Stars,Rating;
    private ArrayList<String> UrlImages;


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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getStars() {
        return Stars;
    }

    public void setStars(double stars) {
        Stars = stars;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public ArrayList<String> getUrlImages() {
        return UrlImages;
    }

    public void setUrlImages(ArrayList<String> urlImages) {
        UrlImages = urlImages;
    }

    public Hotels(String name, String country, String city, String description, double price, double stars, double rating, ArrayList<String> urlImages) {
        Name = name;
        Country = country;
        City = city;
        Description = description;
        Price = price;
        Stars = stars;
        Rating = rating;
        UrlImages = urlImages;
    }

}
