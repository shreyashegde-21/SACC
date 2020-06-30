package com.example.pglocator;

public class Owner_helper_Class {
    String Name;
    int Age;
    String Sex, Phonenumber, Email, Aadharnumber, Username, Password;

    public Owner_helper_Class() {

    }

    public Owner_helper_Class(String name, int age, String sex, String phonenumber,
                              String email, String aadharnumber, String username, String password) {
        Name = name;
        Age = age;
        Sex = sex;
        Phonenumber = phonenumber;
        Email = email;
        Aadharnumber = aadharnumber;
        Username = username;
        Password = password;
    }

    public String getName() { return Name; }
    public void setName(String name) { Name = name; }

    public int getAge() { return Age; }
    public void setAge(int age) { Age = age; }

    public String getSex() { return Sex; }
    public void setSex(String sex) { Sex = sex; }

    public String getPhonenumber() { return Phonenumber; }
    public void setPhonenumber(String phonenumber) { Phonenumber = phonenumber; }

    public String getEmail() { return Email; }
    public void setEmail(String email) { Email = email; }

    public String getAadharnumber() { return Aadharnumber; }
    public void setAadharnumber(String aadharnumber) { Aadharnumber = aadharnumber; }

    public String getUsername() { return Username; }
    public void setUsername(String username) { Username = username; }

    public String getPassword() { return Password; }
    public void setPassword(String password) { Password = password; }
}
