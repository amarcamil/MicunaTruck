package pe.edu.utp.micunatruck.beans;

import pe.edu.utp.micunatruck.models.Applicant;
import pe.edu.utp.micunatruck.models.Event;
import pe.edu.utp.micunatruck.models.User;
import pe.edu.utp.micunatruck.services.MicunaTruckService;
import pe.edu.utp.micunatruck.utils.SessionUtils;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AplicantsBean implements Serializable {
    private MicunaTruckService micunaTruckService;
    private Applicant applicant;
    private User user;
    private Event event;

    public AplicantsBean(){
        micunaTruckService = new MicunaTruckService();
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getId(){
        return this.getApplicant().getId();
    }

    public void setId(int id){
        this.getApplicant().setId(id);
    }

    public int getEventId(){
        return this.getEvent().getId();
    }

    public void setEventId(int id){
        this.getEvent().setId(id);
    }

    public String newApplicant(Event event){
        this.setUser((User) SessionUtils.getUser());
        this.setApplicant(micunaTruckService.createApplicant(getUser(), event, false));
        return "success";
    }

    public String cancelApplicant(Event event) {
        this.setUser((User) SessionUtils.getUser());
        boolean dd = micunaTruckService.cancelApplicant(getUser(), event);
        return "success";
    }

    public String applicantsByEvent(Event event) {
        this.setEvent(event);
        return "success";
    }

    public List<Applicant> getAllApplicantsByEvent() {
        return micunaTruckService.findAllApplicantsByEvent(this.getEvent());
    }

    public String acceptEvent(Applicant applicant){
        micunaTruckService.acceptApplicantByEvent(applicant);
        return "success";
    }
}