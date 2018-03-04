package pe.edu.utp.micunatruck.beans;

import pe.edu.utp.micunatruck.models.Event;
import pe.edu.utp.micunatruck.models.EventStatus;
import pe.edu.utp.micunatruck.models.User;
import pe.edu.utp.micunatruck.services.MicunaTruckService;
import pe.edu.utp.micunatruck.utils.SessionUtils;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

@Named
@SessionScoped
public class EventsBean implements Serializable {
    private MicunaTruckService service;
    private Event event;
    private User user;
    private EventStatus eventStatus;

    public EventsBean() {
        service = new MicunaTruckService();
    }

    public List<Event> getEvents() {
        return service.findAllEvents();
    }

    public List<Event> getEventsByUser() {
        this.setUser((User) SessionUtils.getUser());
        return service.findAllEventsByUser(this.getUser());
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public String getEventStatusName(){
        return eventStatus.getName();
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getName() {
        return this.getEvent().getName();
    }
    //patron de diseño delegate
    public void setName(String name){
        this.getEvent().setName(name);
    }

    public String getDescription() {
        return this.getEvent().getDescription();
    }

    public void setDescription(String description){
        this.getEvent().setDescription(description);
    }

    public String getImage() {
        return this.getEvent().getImage();
    }

    public void setImage(String image){
        this.getEvent().setName(image);
    }

    public String editEvent(Event event){
        this.setEvent(event);
        return "success";
    }

    public String newEvent(){
        this.setEvent(new Event());
        return "success";
    }

    public String updateEvent(){
        service.updateEvent(this.getEvent(), this.getUser(), this.getEventStatus());
        return "success";
    }

    public String cancelEvent(Event event){
        service.cancelEvent(event, this.getUser(), this.getEventStatus());
        return "success";
    }

    public String createEvent(){
        this.setUser((User) SessionUtils.getUser());
        service.createEvent(this.getUser(), this.getEventStatus(), this.getName(), this.getDescription(), this.getImage(), this.getDate());
        return "success";
    }

    public String deleteEvent(Event event){
        service.deleteEvent(event.getId());
        return "success";
    }

    public String getDate() {
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = "";
        try{
            date = formatter.format(this.getEvent().getDate());
        }catch (Exception e) {
            date = "";
        }
        return date;
    }

    public void setDate(String date){
        this.getEvent().setDate(date);
    }

    public void validateImage(FacesContext ctx, UIComponent comp, Object value){

    }

    public String getPathBaseImage(){
        Path folder = Paths.get("/web/uploads");
        return folder.toString();
    }

    public List<Event> getEventsPendings() {
        this.setUser((User) SessionUtils.getUser());
        return service.findAllEventsPendings(getUser());
    }

    public List<Event> getMyEvents() {
        this.setUser((User) SessionUtils.getUser());
        return service.findAllByUserApplicants(getUser());
    }
}