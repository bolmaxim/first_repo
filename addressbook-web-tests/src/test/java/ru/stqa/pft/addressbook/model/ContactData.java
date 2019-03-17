package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table (name = "addressbook")

public class ContactData {

    @XStreamOmitField
    @Id
    @Column (name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column (name = "firstname")
    private String firstname;
    @Expose
    @Column (name = "lastname")
    private String lastname;
    @Expose
    @Type(type = "text")
    @Column (name = "mobile")
    private String mobilePhone;
    @Expose
    @Type(type = "text")
    @Column (name = "email")
    private String email;
    @Expose
    @Type(type = "text")
    @Column (name = "email2")
    private String email1;
    @Expose
    @Type(type = "text")
    @Column (name = "email3")
    private String email2;
    @Expose
    @Type(type = "text")
    @Column (name = "address")
    private String address;

    @Expose
    @Transient
    private String group;
    @Expose
    @Type(type = "text")
    @Column (name = "home")
    private String homePhone;
    @Expose
    @Type(type = "text")
    @Column (name = "work")
    private String workPhone;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Type(type = "text")
    @Column (name = "photo")
    private String photo;
    public File getPhoto() {
        if (photo == null) {
            return null;
        } else {
            return new File(photo);
        }
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(email1, that.email1) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(address, that.address) &&
                Objects.equals(homePhone, that.homePhone) &&
                Objects.equals(workPhone, that.workPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, mobilePhone, email, email1, email2, address, homePhone, workPhone);
    }

    public String getWorkPhone() { return workPhone; }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public int getId() { return id; }


}
