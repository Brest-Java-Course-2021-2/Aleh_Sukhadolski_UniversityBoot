package com.epam.brest;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "lectors")
public class Lector {

    /** field id - lectors's identificator in database*/
    @Column(name = "idLector", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idLector;

    /** field name - lectors's fullname*/
    @Column(name = "nameLector", nullable = false, length = 50)
    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 50, message = "Size of name should not be 2-50 characters")
    private String nameLector;

    /** field name - lectors's login in system*/
    @Column(name = "loginLector", nullable = false, length = 50)
    @NotEmpty(message = "Login should be not empty")
    @Size(min = 2, max = 50, message = "Size of login should not be 2-50 characters")
    private String loginLector;

    /** field name - lectors's password*/
    @Column(name = "passwordLector", nullable = false, length = 50)
    @NotEmpty(message = "Password should be not empty")
    @Size(min = 4, max = 50, message = "Size of password should not be 4-50 characters")
    private String passwordLector;

    /** field name - lectors's email*/
    @Column(name = "emailLector", nullable = false)
    @NotEmpty(message = "Email should  be not empty")
    @Email(message = "Email should  be valid")
    /** field name - user's e-mail*/
    private String emailLector;


    public Lector() {}

    public Lector(String nameLector, String loginLector, String passwordLector, String emailLector) {
        this.nameLector = nameLector;
        this.loginLector = loginLector;
        this.passwordLector = passwordLector;
        this.emailLector = emailLector;
    }

    public Lector(int idLector, String nameLector, String loginLector, String passwordLector, String emailLector) {
        this.idLector = idLector;
        this.nameLector = nameLector;
        this.loginLector = loginLector;
        this.passwordLector = passwordLector;
        this.emailLector = emailLector;
    }

    public int getIdLector() {
        return idLector;
    }

    public void setIdLector(int idLector) {
        this.idLector = idLector;
    }

    public String getNameLector() {
        return nameLector;
    }

    public void setNameLector(String nameLector) {
        this.nameLector = nameLector;
    }

    public String getLoginLector() {
        return loginLector;
    }

    public void setLoginLector(String loginLector) {
        this.loginLector = loginLector;
    }

    public String getPasswordLector() {
        return passwordLector;
    }

    public void setPasswordLector(String passwordLector) {
        this.passwordLector = passwordLector;
    }

    public String getEmailLector() {
        return emailLector;
    }

    public void setEmailLector(String emailLector) {
        this.emailLector = emailLector;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "idLector=" + idLector +
                ", nameLector='" + nameLector + '\'' +
                ", loginLector='" + loginLector + '\'' +
                ", passwordLector='" + passwordLector + '\'' +
                ", emailLector='" + emailLector + '\'' +
                '}';
    }
}
