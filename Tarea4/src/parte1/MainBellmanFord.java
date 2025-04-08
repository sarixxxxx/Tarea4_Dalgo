package parte1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainBellmanFord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresa el nombre del archivo (ej: distances5.txt): ");
        String fileName = sc.nextLine();

        File file = new File(fileName);
        List<DirectedEdge> graph = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\\s+");
                    int source = Integer.parseInt(parts[0]);
                    int destination = Integer.parseInt(parts[1]);
                    int weight = Integer.parseInt(parts[2]);
                    graph.add(new DirectedEdge(source, destination, weight));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error leyendo el archivo: " + fileName);
            e.printStackTrace();
            return;
        }

        BellmanFord bellman = new BellmanFord();
        int[][] respuesta = bellman.bellmanFordImplementation(graph);

        System.out.println("\nResultados (desde nodo fuente 0):");
        for (int i = 0; i < respuesta.length; i++) {
            String costo = (respuesta[i][1] >= BellmanFord.INFINITY) ? "INF" : String.valueOf(respuesta[i][1]);
            System.out.println("Destino: " + i + ", Predecesor: " + respuesta[i][0] + ", Costo: " + costo);
        }
    }
}
