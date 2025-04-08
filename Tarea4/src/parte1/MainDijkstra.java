package parte1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainDijkstra {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo con los arcos (ej: arcos.txt): ");
        String fileName = scanner.nextLine().trim();

        List<DirectedEdge> graph = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if(parts.length == 3) {
                    int from = Integer.parseInt(parts[0]);
                    int to = Integer.parseInt(parts[1]);
                    int cost = Integer.parseInt(parts[2]);
                    graph.add(new DirectedEdge(from, to, cost));
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
            return;
        }

        Dijkstra dijkstra = new Dijkstra();
        int[][] resultado = dijkstra.dijkstraImplementation(graph);

        System.out.println("\nResultado del algoritmo de Dijkstra (fuente: nodo 0)");
        System.out.println("Destino\tPredecesor\tCosto");
        for(int i = 0; i < resultado.length; i++) {
            if(i == dijkstra.f) continue; // ignorar el nodo fuente
            String costoStr = (resultado[i][1] == Integer.MAX_VALUE) ? "∞" : String.valueOf(resultado[i][1]);
            System.out.println(i + "\t" + resultado[i][0] + "\t\t" + costoStr);
        }

        scanner.close();
    }
}
