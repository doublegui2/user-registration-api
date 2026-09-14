package org.user.registration.ms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    // ISO-8601 YYYY-MM-DD format
    private String birthdate;

    @Column(nullable = false)
    // ISO 3-letter format
    private String countryOfResidence;

    @Column
    private String phoneNumber;

    @Column
    private String gender;

    public UserEntity(String username, String birthdate, String countryOfResidence, String phoneNumber, String gender) {
        this.setUsername(username);
        this.setBirthdate(birthdate);
        this.setCountryOfResidence(countryOfResidence);
        this.setPhoneNumber(phoneNumber);
        this.setGender(gender);
    }

    protected UserEntity() { }

    private String getUsername() {
        return this.username;
    }
    private void setUsername(String username) {
        this.username = username;
    }

    private String getBirthdate() {
        return this.birthdate;
    }
    private void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    private String getCountryOfResidence() {
        return this.countryOfResidence;
    }
    private void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
