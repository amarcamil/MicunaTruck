package pe.edu.utp.micunatruck.models;

import java.util.Date;

public class User {
    private int id;
    private UserType userType;
    private String name;
    private String lastName;
    private String legalName;
    private String description;
    private String photo;
    private String address;
    private String telephone;
    private String email;
    private String password;
    private boolean flagActive;
    private Date deletedAt = new Date();
    private Date updatedAt = new Date();
    private Date createdAt = new Date();

    public User()
    {

    }

    public User(int id,
                UserType userType, String name, String lastName, String legalName,
                String description, String photo, String address, String telephone,
                String email, String password, boolean flagActive)
    {
        this.setId(id);
        this.setUserType(userType);
        this.setName(name);
        this.setLastName(lastName);
        this.setLegalName(legalName);
        this.setDescription(description);
        this.setPhoto(photo);
        this.setAddress(address);
        this.setTelephone(telephone);
        this.setEmail(email);
        this.setPassword(password);
        this.setFlagActive(flagActive);
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public UserType getUserType() {
        return userType;
    }

    public User setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLegalName() {
        return legalName;
    }

    public User setLegalName(String legalName) {
        this.legalName = legalName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public User setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public User setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isFlagActive() {
        return flagActive;
    }

    public User setFlagActive(boolean flagActive) {
        this.flagActive = flagActive;
        return this;
    }

    public boolean getFlagActive() {
        return flagActive;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}