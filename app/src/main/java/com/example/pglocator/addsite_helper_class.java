package com.example.pglocator;

import java.util.ArrayList;

public class addsite_helper_class {

    String Image, Name, Address, City, Pincode, PhoneNumber, Email, Type, Rent_Per_Month_INR,
            Food_Availability, Furnishment, AC_Availability, Room_Type;
    ArrayList<String> Facility_In_Vicinity;

    public addsite_helper_class() {

    }

    public addsite_helper_class(String image, String name, String address, String city, String pincode, String phoneNumber, String email,
                                String type, String rent_Per_Month_INR, ArrayList<String> facility_In_Vicinity, String food_Availability,
                                String furnishment, String AC_Availability, String room_Type) {
        Image = image;
        Name = name;
        Address = address;
        City = city;
        Pincode = pincode;
        PhoneNumber = phoneNumber;
        Email = email;
        Type = type;
        Rent_Per_Month_INR = rent_Per_Month_INR;
        Food_Availability = food_Availability;
        Furnishment = furnishment;
        this.AC_Availability = AC_Availability;
        Room_Type = room_Type;
        Facility_In_Vicinity = facility_In_Vicinity;
    }

    public String getImage() { return Image; }
    public void setImage(String image) { Image = image; }

    public String getName() { return Name; }
    public void setName(String name) { Name = name; }

    public String getAddress() { return Address; }
    public void setAddress(String address) { Address = address; }

    public String getCity() { return City; }
    public void setCity(String city) { City = city; }

    public String getPincode() { return Pincode; }
    public void setPincode(String pincode) { Pincode = pincode; }

    public String getPhoneNumber() { return PhoneNumber; }
    public void setPhoneNumber(String phoneNumber) { PhoneNumber = phoneNumber; }

    public String getEmail() { return Email; }
    public void setEmail(String email) { Email = email; }

    public String getType() { return Type; }
    public void setType(String type) { Type = type; }

    public String getRent_Per_Month_INR() { return Rent_Per_Month_INR; }
    public void setRent_Per_Month_INR(String rent_Per_Month_INR) { Rent_Per_Month_INR = rent_Per_Month_INR; }

    public ArrayList<String> getFacility_In_Vicinity() { return Facility_In_Vicinity; }
    public void setFacility_In_Vicinity(ArrayList<String> facility_In_Vicinity) { Facility_In_Vicinity = facility_In_Vicinity; }

    public String getFood_Availability() { return Food_Availability; }
    public void setFood_Availability(String food_Availability) { Food_Availability = food_Availability; }

    public String getFurnishment() { return Furnishment; }
    public void setFurnishment(String furnishment) { Furnishment = furnishment; }

    public String getAC_Availability() { return AC_Availability; }
    public void setAC_Availability(String AC_Availability) { this.AC_Availability = AC_Availability; }

    public String getRoom_Type() { return Room_Type; }
    public void setRoom_Type(String room_Type) { Room_Type = room_Type; }
}

