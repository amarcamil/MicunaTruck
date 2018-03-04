package pe.edu.utp.micunatruck.models;

import java.util.Date;

public class Ads {
    private int id;
    private Admin admin;
    private String name;
    private String description;
    private String image;
    private double price;
    private Date start_date;
    private Date end_date;
    private int flagActive;
    private Date deletedAt = new Date();
    private Date updatedAt = new Date();
    private Date createdAt = new Date();
    /****Campo pendiente de agregar a la bd jose luis  16/02/2018 ****/
    private int code_space;

    public Ads() {

    }



    public Ads(int id, Admin admin, String name, String description, String image, double price, Date start_date, Date end_date, int flagActive, Date deletedAt, Date updatedAt, Date createdAt, int code_space) {
        this.id = id;
        this.admin = admin;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.start_date = start_date;
        this.end_date = end_date;
        this.flagActive = flagActive;
        this.deletedAt = deletedAt;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.code_space = code_space;
    }

    public Ads(int id, Admin admin, String name, String description, String image, double price, Date start_date, Date end_date, int flagActive, Date createdAt, int code_space) {
        this.id = id;
        this.admin = admin;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.start_date = start_date;
        this.end_date = end_date;
        this.flagActive = flagActive;
        this.createdAt = createdAt;
        this.code_space = code_space;
    }

    public int getId() {
        return id;
    }

    public Ads setId(int id) {
        this.id = id;
        return this;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Ads setAdmin(Admin admin) {
        this.admin = admin;
        return this;
    }

    public String getName() {
        return name;
    }

    public Ads setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Ads setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Ads setImage(String image) {
        this.image = image;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Ads setPrice(double price) {
        this.price = price;
        return this;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Ads setStart_date(Date start_date) {
        this.start_date = start_date;
        return this;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public Ads setEnd_date(Date end_date) {
        this.end_date = end_date;
        return this;
    }

    public int getFlagActive() {
        return flagActive;
    }

    public Ads setFlagActive(int flagActive) {
        this.flagActive = flagActive;
        return this;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public Ads setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Ads setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Ads setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public int getCodeSpace() {
        return code_space;
    }

    public Ads setCodeSpace(int code_space) {
        this.code_space = code_space;
        return this;
    }
}