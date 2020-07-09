package com.example.pglocator;

public class Tenant_helper_Class {

    String UID,Name;
    int Age;
    String Sex, Phonenumber, Email, Aadharnumber, Hometownaddress, Proffession,College_Or_Company_Name,
            College_Or_Company_Address, Username, Password;

    public Tenant_helper_Class(){
    }


    public Tenant_helper_Class(String uid, String name, int age, String sex,  String phonenumber,
                               String email, String aadharnumber, String hometownaddress, String proffession,
                               String college_Or_Company_Name, String college_Or_Company_Address, String username,
                               String password) {
        UID = uid;
        Name = name;
        Age = age;
        Sex = sex;
        Phonenumber = phonenumber;
        Email = email;
        Aadharnumber = aadharnumber;
        Hometownaddress = hometownaddress;
        Proffession = proffession;
        College_Or_Company_Name = college_Or_Company_Name;
        College_Or_Company_Address = college_Or_Company_Address;
        Username = username;
        Password = password;
    }

    public String getUID() { return UID; }
    public void setUID(String UID) { this.UID = UID; }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public int getAge() { return Age;}
    public void setAge(int age) { Age = age; }

    public String getSex() { return Sex; }
    public void setSex(String sex) { Sex = sex; }

    public String getPhonenumber() { return Phonenumber; }
    public void setPhonenumber(String phonenumber) { Phonenumber = phonenumber; }

    public String getEmail() { return Email; }
    public void setEmail(String email) { Email = email; }

    public String getAadharnumber() { return Aadharnumber; }
    public void setAadharnumber(String aadharnumber) { Aadharnumber = aadharnumber; }

    public String getHometownaddress() { return Hometownaddress; }
    public void setHometownaddress(String hometownaddress) { Hometownaddress = hometownaddress; }

    public String getProffession() { return Proffession;}
    public void setProffession(String proffession) { Proffession = proffession;}

    public String getCollege_Or_Company_Name() { return College_Or_Company_Name; }
    public void setCollege_Or_Company_Name(String college_Or_Company_Name) { College_Or_Company_Name = college_Or_Company_Name; }

    public String getCollege_Or_Company_Address() { return College_Or_Company_Address; }
    public void setCollege_Or_Company_Address(String college_Or_Company_Address) { College_Or_Company_Address = college_Or_Company_Address; }

    public String getUsername() { return Username; }
    public void setUsername(String username) { Username = username; }

    public String getPassword() { return Password; }
    public void setPassword(String password) { Password = password; }
}
