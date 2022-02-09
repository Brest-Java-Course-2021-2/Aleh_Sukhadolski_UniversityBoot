/**
 * Request class . Fields <b>idR</b> <b>id</b> <b>groupe</b> <b>pairs</b> <b>subject</b> <b></>date</b>
 *
 * @autor Oleg Suhodolsky
 * @version 1.1
 */
package com.epam.brest.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//@Entity
public class Request implements Serializable {

    /** field idR - request identificator in database*/
    private int idR;

    /** field id - foreign key (one-to-many with User)
     * @see User
     */
    private int id;

    /** field groupe - groupe request*/
   /* @NotEmpty(message = "Groupe should be not empty")
    @Size(min = 1, max = 10, message = "Size of groupe should not be 1-10 characters")*/
    private String groupe;

    /** field pairs - pairs request*/
   /* @NotEmpty(message = "Pairs should be not empty")
    @Size(min = 1, max = 2, message = "Size of pairs should not be 1-2 characters")*/
    private String pairs;

    /** field subject - subject request*/
   /* @NotEmpty(message = "Subject should be not empty")
    @Size(min = 3, max = 100, message = "Size of subject should not be 3-100 characters")*/
    private String subject;

    /** field date - date when request was create or update*/
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date date;

    /**
     * Constructor - create new object
     * @see Request#Request()
     * @see Request#Request(int, String, String, String, Date)
     * @see Request#Request(int, String, String, String)
     * @param idR - identificator
     * @param id - identificator of User
     * @param groupe - groupe name
     * @param pairs - number pairs
     * @param subject - subject of user
     * @param date - date of request
     */
    public Request(int idR, int id, String groupe, String pairs, String subject, Date date) {
        this.idR = idR;
        this.id = id;
        this.groupe = groupe;
        this.pairs = pairs;
        this.subject = subject;
        this.date = date;
    }

    /**
     * Constructor - create new object
     * @see Request#Request()
     * @see Request#Request(int, String, String, String, Date)
     * @see Request#Request(int, int, String, String, String, Date)
     * @param id - identificator of User
     * @param groupe - groupe name
     * @param pairs - number pairs
     * @param subject - subject of user
     */
    public Request(int id, String groupe, String pairs, String subject) {
        //this.idR = idR;
        this.id = id;
        this.groupe = groupe;
        this.pairs = pairs;
        this.subject = subject;
        this.date = new Date();
    }


    /**
     * Constructor - create new object
     * @see Request#Request()
     * @see Request#Request(int, int, String, String, String, Date)
     * @see Request#Request(int, String, String, String)
     * @param id - identificator of User
     * @param groupe - groupe name
     * @param pairs - number pairs
     * @param subject - subject of user
     * @param date - date of request
     */
    public Request(int id, String groupe, String pairs, String subject, Date date) {
        this.id = id;
        this.groupe = groupe;
        this.pairs = pairs;
        this.subject = subject;
        this.date = date;
    }

    /**
     * Constructor - create new object
     * @see Request#Request(int, int, String, String, String, Date)
     * @see Request#Request(int, String, String, String, Date)
     * @see Request#Request(int, String, String, String)
     */
    public Request() {
    }

    /**
     * Getter - get field date {@link Request#date}
     * @return - return date of request
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter - set date field to Request {@link Request#date}
     * @param date - date of request
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter - get field idR {@link Request#idR}
     * @return - return identificator of request in database
     */
    public int getIdR() {
        return idR;
    }

    /**
     * Setter - set idR field to Request {@link Request#idR}
     * * @param idR - identificator of request in database
     */
    public void setIdR(int idR) {
        this.idR = idR;
    }

    /**
     * Getter - get field id {@link Request#id}
     * @return - return id (foreign key id - User.id)
     */
    public int getId() {
        return id;
    }

    /**
     * Setter - set id field to Request {@link Request#id}
     * @param id - foreign key id (linking with User.id)
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter - get field groupe {@link Request#groupe}
     * @return - return groupe of request
     */
    public String getGroupe() {
        return groupe;
    }

    /**
     * Setter - set groupe field to Request {@link Request#groupe}
     * @param groupe - groupe name
     */
    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    /**
     * Getter - get field pairs {@link Request#pairs}
     * @return - return pairs of request
     */
    public String getPairs() {
        return pairs;
    }

    /**
     * Setter - set pairs field to Request {@link Request#pairs}
     * @param pairs - number of pairs
     */
    public void setPairs(String pairs) {
        this.pairs = pairs;
    }

    /**
     * Getter - get field subject {@link Request#subject}
     * @return - return subject of request
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Setter - set subject field to Request {@link Request#subject}
     * @param subject - subject name
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }


    @Override
    public String toString() {
        return "Request{" +
                "idR=" + idR +
                ", id=" + id +
                ", groupe='" + groupe + '\'' +
                ", pairs='" + pairs + '\'' +
                ", subject='" + subject + '\'' +
                ", date=" + date +
                '}';
    }
}
