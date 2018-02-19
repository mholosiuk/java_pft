package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String id;
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String mobile;
  private String group;
  private String contact;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstname, that.firstname);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, firstname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            '}';

  }

  public String getId() {
    return id;
  }

  public ContactData(String firstname, String lastname, String address, String mobile, String group) {
    this.id = null;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.mobile = mobile;
    this.group = group;
  }

  public ContactData(String id, String firstname, String lastname, String address, String mobile, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.mobile = mobile;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getMobile() {
    return mobile;
  }

  public String getGroup() { return group; }

  public String getContact() { return contact; }

}
