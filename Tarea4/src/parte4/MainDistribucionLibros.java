package parte4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainDistribucionLibros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese la ruta del archivo .txt con los camiones: ");
        String archivo = sc.nextLine();

        System.out.print("Ingrese la cantidad de libros por camión: ");
        int librosPorCamion = sc.nextInt();

        System.out.print("Ingrese la cantidad de libros por bodega: ");
        int librosPorBodega = sc.nextInt();

        List<Camion> listaCamiones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Formato: fuente tipoFuente destino tipoDestino
                String[] partes = linea.split("\\s+");
                int fuente = Integer.parseInt(partes[0]);
                String tipoFuente = partes[1];
                int destino = Integer.parseInt(partes[2]);
                String tipoDestino = partes[3];

                Camion camion = new Camion(fuente, tipoFuente, destino, tipoDestino);
                listaCamiones.add(camion);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        RedDistribucionLibros red = new RedDistribucionLibros();
        int maxLibros = red.librosMaximosATransportar(listaCamiones, librosPorCamion, librosPorBodega);

        System.out.println("La cantidad máxima de libros que se pueden transportar es: " + maxLibros);
    }
}
