package pe.edu.utp.micunatruck.entities;

import pe.edu.utp.micunatruck.models.Finance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinancesEntity extends BaseEntity {
    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.incomes";

    private List<Finance> findByCriteria(String sql) {
        List<Finance> Finances;
        if (hasConnection()) {
            Finances = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement()
                        .executeQuery(sql);
                while (resultSet.next()) {
//                    Finance region = new Finance()
//                            .setId(resultSet.getInt("id"))
//                            .setOriginId(resultSet.getInt("originId"))
//                            .setOriginTypeId(resultSet.getInt("originId"))
//                            .setAmount(resultSet.getDouble("amount"))
//                            .setStartDate(resultSet.getDate("start_date"))
//                            .setEndDate(resultSet.getDate("end_date"))
//                            .setFlagActive(resultSet.getBoolean("flag_active"));//comentario
//                    Finances.add(region);
                }
                return Finances;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Finance> findAll() {
        return findByCriteria(DEFAULT_SQL);
    }

    public Finance findById(int id) {
        List<Finance> finances = findByCriteria(DEFAULT_SQL + " WHERE id = " +
                String.valueOf(id));
        return (finances) != null ? finances.get(0) : null;
    }

    public Finance findByName(String name) {
        List<Finance> incomes = findByCriteria(DEFAULT_SQL + " WHERE name like '%" + name + "%'");
        return (incomes != null) ? incomes.get(0) : null;
    }

    private int getMaxId() {
        String sql = "SELECT MAX(id) AS max_id FROM income";
        if (hasConnection()) {
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
                return resultSet.next() ? resultSet.getInt("max_id") : 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private int updateByCriteria(String sql) {
        if (hasConnection()) {
            try {
                return getConnection().createStatement().executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public Finance findInfoDuplicate(int id, Date startDate, Date endDate, int action) {
        if (action == 0) {
            //INSERT
            List<Finance> adsRs = findByCriteria(DEFAULT_SQL + "WHERE start_date>='" + startDate + "' AND end_date<='" + endDate + "' AND flag_active=1");
            return (adsRs != null ? adsRs.get(0) : null);
        } else if (action == 1) {
            //UPDATE
            List<Finance> adsRs = findByCriteria(DEFAULT_SQL + "WHERE start_date='" + startDate + "' AND end_date='" + endDate + "' AND id<>" + id + " AND flag_active=1");
            return (adsRs != null ? adsRs.get(0) : null);
        }
        return null;
    }

    public Finance create(int originId, int originTypeId, double amount, Date startDate, Date endDate, boolean flagActive) {

        if (findInfoDuplicate(0, startDate, endDate, 0) == null) {
            if (hasConnection()) {
                int id = getMaxId() + 1;
                String sql = "INSERT INTO incomes(id,origin_id,origin_type_id,amount,start_date,end_date,flag_active,created_at)" +
                        "values(" + id + "," + originId + ",'" + originTypeId + "','" + amount + "','" + startDate + "'," + endDate + ",1,NOW())";

                int results = updateByCriteria(sql);
                if (results > 0) {
                    return new Finance(id, originId, originTypeId, amount, startDate,
                            endDate, flagActive);
                }
            }

        }
        return null;
    }

    public boolean delete(int id) {
        return updateByCriteria("DELETE FROM events WHERE id = " + String.valueOf(id)) > 0;
    }
}