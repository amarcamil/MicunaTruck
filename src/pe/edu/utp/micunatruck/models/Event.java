package pe.edu.utp.micunatruck.models;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {

    private int id;
    private User user;
    private EventStatus eventStatus;
    private String name;
    private String description;
    private String image;
    private int flagActive;
    private Date date;
    private Date deleteAt;
    private Date updatedAt;
    private Date createdAt;
    private boolean isSendRequest = false;

    public Event(){

    }

    public Event(int id, User user, EventStatus eventStatus, String name, String description, String image, int flagActive, String date){
        this.setId(id);
        this.setUser(user);
        this.setEventStatus(eventStatus);
        this.setName(name);
        this.setDescription(description);
        this.setImage(image);
        this.setDate(date);
        this.setFlagActive(flagActive);
    }

    public int getId() {
        return id;
    }

    public Event setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Event setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Event setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Event setImage(String image) {
        this.image = image;
        return this;
    }

    public int getFlagActive() {
        return flagActive;
    }

    public Event setFlagActive(int flagActive) {
        this.flagActive = flagActive;
        return this;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }


    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public Event setUser(User user) {
        this.user = user;
        return this;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public Event setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Event setDate(String date) {
        Date dateParse = null;
        boolean parseableEnter = false;
        try {
            if(date != null && date != ""){
                parseableEnter = true;
                dateParse = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            }
        } catch (ParseException e) {
            //TODO: quitar este try catch
            if(parseableEnter){
                try {
                    dateParse = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                } catch (ParseException e1) {
                    dateParse = null;
                }
            }
            e.printStackTrace();
        }
        this.date = dateParse;
        return this;
    }

    public String getDateParse(){
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = "";
        try{
            date = formatter.format(this.getDate());
        }catch (Exception e) {
            date = "-";
        }
        return date;
    }

    public boolean getIsSendRequest() {
        return isSendRequest;
    }

    public void setIsSendRequest(boolean sendRequest) {
        this.isSendRequest = sendRequest;
    }
}