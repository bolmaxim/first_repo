package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

public class ContactData {

    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String mobilePhone;
    @Expose
    private String email;
    @Expose
    private String email1;
    @Expose
    private String email2;
    @Expose
    private String address;
    @Expose
    private String group;
    @Expose
    private String homePhone;
    @Expose
    private String workPhone;
    private String allPhones;
    private String allEmails;

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    private File photo;

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }


    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }
    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }


    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }


    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }
  //  public ContactData withMobilePhone(String mobilePhone) {
   //     this.mobilePhone = mobilePhone;
   //     return this;
   // }
    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() { return email; }

    public String getEmail1() { return email1; }

    public String getEmail2() { return email2; }

    public String getAddress() { return address; }

    public String getGroup() { return group; }

    public String getHomePhone() { return homePhone; }

    public String getWorkPhone() { return workPhone; }


    public int getId() { return id; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

}
