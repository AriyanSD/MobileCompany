import java.io.Serializable;

public class Address implements Cloneable, Comparable<Address>, Serializable {
    protected int streetNum;
    protected String street;
    protected String suburb;
    protected String city;

    public Address(int streetNum, String street, String suburb, String city) {
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNum() {
        return streetNum;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void print() {
        System.out
                .print("Street number: " + streetNum + " Street: " + street + " Subrub: " + suburb + " City: " + city);
    }

    public String toString() {
        return "Street number: " + streetNum + " Street: " + street + " Subrub: " + suburb + " City: " + city;
    }

    // LAB4
    public Address(Address address) {
        this.streetNum = address.streetNum;
        this.street = address.street;
        this.suburb = address.suburb;
        this.city = address.city;
    }

    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }

    public int compareTo(Address other) {
        return this.city.compareTo(other.city);
    }

    // Lab6
    public String toDilimatedString() {
        return streetNum + "," + street + "," + suburb + "," + city;
    }
}
