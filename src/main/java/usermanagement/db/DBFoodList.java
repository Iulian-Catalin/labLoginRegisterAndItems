package usermanagement.db;

import usermanagement.MyFoodList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBFoodList {

    public boolean newFood(MyFoodList u) {

        System.out.println(u);

        boolean isInserted=false;
        try {
            // 1. ma conectez la db
            final String URL = "jdbc:postgresql://idc.cluster-custom-cjcsijnttbb2.eu-central-1.rds.amazonaws.com:5432/ionelcondor";
            final String USERNAME = "ftuser";

            final String PASSWORD = System.getenv("PWDDB");


            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. creez un prepared ststement si il populez cu date
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO myfoodlist (foodname,fooddate, iduser) VALUES(?,?, ?)");
            pSt.setString(1,u.getFoodName());

            Date date = Date.valueOf(u.getFoodDate());
            pSt.setDate(2, date);

            pSt.setInt(3, u.getIduser());


            // 3. executie
            int insert = pSt.executeUpdate();
            if(insert!=-1)
                isInserted=true;
            System.out.println(isInserted);

            pSt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            isInserted=false;

        }


        return isInserted;
    }

    public List<MyFoodList> getFoodList (int idUser, String search) {

        MyFoodList mfl =null;
        List<MyFoodList> list = new ArrayList<>();
        // 1. ma conectez la db
        final String URL = "jdbc:postgresql://idc.cluster-custom-cjcsijnttbb2.eu-central-1.rds.amazonaws.com:5432/ionelcondor";
        final String USERNAME = "ftuser";
        final String PASSWORD = System.getenv("PWDDB");
        int id =-1;
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. fac un query pe o tabela , intai creez obiectul



            PreparedStatement pSt = conn.prepareStatement("select * from myfoodlist where iduser=? and foodname like CONCAT( '%',?,'%') ORDER BY fooddate desc");


            pSt.setInt(1, idUser);
            pSt.setString(2, search);


            // 3. executie
            ResultSet rs = pSt.executeQuery();




            // atata timp cat am randuri
            while (rs.next()) {

                mfl = new MyFoodList();
                mfl.setId(rs.getInt("id"));
                mfl.setFoodName(rs.getString("foodname"));

                Date dateFromDB = rs.getDate("fooddate");
                LocalDate localDate = dateFromDB.toLocalDate();
                mfl.setFoodDate(localDate);


                list.add(mfl);

            }

            rs.close();
            pSt.close();
            conn.close();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return list;
    }


    public static void main(String[] args) {

        DBFoodList db = new DBFoodList();

//       MyFoodList m0 = new MyFoodList("pizza", LocalDate.now(), 48 );
//
//        MyFoodList m1 = new MyFoodList("cartofi cu ceapa ", LocalDate.now(), 48 );
//        MyFoodList m2 = new MyFoodList("peste cu morcovi", LocalDate.now(), 48 );
//        MyFoodList m3 = new MyFoodList("inghetata de vanilie", LocalDate.now(), 48 );
//        MyFoodList m4 = new MyFoodList("fructe de mare", LocalDate.now(), 48 );
//
//
//        db.newFood(m0);
//        db.newFood(m1);
//        db.newFood(m2);
//        db.newFood(m3);
//        db.newFood(m4);

        List<MyFoodList> l = db.getFoodList(48,"");

        for(int i = 0;i<l.size();i++) {

            MyFoodList mfl = (MyFoodList) l.get(i);

            System.out.println(mfl.toString()); // just to test we get the right data from db
        }




    }
}
