package pe.edu.utp.micunatruck.entities;

import pe.edu.utp.micunatruck.models.Ads;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdsEntity extends BaseEntity {
    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.ads ";
    /*private static String DEFAULT_SQL_UPDATE = "UPDATE micunatruck.ads SET ";*/

    private List<Ads> findByCriteria(String sql, AdminsEntity adminsEntity){
        List<Ads> adsRs;
        if(getConnection() != null){
            adsRs = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement()
                        .executeQuery(sql);
                while (resultSet.next()){
                    Ads ads = new Ads(
                            resultSet.getInt("id"),
                            adminsEntity.findById(resultSet.getInt("admin_id")),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getString("image"),
                            resultSet.getDouble("price"),
                            resultSet.getDate("start_date"),
                            resultSet.getDate("end_date"),
                            resultSet.getInt("flag_active"),
                            resultSet.getDate("deleted_at"),
                            resultSet.getDate("updated_at"),
                            resultSet.getDate("created_at"),
                            resultSet.getInt("code_space"));
                    adsRs.add(ads);
                }
                return adsRs;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Ads> findAll(AdminsEntity adminsEntity){
        return findByCriteria(DEFAULT_SQL,adminsEntity);
    }

    public Ads findById(int id,AdminsEntity adminsEntity){
        List<Ads> adsRs=findByCriteria(DEFAULT_SQL + "WHERE id="+String.valueOf(id),adminsEntity);
        return (adsRs!=null && !adsRs.isEmpty() ?adsRs.get(0):null);
    }

    public Ads findInfoDuplicate(int id, Date start_date, Date end_date, int code_space, int action, AdminsEntity adminsEntity){
        if (action==0){
            //INSERT
            List<Ads> adsRs=findByCriteria(DEFAULT_SQL+"WHERE code_space="+code_space+" AND (start_date>='"+start_date+"' AND end_date<='"+end_date+"') AND flag_active=1",adminsEntity);
            return (adsRs!=null?adsRs.get(0):null);
        }else if (action==1){
            //UPDATE
            List<Ads> adsRs=findByCriteria(DEFAULT_SQL+"WHERE code_space="+code_space+" AND (start_date='"+start_date+"' AND end_date='"+end_date+"') AND id<>"+id+" AND flag_active=1",adminsEntity);
            return (adsRs!=null?adsRs.get(0):null);
        }
        return null;
    }

    private int updateByCriteria(String sql){
        if (getConnection()!=null){
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

    private int getMaxId(){
        if (getConnection()!=null){
            String sql="SELECT MAX(id) FROM ads";
            try {
                ResultSet rs=getConnection()
                        .createStatement()
                        .executeQuery(sql);
                return rs.next()?rs.getInt("id"):0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public Ads findBySpace(int code_space,AdminsEntity adminsEntity){
        List<Ads> adsRs=findByCriteria(DEFAULT_SQL+"WHERE code_space="+code_space+" AND " +
                "flag_active=1 AND (start_date>=NOW() AND end_date<=NOW())",adminsEntity);
        return (adsRs!=null?adsRs.get(0):null);
    }

    public Ads create(int idadmin,String name,String description,
                      String image,Double price,Date start_date,Date end_date,
                      int code_space, AdminsEntity adminsEntity){

        if (findInfoDuplicate(0,start_date,end_date,code_space,0,adminsEntity)==null){
            if (getConnection()!=null){
                int id=getMaxId()+1;
                String sql="INSERT INTO ads(id,admin_id,name,description,image,price,flag_active,start_date,end_date,created_at,code_space)" +
                        "values("+id+","+idadmin+",'"+name+"','"+description+"','"+image+"',"+price+",1,'"+start_date+"','"+end_date+"',NOW(),"+code_space+")";

                int results=updateByCriteria(sql);
                if (results>0){
                    return new Ads(id,adminsEntity.findById(idadmin),name,description,image,price,
                            start_date,end_date,1,findById(id,adminsEntity).getCreatedAt(),code_space);
                }
            }

        }
        return null;
    }

    public boolean update(int id,String name,String description,
                          String image,Double price,Date start_date, Date end_date,
                          int flag_active,int code_space,AdminsEntity adminsEntity){
        if (findInfoDuplicate(id,start_date,end_date,code_space,1,adminsEntity)==null){
            if (getConnection()!=null){
                String sql="UPDATE ads SET name='"+name+"',description='"+description+"',image='"+image+"',price="+price+"," +
                        "start_date='"+start_date+"',end_date='"+end_date+"' flag_active="+ flag_active +","+
                        "updated_at=NOW(),code_space="+code_space+" WHERE id="+id;
                return updateByCriteria(sql)>0;
            }
        }
        return false;
    }

    public boolean delete(int id){
        String sql="DELETE FROM ads WHERE id="+id ;
        return updateByCriteria(sql)>0;
    }
}