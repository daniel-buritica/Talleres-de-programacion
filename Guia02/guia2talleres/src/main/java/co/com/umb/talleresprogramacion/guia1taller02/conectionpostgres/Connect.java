package co.com.umb.talleresprogramacion.guia1taller02.conectionpostgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public static Connection con;
    public static Connection dbCon()
    {

        String driver="org.postgresql.Driver";
        String url="jdbc:postgresql://localhost:5432/postgres";
        String user="postgres";
        String pass="1234";
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url,user,pass);
        }
        catch(ClassNotFoundException|SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }

}