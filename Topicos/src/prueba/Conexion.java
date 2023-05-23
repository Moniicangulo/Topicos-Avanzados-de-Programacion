package prueba;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class conexion {
    private String namebd;
    private String userbd;
    private String password;
    private Statement stmt;
    private Connection con;

    public conexion(String name, String user, String password) {
        namebd = name;
        userbd = user;
        this.password = password;
        try {
            conecta();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void conecta() throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try {
            DriverManager.setLoginTimeout(5000);
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://25.2.141.212;encrypt=true;databaseName=" + namebd
                            + ";TrustServerCertificate=true;",
                    userbd, password);
            this.con = con;
            stmt = con.createStatement();
            System.out.println("conectado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public Statement getStmt() {
        return stmt;
    }

    public Connection getCon() {
        return con;
    }
}
