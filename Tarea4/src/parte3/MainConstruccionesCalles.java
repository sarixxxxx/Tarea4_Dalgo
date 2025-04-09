package parte3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MainConstruccionesCalles {
    public static void main(String[] args) {
        String archivo = "distancespiloto.txt"; // Ruta del archivo
        List<Calle> listaCalles = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.trim().split("\\s+");
                if (partes.length == 3) {
                    int esquina1 = Integer.parseInt(partes[0]);
                    int esquina2 = Integer.parseInt(partes[1]);
                    int costo = Integer.parseInt(partes[2]);
                    listaCalles.add(new Calle(esquina1, esquina2, costo));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
            return;
        }

        ConstruccionesDobleVia construcciones = new ConstruccionesDobleVia();
        Set<Calle> resultado = construcciones.viasAConvertir(listaCalles);

        System.out.println("Calles seleccionadas para ser doble vía:");
        for (Calle calle : resultado) {
            System.out.println("Esquina " + calle.getEsquina1() + " <--> Esquina " + calle.getEsquina2() +
                    " | Costo: " + calle.getCostoDeConstruccion());
        }
    }
}
