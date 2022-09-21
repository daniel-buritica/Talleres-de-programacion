package co.com.umb.talleresprogramacion.guia1taller02;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import co.com.umb.talleresprogramacion.guia1taller02.dao.UserSql;
import co.com.umb.talleresprogramacion.guia1taller02.usuario.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;



@WebServlet(name = "update", value = "/update-account")
public class Update extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();
        UserSql usql=new UserSql();
        int result=0;
        String[] data=null;
        HttpSession session=request.getSession();
        user.setName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastname"));
        user.setGmail(request.getParameter("email"));
        user.setUser(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        try {
            result=usql.updateData(user);

            if(result!=0)
            {
                data=usql.singleView(user);
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
            }
            else {
                PrintWriter out=response.getWriter();
                out.println("Update Failed");
            }
        }
        catch(SQLException|ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }


}
