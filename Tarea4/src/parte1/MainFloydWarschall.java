package parte1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainFloydWarschall {
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

        FloydWarschall floyd = new FloydWarschall();
        int[][] result = floyd.floydWarschallImplementation(graph);

        System.out.println("\nMatriz de costos mínimos entre todos los pares:");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (result[i][j] >= FloydWarschall.INFINITY)
                    System.out.print("INF\t");
                else
                    System.out.print(result[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
