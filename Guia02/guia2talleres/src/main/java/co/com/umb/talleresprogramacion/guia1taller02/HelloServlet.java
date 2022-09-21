package co.com.umb.talleresprogramacion.guia1taller02;
import java.io.IOException;
import java.sql.SQLException;

import co.com.umb.talleresprogramacion.guia1taller02.dao.UserSql;
import co.com.umb.talleresprogramacion.guia1taller02.datos.IUser;
import co.com.umb.talleresprogramacion.guia1taller02.datos.UsuarioImpl;
import co.com.umb.talleresprogramacion.guia1taller02.usuario.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserSql userSql = new UserSql();
        User user = new User();
        int result = 0;
        user.setName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastname"));
        user.setGmail(request.getParameter("email"));
        user.setUser(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        try{
            result = userSql.insertData(user);
        }catch (SQLException e){
            e.printStackTrace();
        }

        response.sendRedirect("login.html");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public boolean validar(String username, String password) {

        boolean respuesta;
        IUser user = new UsuarioImpl();
        user.start("daniel", "1234");
        respuesta = false;

        if (user.validateUser(username) != null) {
            if (user.validateUser(username).equalsIgnoreCase(password)) {
                respuesta = true;
            } else {
                respuesta = false;
            }
        } else {
            respuesta = false;
        }

        return respuesta;
    }



}