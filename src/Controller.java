import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;

public class Controller {
    private int[][] distancias;
    private String[][] recorridos;
    private String[] vertices;
    private int size;
    FloydWarshall floydWarshall;
    private ArrayList<String[]> lineas;
    private ArrayList<String> contenido;

    public Controller() {
        lineas= new ArrayList<>();
    }


    public ArrayList<String> leerArchivo(String filePath) {
        ArrayList<String> lineas= new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {;
            String line;

            while ((line = br.readLine()) != null) 
            {
                lineas.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        encontrarMatrizRecorridos(lineas);
        encontrarMatrizDistancias(lineas);

        return lineas;

    }
    

    public String Ruta_Corta(String universidadActual, String universidadFinal, int climaViaje){
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

        climaViaje = climaViaje+1;
        elegirClima(universidadActual, universidadFinal, climaViaje);
        String resultado = "";
        floydWarshall = new FloydWarshall(distancias, recorridos, vertices, vertices.length);
        floydWarshall.CalcularRutas();
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if(universidadActual.equals(universidadFinal)){
                    resultado+=universidadActual+" ===> "+floydWarshall.getRecorridos()[i][j];
                    break;
                }
                else if(vertices[i].equals(universidadActual)&&vertices[j].equals(universidadFinal)){
                    resultado+=universidadActual+" ===> "+floydWarshall.getRecorridos()[i][j]+" ===> "+universidadFinal;
                }
            }
        }
        
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if(vertices[i].equals(universidadActual)&&vertices[j].equals(universidadFinal)){
                    resultado+="\n"+clima+": "+floydWarshall.getDistancias()[i][j];
                    break;
                }
            }
        }
        return resultado;
    }

    public void elegirClima(String universidadActual, String universidadFinal, int posicion) {
        int lugarInicio = -1;
        int lugarFinal = -1;
        String[] tokens =new String[1];

        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(universidadActual)) {
                lugarInicio = i;
            } else if (vertices[i].equals(universidadFinal)) {
                lugarFinal = i;
            }
        }
    
        if (lugarInicio != -1 && lugarFinal != -1) {
            // Obtener el arreglo de cadenas correspondiente al par de lugares en el ArrayList
            for(int i =0;i<lineas.size();i++){
                if(lineas.get(i)[0].equals(universidadActual)&&lineas.get(i)[1].equals(universidadFinal)){
                     tokens = lineas.get(i);
                }
            }
    
            int nuevaDistancia = Integer.parseInt(tokens[posicion]);
            tokens[posicion] = Integer.toString(nuevaDistancia);
    
            for(int i =0;i<lineas.size();i++){
                if(lineas.get(i)[0].equals(universidadActual)&&lineas.get(i)[1].equals(universidadFinal)){
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


    public String encontrarMatrizDistancias( ArrayList<String> contenido ) {
        lineas= new ArrayList<>();
        String resultado="";
            this.contenido = contenido;
            size = contenido.size();
            distancias = new int[size][size];
    
            ArrayList<String> columnas= new ArrayList<>();
            ArrayList<String> filas= new ArrayList<>();
            int k=size;
            int contador=0;
            while (k!= 0) {
                String[] items = contenido.get(contador).split(" ");
                String universidadActual = items[0];
                String universidadFinal = items[1];
                lineas.add(items);
    
                // Almacena los vertices en un ArrayList
                if (!contenidoArray(columnas, universidadActual)) {
                    columnas.add(universidadActual);
                    
                }
                if (!contenidoArray(columnas, universidadFinal)) {
                    columnas.add(universidadFinal);
                }
                k=k-1;
                contador += 1;
            }
            filas=columnas;
            distancias = new int[columnas.size()][columnas.size()];
            for(int i=0; i< columnas.size();i++){
                for(int j=0;j<columnas.size();j++){
                    if(i==j){
                        distancias[i][j]=0;
                    }
                    else{
                        distancias[i][j]=1000;
                    }
                }
            }
            for (String[] items : lineas) {
                String univeridadActual = items[0];
                String universidadFinal = items[1];
                int distancia = Integer.parseInt(items[2]);
    
                int rowIndex = filas.indexOf(univeridadActual);
                int columnIndex = columnas.indexOf(universidadFinal);
    
                distancias[rowIndex][columnIndex] = distancia;
            }
            
            for(int i=0;i<columnas.size();i++){
                for(int j=0;j<columnas.size();j++){
                    resultado += distancias[i][j]+" ";
                }
                resultado+="\n";
                
            }
            vertices=new String[columnas.size()];
            for(int i=0;i<columnas.size();i++){
                vertices[i]=columnas.get(i);
            }
            
        return resultado;
    }

    
    public String encontrarMatrizRecorridos(ArrayList<String> contenido) {
        String resultado="";
            size = contenido.size();
            this.contenido=contenido;
            ArrayList<String> universidades= new ArrayList<>();
            int k=size;
            System.out.println(size);
            int contador=0;
            while (k!= 0) {
                String[] tokens = contenido.get(contador).split(" ");
                String universidadActual = tokens[0];
                String universidadFinal = tokens[1];
    
                // Almacena las universidades en un ArrayList
                if (!contenidoArray(universidades, universidadActual)) {
                    universidades.add(universidadActual);
                    
                }
                if (!contenidoArray(universidades, universidadFinal)) {
                    universidades.add(universidadFinal);
                }
                k=k-1;
                contador =+ 1;
            }
            recorridos = new String[universidades.size()][universidades.size()];
            for(int i=0;i<universidades.size();i++){
                for(int j=0;j<universidades.size();j++){
                    recorridos[i][j]=universidades.get(j);
                }
                
            }
           
            for(int i=0;i<universidades.size();i++){
                for(int j=0;j<universidades.size();j++){
                    resultado += recorridos[i][j]+" ";
                }
                resultado+="\n";
                
            }
        return resultado;
    }

    public ArrayList<String> getContenido()  
    {
       return this.contenido;
    }

    public int[][] getDistancias() {
        return distancias;
    }

    public String[][] getRecorridos() {
        return recorridos;
    }

    public int getSize() {
        return size;
    
        }

    private boolean contenidoArray(ArrayList<String> universidades, String universidad) {
        for (int i = 0; i < universidades.size(); i++) {
            if (universidades.get(i) != null && universidades.get(i).equals(universidad)) {
                return true;
            }
        }
        return false;
    }
    


        
        
        
    
    
}

