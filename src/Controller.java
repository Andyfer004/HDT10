import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;


public class Controller {
    
    // Scanner
    Scanner sc = new Scanner(System.in);

    //Lugares y distancias
    ArrayList<String> universidades = new ArrayList<>();
    ArrayList<String> distancias = new ArrayList<>();

    // Configuracion para el archivo
    String ruta = "./distancias.txt";
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;

    FloydWarshall grafo;

    /*
     * Encontrar la ruta mas corta entre 2 universidades, utilizando el algoritmo de FloydWarshall
     * 
     */
    public void rutaMasCorta() {

        System.out.println("-----------------RUTA MAS CORTA------------------");
        System.out.println("Ingrese la universidad de origen: ");
        String universidadInicial = sc.nextLine().toLowerCase();

        System.out.println("Ingrese la universidad de destino: ");
        String universidadFinal = sc.nextLine().toLowerCase();

        // Compara cada ciudad con las demas ciudades
        for (int i = 0; i < universidades.size(); i++) {
            for (int j = 0; j < universidades.size(); j++) {
                if (i > j) {

                    // Mira si el origen y el destino son iguales a los indicados
                    if ((universidadInicial.equals(grafo.getNombre(i).toLowerCase())
                            && universidadFinal.equals(grafo.getNombre(j).toLowerCase()))
                            || (universidadFinal.equals(grafo.getNombre(i).toLowerCase())
                                    && universidadInicial.equals(grafo.getNombre(j).toLowerCase()))) {

                        
                    }

                }
            }
        }

    }



}
