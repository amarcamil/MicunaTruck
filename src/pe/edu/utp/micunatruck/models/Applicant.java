package pe.edu.utp.micunatruck.models;

import java.util.Date;

public class Applicant {

    private int id;
    private User user;
    private Event event;
    private boolean flagActive;
    private Date updatedAt;
    private Date deleteAt;
    private Date createdAt;

    public Applicant() {

    }

    public Applicant(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public Applicant(int id, User user, Event event) {
        this.id = id;
        this.user = user;
        this.event = event;
    }

    public Applicant(int id, User user, Event event, boolean flagActive, Date updatedAt, Date deleteAt, Date createdAt) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.flagActive = flagActive;
        this.updatedAt = updatedAt;
        this.deleteAt = deleteAt;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public Applicant setId(int id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Applicant setUser(User user) {
        this.user = user;
        return this;
    }

    public Event getEvent() {
        return event;
    }

    public Applicant setEvent(Event event) {
        this.event = event;
        return this;
    }

    public boolean isFlagActive() {
        return flagActive;
    }

    public Applicant setFlagActive(boolean flagActive) {
        this.flagActive = flagActive;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Applicant setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public Applicant setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Applicant setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}