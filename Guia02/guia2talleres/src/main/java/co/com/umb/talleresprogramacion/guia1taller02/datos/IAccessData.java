package co.com.umb.talleresprogramacion.guia1taller02.datos;


import co.com.umb.talleresprogramacion.guia1taller02.usuario.User;

public interface IAccessData {

    boolean exists(String fileName) throws Exception;

    String search(String fileName, String search) throws Exception;

    void toWrite(User user, String fileName, Boolean append) throws Exception;
    
    void toOpen(String fileName)throws Exception;
    
    void Delete(String fileName)throws Exception;
    
    void create(String fileName)throws Exception;
}
