package co.com.umb.talleresprogramacion.guia1taller02.datos;




import co.com.umb.talleresprogramacion.guia1taller02.usuario.User;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class AccessDataImplement implements IAccessData {

    @Override
    public boolean exists(String fileName) throws Exception {
        File file = new File(fileName);
        return file.exists();
    }

    @Override
    public String search(String fileName, String search) throws Exception {
        File file = new File(fileName);
        String result = null;
        boolean validate = false;
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(file));
            String linea = null;
            linea = entrada.readLine();
            while (linea != null) {
                if (validate) {
                    result = linea;
                    break;
                }
                if (search != null && search.equalsIgnoreCase(linea)) {
                    validate = true;
                }
                linea = entrada.readLine();
                //indice++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public void toWrite(User user, String fileName, Boolean append) throws Exception {
        File file = new File(fileName);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(file, append));
            salida.println(user.getUser());
            System.out.println(user.getUser());
            salida.println(user.getPassword());
            System.out.println(user.getPassword());
            salida.close();
            System.out.println("Se ha escrito informacion al archivo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toOpen(String fileName) throws Exception {
        File file = new File(fileName);
        System.out.println("URL: " + file.getPath());
        Desktop.getDesktop().open(file);
    }

    @Override
    public void Delete(String fileName) throws Exception {
        File file = new File(fileName);
        file.delete();
        System.out.println("Se borró la información");
    }

    @Override
    public void create(String fileName) throws Exception {
        File file = new File(fileName);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(file));
            salida.close();
            System.out.println("Se creó el archivo con éxito");
        } catch (Exception e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

}
