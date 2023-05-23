package Unidad2;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConeccionSingleton {
    static private Connection coneccion;

    private ConeccionSingleton(){
        String url = "jdbc:sqlserver://localhost:1433;databaseName=EMPRESA;encrypt=true;trustServerCertificate=true";

        try {
            coneccion = DriverManager.getConnection(url, "sa", "Monicanv9");

        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("FELICIDADES TE CONECTASTE A UNA BD");

    }

    public static Connection getConnection(){
        if(coneccion == null){
            new ConeccionSingleton();
        }
        return coneccion;
    }


}
