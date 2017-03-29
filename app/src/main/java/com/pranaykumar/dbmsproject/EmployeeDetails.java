package com.pranaykumar.dbmsproject;


public class EmployeeDetails {

    private String eid;
    private String name;
    private String phone;
    private String gender;
    private String email;


    public String getEid() {
        return eid;
    }

    public void setId(String eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public EmployeeDetails(){
    }

    public EmployeeDetails(String eid, String name, String phone, String gender, String email){

        this.eid = eid;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.email = email;

    }
}

