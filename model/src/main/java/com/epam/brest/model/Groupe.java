package com.epam.brest.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "groupe")
public class Groupe {
    @Column(name = "idG", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idG;

    /** field groupe - groupe request*/
    @Column(name = "groupe", nullable = false, length = 10)
    @NotEmpty(message = "Groupe should be not empty")
    @Size(min = 1, max = 10, message = "Size of groupe should not be 1-10 characters")
    private String groupe;


    public Groupe(int idG, String groupe) {
        this.idG = idG;
        this.groupe = groupe;
    }

    public Groupe(String groupe) {
        this.groupe = groupe;
    }

    public Groupe() {
    }

    public int getIdG() {
        return idG;
    }

    public void setIdG(int idG) {
        this.idG = idG;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    @Override
    public String toString() {
        return "Groupe{" +
                "idG=" + idG +
                ", groupe='" + groupe + '\'' +
                '}';
    }
}
