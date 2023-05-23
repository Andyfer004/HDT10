import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Controller {

    private int[][] distancias;
    private String[][] recorridos;
    private String[] vertices;
    private int SIZE;

    public Controller(String rutaArchivo) {
        leerArchivo(rutaArchivo);
    }

    private void leerArchivo(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            // Leer la primera línea para obtener el tamaño de la matriz
            String primeraLinea = br.readLine();
            String[] partes = primeraLinea.split(" ");
            SIZE = partes.length;

            // Inicializar las matrices
            distancias = new int[SIZE][SIZE];
            recorridos = new String[SIZE][SIZE];
            vertices = new String[SIZE];

            // Leer las demás líneas del archivo
            int fila = 0;
            while ((primeraLinea = br.readLine()) != null) {
                partes = primeraLinea.split(" ");
                vertices[fila] = partes[0];

                for (int columna = 0; columna < SIZE; columna++) {
                    distancias[fila][columna] = Integer.parseInt(partes[columna + 2]);
                    recorridos[fila][columna] = partes[1];
                }

                fila++;
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calcularRutas() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if ((i != j) && (i != k)) {
                        int suma = distancias[j][i] + distancias[i][k];
                        if (suma < distancias[j][k]) {
                            distancias[j][k] = suma;
                            recorridos[j][k] = vertices[i];
                        }
                    }
                }
            }
        }
    }

    public void mostrarMatrizDistancias() {
        System.out.println("Matriz de Distancias:");
        for (int i = 0; i < SIZE; i++) {
            System.out.println(Arrays.toString(distancias[i]));
        }
    }

    public void mostrarRutaMasCorta(String origen, String destino) {
        int indiceOrigen = obtenerIndiceCiudad(origen);
        int indiceDestino = obtenerIndiceCiudad(destino);

        if (indiceOrigen == -1 || indiceDestino == -1) {
            System.out.println("Universidades no encontradas.");
            return;
        }

        String ruta = origen;
        int distancia = distancias[indiceOrigen][indiceDestino];

        while (!vertices[indiceOrigen].equals(destino)) {
            ruta += " -> " + vertices[indiceOrigen];
            indiceOrigen = obtenerIndiceCiudad(vertices[indiceOrigen]);
        }

        ruta += " -> " + destino;

        System.out.println("Ruta más corta:");
        System.out.println(ruta);
        System.out.println("Distancia: " + distancia);
    }

    private int obtenerIndiceCiudad(String ciudad) {
        for (int i = 0; i < SIZE; i++) {
            if (vertices[i].equals(ciudad)) {
                return i;
            }
        }
        return -1;
    }

}
