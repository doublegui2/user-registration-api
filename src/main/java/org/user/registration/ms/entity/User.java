package org.user.registration.ms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

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


}
