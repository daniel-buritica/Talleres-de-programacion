package co.com.umb.talleresprogramacion.guia1taller02.dao;

import co.com.umb.talleresprogramacion.guia1taller02.conectionpostgres.Connect;
import co.com.umb.talleresprogramacion.guia1taller02.usuario.User;

import java.sql.*;

public class UserSql {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int insertData(User user) throws SQLException {
        int result = 0;
        String insertData = "insert into registeruser (name, last_name, email, user_name, passwordd) values(?,?,?,?,?)";
        con = Connect.dbCon();

        ps = con.prepareStatement(insertData);
        ps.setString(1, user.getName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getGmail());
        ps.setString(4, user.getUser());
        ps.setString(5, user.getPassword());


        result = ps.executeUpdate();

        con.close();
        return result;
    }
    public String[] singleView(User user) throws ClassNotFoundException, SQLException {
        String data[] = null;
        int count = 0;

        con = Connect.dbCon();

        String sql = "select name,last_name,email,user_name,passwordd from registeruser where user_name=? and passwordd=?";

        ps = con.prepareStatement(sql);
        ps.setString(1, user.getUser());
        ps.setString(2, user.getPassword());

        rs = ps.executeQuery();
        count = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            data = new String[count];
            for (int i = 0; i < count; i++) {
                data[i] = rs.getString(i + 1);
            }
        }
        con.close();
        return data;
    }
    public boolean userLogin(User user) throws SQLException {
        String checkData = "Select * from registeruser where user_name=? and passwordd=?";
        con = Connect.dbCon();

        ps = con.prepareStatement(checkData);
        ps.setString(1, user.getUser());
        ps.setString(2, user.getPassword());

        rs = ps.executeQuery();

        if (rs.next()) {
            return true;
        }
        return false;
    }

    public int updateData(User user) throws SQLException {
        int result = 0;
        String updateData = "update registeruser set name=?,last_name=?,email=?,user_name=?,passwordd=? where user_name=?";
        con = Connect.dbCon();

        ps = con.prepareStatement(updateData);
        ps.setString(1, user.getName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getGmail());
        ps.setString(4, user.getUser());
        ps.setString(5, user.getPassword());
        ps.setString(6, user.getUser());

        result = ps.executeUpdate();

        con.close();
        return result;
    }
    public int deleteAccount(User user) throws SQLException {
        int result = 0;
        String updateData = "delete from registeruser where user_name =?";
        con = Connect.dbCon();

        ps = con.prepareStatement(updateData);
        ps.setString(1, user.getUser());


        result = ps.executeUpdate();

        con.close();
        return result;
    }

}
