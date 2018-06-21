package com.travelmate.data;

/**
 * Created by Betim on 6/21/2018.
 */

public class Recommended {

    private String Name,Address,UrlImage,Phone;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Recommended(String name, String address, String phone, String urlImage) {
        Name = name;
        Address = address;
        Phone = phone;
        UrlImage = urlImage;

    }
}
