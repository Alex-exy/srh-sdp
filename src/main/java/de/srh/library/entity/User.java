package de.srh.library.entity;

import java.util.Date;

public class User {
  private Long userId;
  private String email;
  private String userRole;
  private String firstName;
  private String familyName;
  private String address;
  private String userStatus;
  private String passwordHash;
  private Date registrationDate;
  private Date updateTime;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(String userStatus) {
    this.userStatus = userStatus;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName()
        + " ["
        + "Hash = "
        + hashCode()
        + ", userId="
        + userId
        + ", email="
        + email
        + ", userRole="
        + userRole
        + ", firstName="
        + firstName
        + ", familyName="
        + familyName
        + ", address="
        + address
        + ", userStatus="
        + userStatus
        + ", passwordHash="
        + passwordHash
        + ", registrationDate="
        + registrationDate
        + ", updateTime="
        + updateTime
        + "]";
  }
}
