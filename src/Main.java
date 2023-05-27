import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("-----------------------------------------");
        System.out.println("BIENVENIDO AL PROGRAMA DE RUTAS CIUDADES");
        System.out.println("-----------------------------------------");
        
        int opcion;
        do {
            System.out.println("1. Ruta más corta entre dos ciudades");
            System.out.println("2. Nombre ciudad centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = scan.nextInt();
            switch (opcion) {
                case 1:
                      System.out.println("Ingrese ciudad origen: ");
                    break;
                case 2:
                    System.out.println("Ingresar rutas");
                    break;
                case 3:
                    System.out.println("Calcular rutas");
                    break;
                case 4:
                    System.out.println("Salir");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 4);

    }
}