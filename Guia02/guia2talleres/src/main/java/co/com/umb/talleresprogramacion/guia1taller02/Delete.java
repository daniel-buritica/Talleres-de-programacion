package co.com.umb.talleresprogramacion.guia1taller02;

import co.com.umb.talleresprogramacion.guia1taller02.dao.UserSql;
import co.com.umb.talleresprogramacion.guia1taller02.usuario.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "delete", value = "/delete-account")
public class Delete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserSql usql = new UserSql();
        User user = new User();
        int result=0;
        HttpSession session = request.getSession();

        user.setUser((String) session.getAttribute("username"));
        user.setPassword((String) session.getAttribute("password"));

        try{
            result=usql.deleteAccount(user);
        }catch (SQLException e1){
            e1.printStackTrace();
        }
        response.sendRedirect("index.html");

    }
}
