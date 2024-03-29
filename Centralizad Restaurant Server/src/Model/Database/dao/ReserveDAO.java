package Model.Database.dao;

import Model.Database.BBDDHelper;
import Model.Database.Entity.Reserve;

import java.sql.Date;
import java.util.Calendar;

/**
 * Reserve class.
 */

public class ReserveDAO {

    public ReserveDAO() {
    }

    /**
     * Add a booking Table
     * @param reserve Reserve
     * @param id Id
     */
    public void addBookingTable(Reserve reserve, int id){
        // 1) create a java calendar instance
        Calendar calendar = Calendar.getInstance();

        // 2) get a java.util.Date from the calendar instance.
        //    this date will represent the current instant, or "now".
        java.util.Date date = calendar.getTime();

        Object param = new java.sql.Timestamp(date.getTime());
        String query = "INSERT INTO customerbooktable VALUES " +
                "('"+reserve.getUser().getUser()+"',"+id+",'"+param+"','"+reserve.getReserveName()+"');";
        System.out.println(query);
        BBDDHelper.getInstance().insertData(query);
    }

    /**
     * Add a Dish to Reserve
     * @param id_d Dish
     * @param id_t Table
     * @param q quantity.
     */
    public void addDishToReserve(int id_d,int id_t,int q){
        BBDDHelper.getInstance().callAddDish(id_d,id_t,q);
    }
}
