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
        String filePath = "/Users/andyfer004/IdeaProjects/HDT10/src/distancias.txt";
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
            System.out.println("4. Salir");
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
                    System.out.println("Ciudad del centro del grafo:");
                    String centroGrafo = controller.calcularCentroGrafo();
                    System.out.println(centroGrafo);
                    break;
                case 3:
                    System.out.println("Modificación de Grafo");
                    System.out.println("1. Interrupción de tráfico entre ciudades");
                    System.out.println("2. Establecer conexión entre ciudades");
                    System.out.println("3. Indicar clima entre ciudades");
                    System.out.print("Ingrese una opción: ");
                    int opcionModificacion = scan.nextInt();
                    scan.nextLine();

                    switch (opcionModificacion) {
                        case 1:
                            System.out.println("Interrupción de tráfico entre ciudades");
                            System.out.print("Ingrese la primera ciudad: ");
                            String ciudad1 = scan.nextLine();
                            System.out.print("Ingrese la segunda ciudad: ");
                            String ciudad2 = scan.nextLine();
                            controller.modificarGrafo(ciudad1, ciudad2, 0, 1); // Se establece tiempo 0 (sin conexión)
                            System.out.println("Interrupción de tráfico entre " + ciudad1 + " y " + ciudad2 + " realizada.");
                            break;
                        case 2:
                            System.out.println("Establecer conexión entre ciudades");
                            System.out.print("Ingrese la primera ciudad: ");
                            ciudad1 = scan.nextLine();
                            System.out.print("Ingrese la segunda ciudad: ");
                            ciudad2 = scan.nextLine();
                            System.out.print("Ingrese el tiempo de viaje entre las ciudades: ");
                            int tiempo = scan.nextInt();
                            scan.nextLine();
                            controller.modificarGrafo(ciudad1, ciudad2, tiempo, 1); // Por defecto, clima normal
                            System.out.println("Conexión establecida entre " + ciudad1 + " y " + ciudad2 + " con tiempo " + tiempo + ".");
                            break;
                        case 3:
                            System.out.println("Indicar clima entre ciudades");
                            System.out.print("Ingrese la primera ciudad: ");
                            ciudad1 = scan.nextLine();
                            System.out.print("Ingrese la segunda ciudad: ");
                            ciudad2 = scan.nextLine();
                            System.out.println("1. Normal");
                            System.out.println("2. Lluvia");
                            System.out.println("3. Nieve");
                            System.out.println("4. Tormenta");
                            System.out.print("Ingrese el clima: ");
                            int clima = scan.nextInt();
                            scan.nextLine();
                            controller.modificarGrafo(ciudad1, ciudad2, 0, clima); // Se establece tiempo 0 (sin conexión)
                            System.out.println("Clima entre " + ciudad1 + " y " + ciudad2 + " modificado.");
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                    String nuevoCentroGrafo = controller.calcularCentroGrafo();
                    System.out.println("Nuevo centro del grafo: " + nuevoCentroGrafo);
                    break;


            }
        } while (opcion != 4);

    }
}