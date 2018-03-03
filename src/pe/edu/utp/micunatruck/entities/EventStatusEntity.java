package pe.edu.utp.micunatruck.entities;

import pe.edu.utp.micunatruck.models.EventStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventStatusEntity extends BaseEntity {
    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.event_status";
    private List<EventStatus> findByCriteria(String sql) {
        List<EventStatus> eventStatus;
        if(getConnection() != null) {
            eventStatus = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
                while (resultSet.next()) {
                    EventStatus region = new EventStatus()
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"));
                    eventStatus.add(region);
                }
                return eventStatus;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<EventStatus> findAll() {
        return findByCriteria(DEFAULT_SQL);
    }

    public EventStatus findById(int id) {
        List<EventStatus> eventSatus = findByCriteria(DEFAULT_SQL +
                " WHERE id = "+ String.valueOf(id));
        return (eventSatus != null && !eventSatus.isEmpty() ? eventSatus.get(0) : null);
    }

    public EventStatus findByName(String name) {
        List<EventStatus> eventSatus = findByCriteria(DEFAULT_SQL +
                " WHERE name = '" + name + "'");
        return (eventSatus != null && !eventSatus.isEmpty() ? eventSatus.get(0) : null);
    }

    private int updateByCriteria(String sql) {
        if(getConnection() != null) {
            try {
                return getConnection()
                        .createStatement()
                        .executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public boolean delete(int id) {
        return updateByCriteria("DELETE FROM event_status WHERE id = " + String.valueOf(id)) > 0;
    }

    private int getMaxId() {
        String sql = "SELECT MAX(id) AS max_id FROM event_status";
        if(getConnection() != null) {
            try {
                ResultSet resultSet = getConnection()
                        .createStatement()
                        .executeQuery(sql);
                return resultSet.next() ?
                        resultSet.getInt("max_id") : 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return 0;
    }

    public EventStatus create(String name) {
        if(findByName(name) == null) {
            if(getConnection() != null) {
                String sql = "INSERT INTO event_status(id, name) VALUES(" +
                        String.valueOf(getMaxId() + 1) + ", '" +
                        name + "')";
                int results = updateByCriteria(sql);
                if(results > 0) {
                    EventStatus region = new EventStatus(getMaxId(), name);
                    return region;
                }
            }
        }
        return null;
    }

    public boolean update(EventStatus region) {
        if(findByName(region.getName()) != null) return false;
        return updateByCriteria(
                "UPDATE event_status SET name = '" +
                        region.getName() + "'" +
                        " WHERE id = " +
                        String.valueOf(region.getId())) > 0;
    }
}