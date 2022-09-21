package co.com.umb.talleresprogramacion.guia1taller02.datos;


public interface IUser {
    String NOMBRE_ARCHIVO="danielburitica.txt";
    void addUser(String user, String password);
    String validateUser(String user);
    void start(String user, String password);
}
