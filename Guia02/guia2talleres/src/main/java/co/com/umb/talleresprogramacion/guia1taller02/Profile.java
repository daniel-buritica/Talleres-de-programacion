package co.com.umb.talleresprogramacion.guia1taller02;

import co.com.umb.talleresprogramacion.guia1taller02.dao.UserSql;
import co.com.umb.talleresprogramacion.guia1taller02.usuario.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "profile", value = "/profile-account")
public class Profile extends HelloServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserSql usql = new UserSql();
        User user = new User();
        String[] data = null;
        HttpSession session = request.getSession();

        user.setUser((String) session.getAttribute("username"));
        user.setPassword((String) session.getAttribute("password"));

        try {
            data = usql.singleView(user);
            user.setName(data[0]);
            user.setLastName(data[1]);
            user.setGmail(data[2]);
            user.setUser(data[3]);
            user.setPassword(data[4]);
            session = request.getSession();
            session.setAttribute("name", user.getName());
            session.setAttribute("lastname", user.getLastName());
            session.setAttribute("email", user.getGmail());
            session.setAttribute("username", user.getUser());
            session.setAttribute("password", user.getPassword());
            RequestDispatcher rd = request.getRequestDispatcher("profile02.jsp");
            rd.forward(request, response);
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }

    }

}
