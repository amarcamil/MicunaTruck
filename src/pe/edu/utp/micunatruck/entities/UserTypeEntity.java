package pe.edu.utp.micunatruck.entities;

import pe.edu.utp.micunatruck.models.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTypeEntity extends BaseEntity {

    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.user_types ";

    private List<UserType> findByCriteria(String sql)
    {
        List<UserType> usersType = null;
        boolean indHasData = false;
        if(getConnection() != null)
        {
            usersType = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);

                while (resultSet.next()){
                    indHasData = true;
                    UserType userType = new UserType()
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setDescription(resultSet.getString("description"))
                            .setFlagActive(resultSet.getBoolean("flag_active"))
                            ;
                    usersType.add(userType);
                }

                if(!indHasData)
                    usersType = null;

                return usersType;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<UserType> findAll(){
        try{
            return findByCriteria(DEFAULT_SQL + "WHERE flag_active = 1");
        }catch (Exception ex){
            ex.printStackTrace();
            return  null;
        }
    }

    public UserType findById(int id){
        List<UserType> usersType = findByCriteria(DEFAULT_SQL + " WHERE flag_active = 1 AND id = " + String.valueOf(id));
        return (usersType) != null ? usersType.get(0) : null;
    }

    public UserType findByName(String name){
        List<UserType> usersType = findByCriteria(DEFAULT_SQL + " WHERE flag_active = 1 AND name LIKE '%" + name + "%'");
        return (usersType != null) ? usersType.get(0) : null;
    }
}