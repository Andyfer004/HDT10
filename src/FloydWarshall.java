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
public class FloydWarshall {
    private int[][] distancias;
    private String[][] recorridos;
    private String[] vertices;
    private int SIZE;

    public FloydWarshall(int[][] distancias, String[][] recorridos, String[] vertices,int SIZE) {
        this.distancias = distancias;
        this.recorridos = recorridos;
        this.vertices=vertices;
        this.SIZE = SIZE;
    }

    /**
     * @return the distancias
     */
    public int[][] getDistancias() {
        return distancias;
    }

    /**
     * @param distancias the distancias to set
     */
    public void setDistancias(int[][] distancias) {
        this.distancias = distancias;
    }

    /**
     * @return the recorridos
     */
    public String[][] getRecorridos() {
        return recorridos;
    }

    /**
     * @param recorridos the recorridos to set
     */
    public void setRecorridos(String[][] recorridos) {
        this.recorridos = recorridos;
    }

    /**
     * @return the sIZE
     */
    public int getSIZE() {
        return SIZE;
    }

    /**
     * @param sIZE the sIZE to set
     */
    public void setSIZE(int sIZE) {
        SIZE = sIZE;
    }

    

    public void CalcularRutas() {
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

    public String[] getVertices() {
        return vertices;
    }
}