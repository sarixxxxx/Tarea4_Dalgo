package parte2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainBFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresa la ruta del archivo del grafo: ");
        String ruta = sc.nextLine();

        List<UndirectedWeightedEdge> grafo = new ArrayList<>();

        try {
            Scanner archivo = new Scanner(new File(ruta));
            while (archivo.hasNextLine()) {
                String linea = archivo.nextLine().trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split("\\s+");
                    int v1 = Integer.parseInt(partes[0]);
                    int v2 = Integer.parseInt(partes[1]);
                    int peso = Integer.parseInt(partes[2]);
                    grafo.add(new UndirectedWeightedEdge(v1, v2, peso));
                }
            }
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        BFS bfs = new BFS();
        Partition particion = bfs.bfsImplementation(grafo);

        // Mostrar componentes conectados
        System.out.println("\nComponentes conectados:");
        for (int u=0;u<particion.getN();u++) {
            System.out.println(u+"papa es "+ particion.getRepresentantes().get(u));
        }
    }
}
