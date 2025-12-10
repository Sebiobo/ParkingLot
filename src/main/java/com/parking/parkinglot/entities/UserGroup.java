package com.parking.parkinglot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usergroups")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "userGroup")
    private String userGroup;

    public UserGroup() {
        super();
    }

    public String getUserGroup() {
        return userGroup;
    }
    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Constructor opțional (util pentru a crea obiectul rapid)
    public UserGroup(String username, String userGroup) {
        this.username = username;
        this.userGroup = userGroup;
    }
}
