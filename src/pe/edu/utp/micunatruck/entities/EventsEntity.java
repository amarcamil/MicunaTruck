package pe.edu.utp.micunatruck.entities;

import pe.edu.utp.micunatruck.models.Applicant;
import pe.edu.utp.micunatruck.models.Event;
import pe.edu.utp.micunatruck.models.EventStatus;
import pe.edu.utp.micunatruck.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsEntity extends BaseEntity {
    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.events";

    private List<Event> findByCriteria(String sql, UsersEntity usersEntity, EventStatusEntity eventStatusEntity){
        List<Event> events;
        if(hasConnection()){
            events = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement()
                        .executeQuery(sql);
                while (resultSet.next()){

                    Event event = new Event(resultSet.getInt("id"),
                            usersEntity.findById(resultSet.getInt("user_id")),
                            eventStatusEntity.findById(resultSet.getInt("event_status_id")),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getString("image"),
                            resultSet.getByte("flag_active"),
                            resultSet.getString("date")
                    );
                    events.add(event);
                }
                return events;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Event> findAll(EventStatusEntity eventStatusEntity){
        return findByCriteria(DEFAULT_SQL, new UsersEntity(), eventStatusEntity);
    }

    public List<Event> findAllByUser(User user, EventStatusEntity eventStatusEntity){
        String sql = DEFAULT_SQL + " WHERE user_id = " + String.valueOf(user.getId()) + " AND deleted_at IS NULL";
        return findByCriteria(sql, new UsersEntity(), eventStatusEntity);
    }

    public Event findById(int id){
        List<Event> events = findByCriteria(DEFAULT_SQL + " WHERE id = " +
                String.valueOf(id), new UsersEntity(), new EventStatusEntity());
        return (events) != null ? events.get(0) : null;
    }

    public Event findByName(String name){
        List<Event> events = findByCriteria(DEFAULT_SQL+ " WHERE name like '%" + name + "%'", new UsersEntity(), new EventStatusEntity());
        return (events != null && !events.isEmpty()) ? events.get(0) : null;
    }

    public List<Event> findAllPendings(User user, EventStatusEntity eventStatusEntity,
                                       ApplicantsEntity applicantsEntity,
                                       UsersEntity usersEntity,
                                       EventsEntity eventsEntity){
        String sql = DEFAULT_SQL + " WHERE flag_active = 1 AND event_status_id = 1 AND deleted_at IS NULL";

        List<Event> events = findByCriteria(sql, usersEntity, eventStatusEntity);

        for (Event event : events) {
            List<Applicant> applicants = applicantsEntity.findByUserAndEvent(user, event, usersEntity, eventsEntity);
            if (applicants != null)
                event.setIsSendRequest(true);
        }

        return events;
    }

    public List<Event> findAllByUserApplicants(User user, ApplicantsEntity applicantsEntity,
                                               UsersEntity usersEntity, EventsEntity eventsEntity, EventStatusEntity eventStatusEntity){
        List<Applicant> applicants = applicantsEntity.findByUser(user, usersEntity, eventsEntity);
        List<Event> filter = null;
        if(applicants != null) {
            List<Event> all = findAll(eventStatusEntity);
            filter = new ArrayList<>();
            for (Event event : all) {
                boolean add = false;
                for (Applicant applicant : applicants) {
                    if (event.getId() == applicant.getEvent().getId())
                        add = true;
                }

                if (add)
                    filter.add(event);
            }
        }
        return  filter;
    }

    private int getMaxId(){
        String sql = "SELECT MAX(id) AS max_id FROM events";
        if(hasConnection()){
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
                return resultSet.next() ? resultSet.getInt("max_id") : 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private int updateByCriteria(String sql){
        if(hasConnection()){
            try {
                return getConnection().createStatement().executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public Event create(User user, EventStatus eventStatus, String name, String description, String image, String date){
        if(hasConnection()){
            Date createdAt = new Date();
            Date dateDb  = null;
            try {
                dateDb = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String sql = "INSERT INTO events(user_id, event_status_id, name, description, image, date, flag_active, updated_at) " +
                    "VALUES("+user.getId()+","+1+",'"+name+"','"+description+"','"+image+"','"+(new Timestamp(dateDb.getTime())).toString()+"',1,'"+(new Timestamp(createdAt.getTime())).toString()+"')";
            int results = updateByCriteria(sql);
            if(results > 0){
                return new Event(getMaxId(), user, eventStatus, name, description, image, 1, date);
            }
        }
        return null;
    }

    public boolean delete(int id){
        return updateByCriteria("DELETE FROM events WHERE id = " +  String.valueOf(id)) > 0;
    }

    public boolean delete(String name){
        return updateByCriteria("DELETE FROM events WHERE name = " +  name) > 0;
    }

    public boolean update(Event event, User user, EventStatus eventStatus){
        return updateByCriteria("UPDATE events SET " +
                "name = '"+event.getName()+ "', " +
                "date = '"+(new Timestamp(event.getDate().getTime())).toString()+ "', " +
                "description ='"+String.valueOf(event.getDescription())+"', image='"+String.valueOf(event.getImage())+"' WHERE id = " + String.valueOf(event.getId())) > 0;
    }

    public boolean cancel(Event event, User user, EventStatus eventStatus){
        return updateByCriteria("UPDATE events SET " +
                "event_status_id = "+3+" WHERE id = " + String.valueOf(event.getId())) > 0;
    }
}