package com.epam.brest.model;

import java.io.Serializable;

/**
 * Entity class - groupes consist in the university
 */
public class Groupe implements Serializable {

    /**
     * field idGroupe - identificator groupe entity in database
     */
    private int idGroupe;

    /**
     * field nameGroupe -  groupe name in database
     */
    private String nameGroupe;

    /**
     * Constructor - create new object
     * @see Groupe#Groupe(String)
     * @see Groupe#Groupe()
     *   @param idGroupe - identificator groupe in the database
     *   @param nameGroupe - groupe name
     */
    public Groupe(int idGroupe, String nameGroupe) {
        this.idGroupe = idGroupe;
        this.nameGroupe = nameGroupe;
    }

    /**
     * Constructor - create new object
     * @see Groupe#Groupe(int, String)
     * @see Groupe#Groupe()
     * @param nameGroupe - groupe name
     */
    public Groupe(String nameGroupe) {
        this.nameGroupe = nameGroupe;
    }

    /**
     * Constructor - create new object
     * @see Groupe#Groupe(int, String)
     * @see Groupe#Groupe(String)
     */
    public Groupe() {
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNameGroupe() {
        return nameGroupe;
    }

    public void setNameGroupe(String nameGroupe) {
        this.nameGroupe = nameGroupe;
    }
}
