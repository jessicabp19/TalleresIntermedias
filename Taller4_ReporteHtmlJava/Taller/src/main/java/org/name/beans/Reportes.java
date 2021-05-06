package org.name.beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Reportes {

    private Scanner op;
    private int num;
    private String name, usr, pass;
    private Usuario listaUsuarios[];
    private Usuario usuarioActual;
    private int pos;

    public Reportes() {
        System.out.println("Clase Reportes");

        op = new Scanner(System.in);
        num = 0;
        listaUsuarios = new Usuario[30];
        usuarioActual = new Usuario("", "", "");
        pos = 0;

        this.menu();
    }

    private void menu() {
        System.out.println("");
        System.out.println("    ***********************");
        System.out.println("    * CONTROL DE USUARIOS *");
        System.out.println("    ***********************");
        System.out.println("");
        System.out.println("Ingrese un numero segun la opcion que desee: ");
        System.out.println("[1]     Ingresar Usuario");
        System.out.println("[2]     Reporte por Consola");
        System.out.println("[3]     Reporte HTML");
        System.out.println("[otro]  Salir");
        System.out.println("");

        try {
            System.out.println(">");
            num = op.nextInt();

            switch (num) {
                case 1:
                    ingresarUsuario();
                    break;
                case 2:
                    reporteConsola();
                    break;
                case 3:
                    reporteHtml();
                    break;
                default:
                    System.out.println("Hasta luego!");
                    System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error!");
        }
    }

    private void ingresarUsuario() {
        System.out.println("");
        System.out.println(" INGRESO DE USUARIOS");

        System.out.println("Nombre: ");
        name = op.next();
        System.out.println("Usuario: ");
        usr = op.next();
        System.out.println("Contraseña: ");
        pass = op.next();

        usuarioActual = new Usuario(name, usr, pass);
        listaUsuarios[pos] = usuarioActual;
        pos++;

        menu();
    }

    private void reporteConsola() {
        for (int i = 0; i < listaUsuarios.length; i++) {
            if (listaUsuarios[i] != null) {
                System.out.println(listaUsuarios[i].getName() + "\t" + listaUsuarios[i].getUser() + "\t" + listaUsuarios[i].getPassword());
            } else {
                break;
            }
        }

        menu();
    }

    private void reporteHtml() {
        String ruta = "D:\\Users\\JESSICA\\Desktop\\reporteHtml.html";

        crearArchivo(ruta);
    }

    private void crearArchivo(String ruta) {
        String contenido = "";

        try {
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            contenido = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Document</title>\n"
                    + "    <style>*{\n"
                    + "       backgound-color: aqua;\n"
                    + "    }\n"
                    + "     table{\n"
                    + "        margin:auto;\n"
                    + "        padding: auto;\n"
                    + "      }\n"
                    + "      td{\n"
                    + "        margin: 15px;\n"
                    + "         padding: 15px;\n"
                    + "      }"
                    + "    </style>"
                    + "</head>\n"
                    + "<body>"
                    + "<table>\n"
                    + "        <tr>\n"
                    + "            <td>NAME</td>\n"
                    + "            <td>USER</td>\n"
                    + "            <td>PASSWORD</td>\n"
                    + "        </tr>";

            for (int i = 0; i < listaUsuarios.length; i++) {
                if (listaUsuarios[i] != null) {
                    contenido += "<tr>"
                            + "<td>" + listaUsuarios[i].getName() + "</td>"
                            + "<td>" + listaUsuarios[i].getUser() + "</td>"
                            + "<td>" + listaUsuarios[i].getPassword() + "</td>"
                            + "</tr>";
                } else {
                    break;
                }
            }
            contenido += "</table>\n"
                    + "</body>\n"
                    + "</html>";

            bw.write(contenido);
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
