import java.util.*;

public class BFS {

    private int[][] grafo;
    private int numVertices;

    public BFS(int[][] _grafo, int vertices) {
        grafo = _grafo;
        numVertices = vertices;
    }

    public void breadthFirstSearch(int inicio) {
        boolean[] visitado = new boolean[numVertices];
        Queue<Integer> cola = new LinkedList<>();

        visitado[inicio] = true;
        cola.offer(inicio);

        while (!cola.isEmpty()) {
            int vertice = cola.poll();
            System.out.println("Visitando vértice: " + vertice);

            for (int i = 0; i < numVertices; i++) {
                if (grafo[vertice][i] != 0 && !visitado[i]) {
                    visitado[i] = true;
                    cola.offer(i);
                }
            }
        }
    }

}

