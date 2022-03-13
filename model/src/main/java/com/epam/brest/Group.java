package com.epam.brest;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "group")
public class Group {
    @Column(name = "idGroup", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idGroup;

    /** field groupe - groupe request*/
    @Column(name = "groupName", nullable = false, length = 10)
    @NotEmpty(message = "Group name should be not empty")
    @Size(min = 2, max = 10, message = "Size of groupe should not be 1-10 characters")
    private String groupName;

    public Group(int idGroup, String groupName) {
        this.idGroup = idGroup;
        this.groupName = groupName;
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group() {
    }


    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "idGroup=" + idGroup +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
