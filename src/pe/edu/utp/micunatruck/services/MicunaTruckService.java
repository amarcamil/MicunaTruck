package pe.edu.utp.micunatruck.services;

import pe.edu.utp.micunatruck.entities.*;
import pe.edu.utp.micunatruck.models.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MicunaTruckService {
    private Connection connection;

    private UsersEntity usersEntity;
    private EventsEntity eventsEntity;
    private UserTypeEntity userTypeEntity;
    private EventStatusEntity eventStatusEntity;
    private AdsEntity adsEntity;
    private AdminsEntity adminsEntity;
    private ApplicantsEntity applicantsEntity;

    private Connection getConnection() {
        if (connection==null){
            try {
                connection = ((DataSource)InitialContext.doLookup("jdbc/MySQLDataSourceMicunaTruck"))
                        .getConnection();
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /* USER TYPE */
    public UserTypeEntity getUsersTypeEntity() {
        if(getConnection() != null){
            if(userTypeEntity == null){
                userTypeEntity = new UserTypeEntity();
                userTypeEntity.setConnection(getConnection());
            }
        }
        return userTypeEntity;
    }

    public List<UserType> findAllUsersType() {
        return getUsersTypeEntity() != null ? getUsersTypeEntity().findAll() : null;
    }

    public UserType findUserTypeById(int id){
        return getUsersTypeEntity() != null ? getUsersTypeEntity().findById(id) : null;
    }

    /* USERS */
    public UsersEntity getUsersEntity() {
        if(getConnection() != null){
            if(usersEntity == null){
                usersEntity = new UsersEntity();
                usersEntity.setConnection(getConnection());
            }
        }
        return usersEntity;
    }

    public List<User> findAllUsers() {
        return getUsersEntity() != null ? getUsersEntity().findAll() : null;
    }

    public User findUserById(int id){
        return getUsersEntity() != null ? getUsersEntity().findById(id) : null;
    }

    public User findUserByEmailAndPassword(String email, String password){
        return getUsersEntity() != null ? getUsersEntity().findUserByEmailAndPassword(email, password, getUsersTypeEntity()) : null;
    }

    public User createUser(UserType userType, String name, String lastName, String legalName,
                           String description, String photo, String address, String telephone,
                           String email, String password, boolean flagActive){
        return getUsersEntity() != null ? getUsersEntity().create(userType, name, lastName, legalName,
                description, photo, address, telephone,
                email, password, flagActive) : null;
    }

    public boolean deleteUser(int id){
        return getUsersEntity() != null ? getUsersEntity().delete(id) : false;
    }

    public boolean updateUser(User user, UserType userType){
        return getUsersEntity() != null ? getUsersEntity().update(user, userType) : false;
    }

    /* EVENT STATUS */
    public EventStatusEntity getEventStatusEntity() {
        if(getConnection() != null){
            if(eventStatusEntity == null){
                eventStatusEntity = new EventStatusEntity();
                eventStatusEntity.setConnection(getConnection());
            }
        }
        return eventStatusEntity;
    }

    public List<EventStatus> findAllEventStatus() {
        return getEventStatusEntity() != null ? getEventStatusEntity().findAll() : null;
    }

    /* EVENTS */
    public EventsEntity getEventsEntity() {
        if(getConnection() != null){
            if(eventsEntity == null){
                eventsEntity = new EventsEntity();
                eventsEntity.setConnection(getConnection());
            }
        }
        return eventsEntity;
    }

    public List<Event> findAllEvents() {
        return getEventsEntity() != null ? getEventsEntity().findAll(getEventStatusEntity()) : null;
    }

    public List<Event> findAllEventsByUser(User user) {
        return getEventsEntity() != null ? getEventsEntity().findAllByUser(user, getEventStatusEntity()) : null;
    }

    public List<Event> findAllEventsPendings(User user) {
        return getEventsEntity() != null ? getEventsEntity().findAllPendings(user,
                getEventStatusEntity(),
                getApplicantsEntity(),
                getUsersEntity(),
                getEventsEntity()) : null;
    }

    public List<Event> findAllByUserApplicants(User user){
        return getEventsEntity() != null ? getEventsEntity().findAllByUserApplicants(user,
                getApplicantsEntity(),
                getUsersEntity(),
                getEventsEntity(),
                getEventStatusEntity()) : null;
    }

    public Event createEvent(User user, EventStatus eventStatus, String name, String description, String image, String date) {
        return getEventsEntity() != null ?
                getEventsEntity().create(user, eventStatus, name, description, image, date) : null;
    }

    public boolean deleteEvent(int id) {
        return getEventsEntity() != null ?
                getEventsEntity().delete(id) : false;
    }

    public boolean updateEvent(Event event, User user, EventStatus eventStatus) {
        return getEventsEntity() != null ?
                getEventsEntity().update(event, user, eventStatus) : false;
    }

    public boolean cancelEvent(Event event, User user, EventStatus eventStatus) {
        return getEventsEntity() != null ?
                getEventsEntity().cancel(event, user, eventStatus) : false;
    }

    /*ADS*/
    protected AdsEntity getAdsEntity(){
        if (getConnection()!=null){
            if (adsEntity==null){
                adsEntity=new AdsEntity();
                adsEntity.setConnection(getConnection());
            }
        }
        return adsEntity;
    }

    public List<Ads> findAllAds(){
        return getAdsEntity()!=null?
                getAdsEntity().findAll(getAdminsEntity()):null;
    }

    public Ads findAdsById(int id){
        return getAdsEntity()!=null?getAdsEntity().findById(id,getAdminsEntity()):null;
    }

    public Ads findAdsBySpace(int idspace){
        return getAdsEntity()!=null?getAdsEntity().findBySpace(idspace,getAdminsEntity()):null;
    }

    public Ads createAds(int idadmin, String name, String description, String image, Double price,
                         Date start_date, Date end_date, int idspace){
        return getAdsEntity()!=null?getAdsEntity().create(idadmin,name,description,image,price,start_date,end_date,
                idspace,getAdminsEntity()):null;
    }

    public boolean updateAds(int id, String name, String description, String image, Double price,
                             Date start_date, Date end_date, int flag_active, int idspace){
        return getAdsEntity()!=null?getAdsEntity().update(id,name,description,image,price,start_date,end_date,flag_active,idspace,getAdminsEntity()):false;
    }

    public boolean deleteAds(int id){
        return getAdsEntity()!=null?getAdsEntity().delete(id):false;
    }

    /* ADMIN */
    private AdminsEntity getAdminsEntity(){
        if (getConnection()!=null){
            if (adminsEntity==null){
                adminsEntity=new AdminsEntity();
                adminsEntity.setConnection(getConnection());
            }
        }
        return adminsEntity;
    }

    public Admin createAdmin(String name, String lastName, String photo, String email, String password, boolean flagActive) {
        return getAdminsEntity() != null ?
                getAdminsEntity().create(name,lastName,photo,email,password,flagActive) : null;
    }

    public boolean updateAdmin( Admin admin){
        return getAdminsEntity()!= null ?
                getAdminsEntity().update(admin):false;
    }

    /* APPLICANTS */
    public ApplicantsEntity getApplicantsEntity() {
        if(getConnection() != null){
            if(applicantsEntity == null){
                applicantsEntity = new ApplicantsEntity();
                applicantsEntity.setConnection(getConnection());
            }
        }
        return applicantsEntity;
    }

    public List<Applicant> findAllApplicantsByEvent(Event event) {
        return getApplicantsEntity() != null ? getApplicantsEntity().findAllByEvent(event, getUsersEntity(), getEventsEntity()) : null;
    }

    public Applicant createApplicant(User user, Event event, boolean flagActive){
        return getApplicantsEntity() != null ? getApplicantsEntity().create(user, event, flagActive) : null;
    }

    public boolean cancelApplicant(User user, Event event){
        return getApplicantsEntity() != null ? getApplicantsEntity().cancel(event, user) : null;
    }

    public List<Applicant> findAllPostulants() {
        return getUsersEntity() != null ? getApplicantsEntity().findAll(getUsersEntity(), getEventsEntity()) : null;
    }

    public List<Applicant> findAllPostulantsByUser(User user) {
        return getUsersEntity() != null ? getApplicantsEntity().findByUser(user, getUsersEntity(), getEventsEntity()) : null;
    }

    public boolean acceptApplicantByEvent(Applicant applicant){
        return getApplicantsEntity() != null ? getApplicantsEntity().accept(applicant) : null;
    }
}