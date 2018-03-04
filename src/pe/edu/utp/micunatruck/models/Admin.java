package pe.edu.utp.micunatruck.models;

import java.util.Date;

public class Admin {
    private int id;
    private String name;
    private String lastName;
    private String photo;
    private String email;
    private String password;
    private boolean flagActive;
    private Date updateAt;
    private Date deletedAt;
    private Date createdAt;

    public Admin() {
    }

    public Admin(int id, String name, String lastName, String photo, String email, String password, boolean flagActive, Date updateAt, Date deletedAt, Date createdAt) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.photo = photo;
        this.email = email;
        this.password = password;
        this.flagActive = flagActive;
        this.updateAt = updateAt;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public Admin setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Admin setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Admin setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public Admin setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Admin setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Admin setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean getFlagActive() {
        return flagActive;
    }

    public Admin setFlagActive(boolean flagActive) {
        this.flagActive = flagActive;
        return this;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public Admin setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public Admin setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Admin setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}