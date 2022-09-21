
package co.com.umb.talleresprogramacion.guia1taller02.datos;


import co.com.umb.talleresprogramacion.guia1taller02.usuario.User;

public class UsuarioImpl implements IUser {

    private final IAccessData data;

    public UsuarioImpl() {
        this.data = new AccessDataImplement();
    }

    @Override
    public void addUser(String user, String password) {
      //  User user1 = new User(user, password);
        boolean append = false;
        try {
            append = data.exists(NOMBRE_ARCHIVO);
     //       data.toWrite(user1, NOMBRE_ARCHIVO, append);
        } catch (Exception e) {
            System.out.println("Error de acceso a datos");
            e.printStackTrace();
        }
    }

    @Override
    public String validateUser(String user) {

        String result = null;
        try {
            result = this.data.search(NOMBRE_ARCHIVO, user);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error al buscar información.");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void start(String user, String password) {
        try {
            if (!data.exists(NOMBRE_ARCHIVO)) {
                data.create(NOMBRE_ARCHIVO);
                this.addUser(user, password);
                System.out.println("Se inicio con exíto.");
            }
        } catch (Exception ex) {
            System.out.println("Error al inicializar la informción: " + ex.getMessage());
        }
    }

}
