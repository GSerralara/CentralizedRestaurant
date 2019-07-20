package Model.Database.dao;

import Model.Database.BBDDHelper;
import Model.Database.Entity.Ranking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Ranking Dao class.
 */
public class RankingDAO {
    public RankingDAO(){

    }

    /**
     * Getter to 5.
     * @return a list.
     */

    public LinkedList<Ranking> getTop5(){
       String query = "SELECT d.name, SUM(t.quantity) AS num_dish \n" +
               "FROM TableOrderDish AS t,Dish AS d \n" +
               "WHERE d.id_dish = t.id_dish \n" +
               "GROUP BY t.id_dish \n" +
               "ORDER BY num_dish DESC \n" +
               "LIMIT 5;" ;
        ResultSet rs = BBDDHelper.getInstance().selectTable(query);
        LinkedList<Ranking> rankings = new LinkedList<>();
        try {
            while (rs.next()){
               rankings.add(new Ranking(rs.getString(1),rs.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rankings;
    }

    /**
     * Getter the Top 5
     * @return a list.
     */
    public LinkedList<Ranking> getTopActual5(){
        String query = "SELECT d.name, SUM(t.quantity) AS num_dish " +
                "FROM TableOrderDish AS t,Dish AS d " +
                "WHERE d.id_dish = t.id_dish AND t.cur_service = TRUE " +
                "GROUP BY t.id_dish " +
                "ORDER BY num_dish DESC LIMIT 5;";
        ResultSet rs = BBDDHelper.getInstance().selectTable(query);
        LinkedList<Ranking> rankings = new LinkedList<>();
        try {
            while (rs.next()){
                rankings.add(new Ranking(rs.getString(1),rs.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rankings;
    }
}
