package pe.edu.utp.micunatruck.entities;

import pe.edu.utp.micunatruck.models.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminsEntity extends BaseEntity {
    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.admins ";
    private static String DEFAULT_SQL_UPDATE = "UPDATE micunatruck.admins SET ";

    public Admin findById(int id){
        List<Admin> adsRs=findByCriteria(DEFAULT_SQL + "WHERE id="+String.valueOf(id));
        return (adsRs!=null?adsRs.get(0):null);
    }

    private List<Admin> findByCriteria(String sql)
    {
        List<Admin> ads = null;
        boolean indHasData = false;
        if(getConnection() != null){
            ads = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);

                while (resultSet.next()){
                    indHasData = true;
                    Admin region = new Admin()
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setLastName(resultSet.getString("lastname"))
                            .setPhoto(resultSet.getString("photo"))
                            .setEmail(resultSet.getString("email"))
                            .setPassword(resultSet.getString("password"))
                            .setFlagActive(resultSet.getBoolean("flag_active"))
                            ;
                    ads.add(region);
                }

                if(!indHasData)
                    ads = null;

                return ads;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean update(Admin admin){
        String sql = DEFAULT_SQL_UPDATE +
                "name = '" + admin.getName() + "', " +
                "lastname = '" + admin.getLastName() + "', " +
                "photo = '" + admin.getPhoto() + "', " +
                "email = '" + admin.getEmail() + "', " +
                "password = '" + admin.getPassword() + "', " +
                "flag_active = " + String.valueOf(admin.getFlagActive() ? 1 : 0)  + ", " +
                "updated_at = NOW() " +
                "WHERE id = " + String.valueOf(admin.getId());
        return updateByCriteria(sql) > 0;
    }

    private int updateByCriteria(String sql){
        if(getConnection() != null){
            try {
                return getConnection().createStatement().executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public Admin create(String name, String lastName, String photo, String email, String password , boolean flagActive){
        if(findByName(name) == null){
            if(getConnection() != null){
                String sql =
                        "INSERT INTO micunatruck.admins(" +
                                "name, lastname, photo, email, password, " +
                                "flag_active, created_at) " +
                                "VALUES("
                                + ",'" + name + "'"
                                + ",'" + lastName + "'"
                                + ",'" + photo + "'"
                                + ",'" + email + "'"
                                + ",'" + password + "'"
                                + "," +  String.valueOf(flagActive ? 1 : 0)
                                + ", NOW()"
                                + ")";
                int results = updateByCriteria(sql);
                if(results > 0){
                    Admin region = new Admin();
                    return region;
                }
            }
        }
        return null;
    }

    public Admin findByName(String name){
        List<Admin> admins = findByCriteria(DEFAULT_SQL + " WHERE name LIKE '%" + name + "%'");
        return (admins != null) ? admins.get(0) : null;
    }
}
