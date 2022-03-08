/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ibrahim
 */
public class Adresse {
    private double longitude;
    private double latitude;
    private String country;
    private String city;
    private String placeId;

    public Adresse(double longitude, double latitude, String country, String city, String placeId) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.country = country;
        this.city = city;
        this.placeId = placeId;
    }

    public Adresse() {
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "Adresse{" + "longitude=" + longitude + ", latitude=" + latitude + ", country=" + country + ", city=" + city + ", placeId=" + placeId + '}';
    }
    
}
