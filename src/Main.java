/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Seccion: 10
 * Hoja de Trabajo #10
 * 
 * Implementacion de grafos
 * 
 * Integrantes:
 *          Gabriel Alberto Paz Gonzalez 221087
 *          Andy Fernando Fuentes Velasquez 22944 
 * 
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/distancias.txt";
        Scanner scan = new Scanner(System.in);
        Controller controller = new Controller();
        int climaViaje = 0;


        controller.leerArchivo(filePath);
        


        System.out.println("-----------------------------------------");
        System.out.println("BIENVENIDO AL PROGRAMA DE RUTAS UNIVERSITARIAS");
        System.out.println("-----------------------------------------");
        
        int opcion;
        do {
            System.out.println("1. Ruta más corta entre dos universidades");
            System.out.println("2. Nombre de la Universidad en el centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Acerca del programa");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = scan.nextInt();
            switch (opcion) {
                case 1:

                    System.out.println("¿En que clima piensa viajar?");
                    System.out.println("1. Normal");
                    System.out.println("2. Lluvioso");
                    System.out.println("3. Nevado");
                    System.out.println("4. Ventoso");
                    climaViaje = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Ingrese la Universidad donde se encuentra: ");
                    String universidadActual = scan.nextLine();
                    System.out.println(" ");
                    System.out.println("Ingrese la Universidad hacia donde se dirije: ");
                    String universidadFinal = scan.nextLine();
                    System.out.println(controller.Ruta_Corta(universidadActual, universidadFinal, climaViaje));
                    break;
                case 2:
                    System.out.println("Ingresar rutas");
                    break;
                case 3:
                    System.out.println("Calcular rutas");
                    break;
                case 4:
                    System.out.println("Acerca del programa");
                    System.out.println("Este programa fue desarrollado por Gabriel Alberto Paz Gonzalez y Andy Fernando Fuentes Velasquez");
                    System.out.println("para la clase de Algoritmos y Estructuras de Datos de la Universidad del Valle de Guatemala");
                    System.out.println(" ");
                    System.out.println("El programa consiste en un grafo que contiene las distancias entre las universidades");
                    System.out.println(", y calcula la ruta mas corta entre dos universidades, tomando en cuenta");
                    System.out.println("el clima en el que se viaja");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 4);

    }
}