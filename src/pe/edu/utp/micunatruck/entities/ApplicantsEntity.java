package pe.edu.utp.micunatruck.entities;

import pe.edu.utp.micunatruck.models.Applicant;
import pe.edu.utp.micunatruck.models.Event;
import pe.edu.utp.micunatruck.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicantsEntity extends BaseEntity {

    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.applicants ";
    private static String DEFAULT_SQL_UPDATE = "UPDATE micunatruck.applicants SET ";

    private List<Applicant> findByCriteria(String sql, UsersEntity usersEntity, EventsEntity eventsEntity)
    {
        List<Applicant> applicants = null;
        boolean indHasData = false;
        if(hasConnection()){
            applicants = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);

                while (resultSet.next()){
                    indHasData = true;
                    Applicant applicant = new Applicant()
                            .setId(resultSet.getInt("id"))
                            .setUser(usersEntity.findById(resultSet.getInt("user_id")))
                            .setEvent(eventsEntity.findById(resultSet.getInt("event_id")))
                            .setFlagActive(resultSet.getBoolean("flag_active"))
                            .setDeleteAt(resultSet.getDate("deleted_at"))
                            ;
                    applicants.add(applicant);
                }

                if(!indHasData)
                    applicants = null;

                return applicants;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Applicant> findAll(UsersEntity usersEntity, EventsEntity eventsEntity){
        return findByCriteria(DEFAULT_SQL + "WHERE flag_active = 1", usersEntity, eventsEntity);
    }

    public Applicant findById(int id, UsersEntity usersEntity, EventsEntity eventsEntity){
        List<Applicant> applicants = findByCriteria(DEFAULT_SQL + " WHERE flag_active = 1 AND id = " + String.valueOf(id),
                usersEntity,
                eventsEntity);
        return (applicants) != null ? applicants.get(0) : null;
    }

    public List<Applicant> findByUser(User user, UsersEntity usersEntity, EventsEntity eventsEntity){
        String sql = DEFAULT_SQL + " WHERE user_id = " + String.valueOf(user.getId()) + " AND deleted_at IS NULL";
        return findByCriteria(sql, usersEntity, eventsEntity);
    }

    public List<Applicant> findByUserAndEvent(User user, Event event, UsersEntity usersEntity, EventsEntity eventsEntity){
        String sql = DEFAULT_SQL + " WHERE user_id = " + String.valueOf(user.getId())
                + " AND event_id = " + String.valueOf(event.getId())
                + " AND deleted_at IS NULL";
        return findByCriteria(sql, usersEntity, eventsEntity);
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

    public Applicant create(User user, Event event, boolean flagActive){
        //if(findByName(name) == null){
        if(hasConnection()){
            String sql =
                    "INSERT INTO micunatruck.applicants(" +
                            "user_id, event_id, flag_active, created_at) " +
                            "VALUES("
                            + String.valueOf(user.getId())
                            + "," + String.valueOf(event.getId())
                            + "," +  String.valueOf(flagActive ? 1 : 0)
                            + ", NOW()"
                            + ")";
            int results = updateByCriteria(sql);
            if(results > 0){
                Applicant applicant = new Applicant(getMaxId(), user, event);
                return applicant;
            }
        }
        //}
        return null;
    }

    public boolean cancel(Event event, User user){
        return updateByCriteria(DEFAULT_SQL_UPDATE
                + " deleted_at = NOW() "
                + "WHERE user_id = " +  String.valueOf(user.getId())
                + " AND event_id = " + String.valueOf(event.getId()) ) > 0;
    }

    private int getMaxId(){
        String sql = "SELECT MAX(id) AS max_id FROM micunatruck.applicants";
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

    public List<Applicant> findAllByEvent(Event event, UsersEntity usersEntity, EventsEntity eventsEntity){
        String sql = DEFAULT_SQL + " WHERE event_id = " + String.valueOf(event.getId()) + " AND deleted_at IS NULL";
        return findByCriteria(sql, usersEntity, eventsEntity);
    }

    public boolean accept(Applicant applicant){
        return updateByCriteria(DEFAULT_SQL_UPDATE
                + " flag_active = 1"
                + "WHERE id " + String.valueOf(applicant.getId()) ) > 0;
    }
}