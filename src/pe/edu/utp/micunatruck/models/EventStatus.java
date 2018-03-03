package pe.edu.utp.micunatruck.models;

public class EventStatus {
    private int id;
    private String name;

    public EventStatus() {
    }

    public EventStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public EventStatus setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EventStatus setName(String name) {
        this.name = name;
        return this;
    }
}