package org.user.registration.ms.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
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

    @Column
    private Instant createdAt;

    public UserEntity(String username, String birthdate, String countryOfResidence, String phoneNumber, String gender) {
        this.setUsername(username);
        this.setBirthdate(birthdate);
        this.setCountryOfResidence(countryOfResidence);
        this.setPhoneNumber(phoneNumber);
        this.setGender(gender);
        this.createdAt = Instant.now();
    }

    protected UserEntity() { }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthdate() {
        return this.birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountryOfResidence() {
        return this.countryOfResidence;
    }
    public void setCountryOfResidence(String countryOfResidence) {
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

    public Instant createdAt() {
        return this.createdAt;
    }
}
