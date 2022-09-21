package co.com.umb.talleresprogramacion.guia1taller02;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import co.com.umb.talleresprogramacion.guia1taller02.dao.UserSql;
import co.com.umb.talleresprogramacion.guia1taller02.usuario.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

@WebServlet(name = "login", value = "/login-account")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();
        UserSql usql=new UserSql();
        String[] data=null;
        HttpSession session=request.getSession();
        user.setUser(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        try
        {
            if(usql.userLogin(user))
            {
                data=usql.singleView(user);
                user.setName(data[1]);
                session.setAttribute("name",user.getName());
                session.setAttribute("username", user.getUser());
                session.setAttribute("password", user.getPassword());
                RequestDispatcher rd=request.getRequestDispatcher("decision.html");
                rd.forward(request, response);
            }
            else {
                response.sendRedirect("login.html");
                session.setAttribute("ErrorMessage","Login Failed");
            }
        }catch(SQLException|ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}
