package pe.edu.utp.micunatruck.models;

import java.util.Date;

public class Finance {
    private int id;
    private int originId;
    private int originTypeId;
    private double amount;
    private Date startDate;
    private Date endDate;
    private boolean flagActive;
    private Date updateAt;
    private Date deletedAt;
    private Date createdAt;

    public Finance() {

    }

    public Finance(int id, int originId, int originTypeId, double amount, Date startDate, Date endDate, boolean flagActive) {

    }

    public Finance(int id, int originId, int originTypeId, double amount, Date startDate, Date endDate,
                   boolean flagActive, Date updateAt, Date deletedAt, Date createdAt) {
        this.id = id;
        this.originId = originId;
        this.originTypeId = originTypeId;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.flagActive = flagActive;
        this.updateAt = updateAt;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
    }



    public int getId() {
        return id;
    }

    public Finance setId(int id) {
        this.id = id;
        return this;
    }

    public int getOriginId() {
        return originId;
    }

    public Finance setOriginId(int originId) {
        this.originId = originId;
        return this;
    }

    public int getOriginTypeId() {
        return originTypeId;
    }

    public Finance setOriginTypeId(int originTypeId) {
        this.originTypeId = originTypeId;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Finance setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Finance setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Finance setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public boolean getFlagActive() {
        return flagActive;
    }

    public Finance setFlagActive(boolean flagActive) {
        this.flagActive = flagActive;
        return this;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public Finance setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public Finance setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Finance setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}