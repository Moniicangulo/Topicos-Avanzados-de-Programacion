package Unidad2;

// CONECCIONA LA BASE DE DATOS :D

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConeccionBD {

    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Northwind;encrypt=true;trustServerCertificate=true";

        try {
            Connection conec = ConeccionSingleton.getConnection();
            Statement state = conec.createStatement();
            String bd = "SELECT * FROM EMPLOYEES";

           ResultSet res = state.executeQuery(bd);
           while (res.next()){
               System.out.print(res.getInt("EmployeeID") + "\t");
               System.out.println(res.getString("FirstName") );
           }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("FELICIDADES TE CONECTASTE A UNA BD");

        }



}
