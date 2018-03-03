package pe.edu.utp.micunatruck.models;

import java.util.Date;

public class UserType {
    private int id;
    private String name;
    private String description;
    private boolean flagActive;
    private Date updateAt;
    private Date deletedAt;
    private Date createdAt;

    public int getId() {
        return id;
    }

    public UserType setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserType setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserType setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isFlagActive() {
        return flagActive;
    }

    public UserType setFlagActive(boolean flagActive) {
        this.flagActive = flagActive;
        return this;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public UserType setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public UserType setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public UserType setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}