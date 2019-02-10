package ru.stqa.pft.addressbook.tests;

public class ContactData {
    private final String name;
    private final String lastname;
    private final String mobilephone;
    private final String email;

    public ContactData(String name, String lastname, String mobilephone, String email) {
        this.name = name;
        this.lastname = lastname;
        this.mobilephone = mobilephone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getEmail() {
        return email;
    }
}
