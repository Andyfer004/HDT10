import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.lang.String;
public class Controller {
    private static final int SIZE = 5;
    private int[][] distancias;
    private String[][] recorridos;
    private String[] vertices;
    private int size;
    private FloydWarshall floydWarshall;
    private ArrayList<String[]> lineas;

    public Controller() {
        lineas = new ArrayList<>();
    }

    public ArrayList<String> leerArchivo(String filePath) {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                lineas.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        encontrarMatrizRecorridos(lineas);
        encontrarMatrizDistancias(lineas);

        return lineas;
    }

    public String Ruta_Corta(String universidadActual, String universidadFinal, int climaViaje) {
        String clima = "";

        if (climaViaje == 1) {
            clima = "Normal";
        } else if (climaViaje == 2) {
            clima = "Lluvioso";
        } else if (climaViaje == 3) {
            clima = "Nevado";
        } else if (climaViaje == 4) {
            clima = "Ventoso";
        }

        climaViaje = climaViaje + 1;
        elegirClima(universidadActual, universidadFinal, climaViaje);
        String resultado = "";
        floydWarshall = new FloydWarshall(distancias, recorridos, vertices, vertices.length);
        floydWarshall.CalcularRutas();
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if (universidadActual.equals(universidadFinal)) {
                    resultado += universidadActual + " ===> " + floydWarshall.getRecorridos()[i][j];
                    break;
                } else if (vertices[i].equals(universidadActual) && vertices[j].equals(universidadFinal)) {
                    resultado += universidadActual + " ===> " + floydWarshall.getRecorridos()[i][j] + " ===> " + universidadFinal;
                }
            }
        }

        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if (vertices[i].equals(universidadActual) && vertices[j].equals(universidadFinal)) {
                    resultado += "\n" + clima + ": " + floydWarshall.getDistancias()[i][j];
                    break;
                }
            }
        }
        return resultado;
    }

    public void elegirClima(String universidadActual, String universidadFinal, int posicion) {
        int lugarInicio = -1;
        int lugarFinal = -1;
        String[] tokens = new String[1];

        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(universidadActual)) {
                lugarInicio = i;
            } else if (vertices[i].equals(universidadFinal)) {
                lugarFinal = i;
            }
        }

        if (lugarInicio != -1 && lugarFinal != -1) {
            // Obtener el arreglo de cadenas correspondiente al par de lugares en el ArrayList
            for (int i = 0; i < lineas.size(); i++) {
                if (lineas.get(i)[0].equals(universidadActual) && lineas.get(i)[1].equals(universidadFinal)) {
                    tokens = lineas.get(i);
                }
            }

            int nuevaDistancia = Integer.parseInt(tokens[posicion]);
            tokens[posicion] = Integer.toString(nuevaDistancia);

            for (int i = 0; i < lineas.size(); i++) {
                if (lineas.get(i)[0].equals(universidadActual) && lineas.get(i)[1].equals(universidadFinal)) {
                    lineas.set(i, tokens);
                }
            }

            distancias[lugarInicio][lugarFinal] = nuevaDistancia;
            distancias[lugarFinal][lugarInicio] = nuevaDistancia;

            if (nuevaDistancia < 1000) {
                recorridos[lugarInicio][lugarFinal] = universidadFinal;
                recorridos[lugarFinal][lugarInicio] = universidadActual;
            }
        }
    }

    public String encontrarMatrizDistancias(ArrayList<String> contenido) {
        String resultado = "";
        lineas = new ArrayList<>();
        size = contenido.size();
        distancias = new int[size][size];

        ArrayList<String> columnas = new ArrayList<>();
        ArrayList<String> filas = new ArrayList<>();
        int k = size;
        int contador = 0;
        while (k != 0) {
            String[] items = contenido.get(contador).split(" ");
            String universidadActual = items[0];
            String universidadFinal = items[1];
            lineas.add(items);

            // Almacena los vertices en un ArrayList
            if (!columnas.contains(universidadActual)) {
                columnas.add(universidadActual);
            }
            if (!columnas.contains(universidadFinal)) {
                columnas.add(universidadFinal);
            }
            k = k - 1;
            contador += 1;
        }
        filas = columnas;
        distancias = new int[columnas.size()][columnas.size()];
        for (int i = 0; i < columnas.size(); i++) {
            for (int j = 0; j < columnas.size(); j++) {
                if (i == j) {
                    distancias[i][j] = 0;
                } else {
                    distancias[i][j] = 1000;
                }
            }
        }
        for (String[] items : lineas) {
            String universidadActual = items[0];
            String universidadFinal = items[1];
            int distancia = Integer.parseInt(items[2]);

            int rowIndex = filas.indexOf(universidadActual);
            int columnIndex = columnas.indexOf(universidadFinal);

            distancias[rowIndex][columnIndex] = distancia;
        }

        for (int i = 0; i < columnas.size(); i++) {
            for (int j = 0; j < columnas.size(); j++) {
                resultado += distancias[i][j] + " ";
            }
            resultado += "\n";
        }
        vertices = new String[columnas.size()];
        for (int i = 0; i < columnas.size(); i++) {
            vertices[i] = columnas.get(i);
        }

        return resultado;
    }

    public String encontrarMatrizRecorridos(ArrayList<String> contenido) {
        String resultado = "";
        size = contenido.size();
        ArrayList<String> universidades = new ArrayList<>();
        int k = size;
        int contador = 0;
        while (k != 0) {
            String[] items = contenido.get(contador).split(" ");
            String universidadActual = items[0];
            String universidadFinal = items[1];

            // Almacena los vertices en un ArrayList
            if (!universidades.contains(universidadActual)) {
                universidades.add(universidadActual);
            }
            if (!universidades.contains(universidadFinal)) {
                universidades.add(universidadFinal);
            }
            k = k - 1;
            contador += 1;
        }

        recorridos = new String[universidades.size()][universidades.size()];
        for (int i = 0; i < universidades.size(); i++) {
            for (int j = 0; j < universidades.size(); j++) {
                recorridos[i][j] = universidades.get(j);
            }
        }

        for (int i = 0; i < universidades.size(); i++) {
            for (int j = 0; j < universidades.size(); j++) {
                resultado += recorridos[i][j] + " ";
            }
            resultado += "\n";
        }

        return resultado;
    }
    public String calcularCentroGrafo() {
        int centro = -1;
        int minimoMayorDistancia = Integer.MAX_VALUE;

        for (int i = 0; i < vertices.length; i++) {
            int mayorDistancia = 0;

            for (int j = 0; j < vertices.length; j++) {
                if (i != j) {
                    int distancia = distancias[i][j];
                    if (distancia > mayorDistancia) {
                        mayorDistancia = distancia;
                    }
                }
            }

            if (mayorDistancia < minimoMayorDistancia) {
                minimoMayorDistancia = mayorDistancia;
                centro = i;
            }
        }

        if (centro != -1) {
            return "El centro del grafo es la universidad: " + vertices[centro];
        } else {
            return "No se pudo determinar el centro del grafo.";
        }
    }
    public void modificarGrafo(String ciudad1, String ciudad2, int tiempo, int clima) {
        int indiceCiudad1 = obtenerIndice(ciudad1);
        int indiceCiudad2 = obtenerIndice(ciudad2);

        if (indiceCiudad1 != -1 && indiceCiudad2 != -1) {
            distancias[indiceCiudad1][indiceCiudad2] = tiempo;
            distancias[indiceCiudad2][indiceCiudad1] = tiempo;

            // Modificar el clima en la matriz de distancias
            switch (clima) {
                case 1: // Normal
                    break;
                case 2: // Lluvia
                    distancias[indiceCiudad1][indiceCiudad2] *= 1.2; // Aumentar el tiempo en un 20%
                    distancias[indiceCiudad2][indiceCiudad1] *= 1.2;
                    break;
                case 3: // Nieve
                    distancias[indiceCiudad1][indiceCiudad2] *= 1.5; // Aumentar el tiempo en un 50%
                    distancias[indiceCiudad2][indiceCiudad1] *= 1.5;
                    break;
                case 4: // Tormenta
                    distancias[indiceCiudad1][indiceCiudad2] *= 2; // Aumentar el tiempo en un 100%
                    distancias[indiceCiudad2][indiceCiudad1] *= 2;
                    break;
                default:
                    System.out.println("Opción de clima no válida.");
                    break;
            }

            // Recalcular las rutas más cortas
            FloydWarshall fw = new FloydWarshall(distancias, recorridos, vertices, SIZE);
            fw.CalcularRutas();
        } else {
            System.out.println("Una o ambas ciudades no existen en el grafo.");
        }
    }

    // ...

    private int obtenerIndice(String ciudad) {
        for (int i = 0; i < SIZE; i++) {
            if (vertices[i].equals(ciudad)) {
                return i;
            }
        }
        return -1; // La ciudad no existe en el grafo
    }

}


