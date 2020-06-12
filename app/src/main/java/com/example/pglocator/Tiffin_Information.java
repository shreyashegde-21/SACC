package com.example.pglocator;

public class Tiffin_Information {
    private int thumbnail;
    private String tsname;
    private String tsphonenumber;
    private String tslocation;
    private String address;
    private String tsfoodtype;
    private String tsdescription;
    private String tsprices;
    private String tsadditionalinfo;

    public Tiffin_Information(int thumbnail, String tsname, String tsphonenumber, String tslocation,
                              String address, String tsfoodtype, String tsdescription, String tsprices,
                              String tsadditionalinfo) {
        this.thumbnail = thumbnail;
        this.tsname = tsname;
        this.tsphonenumber = tsphonenumber;
        this.tslocation = tslocation;
        this.address = address;
        this.tsfoodtype = tsfoodtype;
        this.tsdescription = tsdescription;
        this.tsprices = tsprices;
        this.tsadditionalinfo = tsadditionalinfo;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setTsname(String tsname) {
        this.tsname = tsname;
    }

    public void setTsphonenumber(String tsphonenumber) {
        this.tsphonenumber = tsphonenumber;
    }

    public void setTslocation(String tslocation) {
        this.tslocation = tslocation;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTsfoodtype(String tsfoodtype) {
        this.tsfoodtype = tsfoodtype;
    }

    public void setTsdescription(String tsdescription) {
        this.tsdescription = tsdescription;
    }

    public void setTsprices(String tsprices) {
        this.tsprices = tsprices;
    }

    public void setTsadditionalinfo(String tsadditionalinfo) {
        this.tsadditionalinfo = tsadditionalinfo;
    }


    public int getThumbnail() {
        return thumbnail;
    }

    public String getTsname() {
        return tsname;
    }

    public String getTsphonenumber() {
        return tsphonenumber;
    }

    public String getTslocation() {
        return tslocation;
    }

    public String getAddress() {
        return address;
    }

    public String getTsfoodtype() {
        return tsfoodtype;
    }

    public String getTsdescription() {
        return tsdescription;
    }

    public String getTsprices() {
        return tsprices;
    }

    public String getTsadditionalinfo() {
        return tsadditionalinfo;
    }
}
