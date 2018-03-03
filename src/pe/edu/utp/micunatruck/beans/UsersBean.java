package pe.edu.utp.micunatruck.beans;

import jdk.nashorn.internal.objects.annotations.Constructor;
import pe.edu.utp.micunatruck.models.User;
import pe.edu.utp.micunatruck.models.UserType;
import pe.edu.utp.micunatruck.services.MicunaTruckService;
import pe.edu.utp.micunatruck.utils.SessionUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UsersBean implements Serializable {
    @Inject
    private AuthsBean authsBean;

    private MicunaTruckService micunaTruckService;
    private User user;
    private List<UserType> userTypes;
    private UserType userType;

    @Constructor
    protected void userInit(){
        this.setUserType(new UserType());
        micunaTruckService = new MicunaTruckService();
    }

    @PostConstruct
    protected void init() {
        micunaTruckService = new MicunaTruckService();
    }

    public AuthsBean getAuthsBean() {
        return authsBean;
    }

    public void setAuthsBean(AuthsBean authsBean) {
        this.authsBean = authsBean;
    }

    public List<User> getUsers() {
        return micunaTruckService.findAllUsers();
    }

    public void setUserTypes(List<UserType> userTypes){
        this.userTypes = userTypes;
    }

    public List<UserType> getUserTypes(){
        return this.userTypes;
    }

    public void setUserType(UserType userType){
        this.userType = userType;
    }

    public UserType getUserType(){
        return this.userType;
    }

    public void setUserTypeSelected(int id){
        this.getUserType().setId(id);
    }

    public int getUserTypeSelected(){
        return this.getUserType().getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getName(){
        return this.getUser().getName();
    }

    public void setName(String name){
        this.getUser().setName(name);
    }

    public String getLastName(){
        return this.getUser().getLastName();
    }

    public void setLastName(String lastName){
        this.getUser().setLastName(lastName);
    }

    public String getLegalName(){
        return this.getUser().getLegalName();
    }

    public void setLegalName(String legalName){
        this.getUser().setLegalName(legalName);
    }

    public String getDescription(){
        return this.getUser().getDescription();
    }

    public void setDescription(String description){
        this.getUser().setDescription(description);
    }

    public String getPhoto(){
        return this.getUser().getPhoto();
    }

    public void setPhoto(String photo){
        this.getUser().setPhoto(photo);
    }

    public String getAddress(){
        return this.getUser().getAddress();
    }

    public void setAddress(String address){
        this.getUser().setAddress(address);
    }

    public String getTelephone(){
        return this.getUser().getTelephone();
    }

    public void setTelephone(String telephone){
        this.getUser().setTelephone(telephone);
    }

    public String getEmail(){
        return this.getUser().getEmail();
    }

    public void setEmail(String email){
        this.getUser().setEmail(email);
    }

    public String getPassword(){
        return this.getUser().getPassword();
    }

    public void setPassword(String password){
        this.getUser().setPassword(password);
    }

    public boolean getFlagActive(){
        return this.getUser().getFlagActive();
    }

    public void setFlagActive(boolean flagActive){
        this.getUser().setFlagActive(flagActive);
    }

    public String newUser(){
        this.setUserType(new UserType());
        this.setUserTypes(micunaTruckService.getUsersTypeEntity().findAll());
        this.setUser(new User());
        return "success";
    }

    public String editUser(User user){
        this.setUser(user);
        return "success";
    }

    public String createUser() {
        User user = micunaTruckService.createUser(this.getUserType(), this.getName(), this.getLastName(),
                this.getLegalName(),
                this.getDescription(), this.getPhoto(), this.getAddress(), this.getTelephone(),
                this.getEmail(), this.getPassword(), this.getFlagActive());

        this.getAuthsBean().setUser(user);

        HttpSession session = SessionUtils.getSession();
        session.setAttribute("username", user.getName());
        session.setAttribute("user", user);
        return "success";
    }

    public String updateUser() {
        micunaTruckService.updateUser(this.getUser(), this.getUser().getUserType());
        return "success";
    }

    public String deleteUser(User user) {
        micunaTruckService.deleteUser(user.getId());
        return "success";
    }

    /*public String indexUser() {
        return "success";
    }

    public String myPostulants() {
        return "success";
    }*/
}