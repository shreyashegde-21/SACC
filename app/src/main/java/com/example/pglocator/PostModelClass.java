package com.example.pglocator;

public class PostModelClass {
    private String title, desc, image, UID;

    public PostModelClass() {
    }

    public PostModelClass(String title, String desc, String image, String UID) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.UID = UID;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getUID() { return UID; }
    public void setUID(String UID) { this.UID = UID; }
}
