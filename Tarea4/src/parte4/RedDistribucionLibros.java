package parte4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedDistribucionLibros {
    public static int INFINITY = 1000000;

    public int librosMaximosATransportar(List<Camion> listaCamiones, int librosPorCamion, int librosPorBodega) {

        Map<Integer, List<Camion>> listaAdj = listaAdyacencia(listaCamiones, librosPorCamion, librosPorBodega);
        Set<Camion> setCamiones = new HashSet<>(listaCamiones);

        Set<Integer> fabricas = listarElem(listaAdj, "F");
        Set<Integer> librerias = listarElem(listaAdj, "L");

        int superfuente = countVertex(listaCamiones);
        int superdestino = -superfuente;


        for (int fabrica : fabricas) {
            listaAdj.computeIfAbsent(superfuente, k -> new ArrayList<>());
            Camion c1 = new Camion(superfuente, "S", fabrica, "F");
            c1.setLibros(librosPorCamion);
            listaAdj.get(superfuente).add(c1);
        }


        for (int libreria : librerias) {
            listaAdj.computeIfAbsent(libreria, k -> new ArrayList<>());
            Camion c1 = new Camion(libreria, "L", superdestino, "T");
            c1.setLibros(librosPorCamion);
            listaAdj.get(libreria).add(c1);
        }


        List<FlujoCamion> flujosPorCamion = new ArrayList<>();
        for (Camion camion : listaCamiones) {
            FlujoCamion nuevo = new FlujoCamion(camion, 0);
            flujosPorCamion.add(nuevo);
        }


        Map<Integer, List<Camion>> grafoResidual = generarGrafoResidual(listaAdj);


        int flujoMaximo = 0;
        List<Camion> existeCamino = encontrarCamino(superfuente, superdestino, grafoResidual);
        while (existeCamino != null) {
            int flujoMinimo = obtenerPesoMinimoCamino(existeCamino, librosPorCamion, librosPorBodega);

            for (Camion camion : existeCamino) {
                if (setCamiones.contains(camion)) {
                    FlujoCamion flujo = encontrarFlujoCamion(flujosPorCamion, camion);
                    flujo.setFlujo(flujo.getFlujo() + flujoMinimo);
                } else {
                    FlujoCamion flujo = encontrarComplemento(flujosPorCamion, camion);
                    flujo.setFlujo(flujo.getFlujo() - flujoMinimo);
                }

                // Actualizar capacidades en el grafo residual
                camion.libros -= flujoMinimo;
                Camion aristaInversa = encontrarArista(grafoResidual, camion.getDestino(), camion.getFuente());
                if (aristaInversa != null) {
                    aristaInversa.setLibros(aristaInversa.getLibros() + flujoMinimo);
                }
            }

            flujoMaximo += flujoMinimo;
            existeCamino = encontrarCamino(superfuente, superdestino, grafoResidual);
        }

        return flujoMaximo;
    }

    private FlujoCamion encontrarComplemento(List<FlujoCamion> flujosPorCamion, Camion camion) {
        for (FlujoCamion flujo : flujosPorCamion) {
            if (flujo.getCamion().getFuente() == camion.getDestino() &&
                flujo.getCamion().getDestino() == camion.getFuente()) {
                return flujo;
            }
        }
        return null;
    }

    private FlujoCamion encontrarFlujoCamion(List<FlujoCamion> flujosPorCamion, Camion camion) {
        for (FlujoCamion flujo : flujosPorCamion) {
            if (flujo.getCamion().equals(camion)) {
                return flujo;
            }
        }
        return null;
    }

    private int obtenerPesoMinimoCamino(List<Camion> existeCamino, int librosPorCamion, int librosPorBodega) {
        int flujoMinimo = INFINITY;
        for (Camion camion : existeCamino) {
            flujoMinimo = Math.min(flujoMinimo, camion.getLibros());
        }
        return flujoMinimo;
    }

    private Camion encontrarArista(Map<Integer, List<Camion>> grafoResidual, int fuente, int destino) {
        for (Camion camion : grafoResidual.getOrDefault(fuente, new ArrayList<>())) {
            if (camion.getDestino() == destino) {
                return camion;
            }
        }
        return null;
    }

    private List<Camion> encontrarCamino(int superFuente, int superDestino, Map<Integer, List<Camion>> grafoResidual) {
        List<Camion> camino = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        if (dfs(superFuente, superDestino, grafoResidual, camino, visitados)) {
            return camino;
        }
        return null;
    }

    private boolean dfs(int nodo, int destino, Map<Integer, List<Camion>> grafoResidual, List<Camion> camino, Set<Integer> visitados) {
        if (nodo == destino) {
            return true;
        }
        visitados.add(nodo);
        for (Camion adjEdge : grafoResidual.getOrDefault(nodo, new ArrayList<>())) {
            int vecino = adjEdge.getDestino();
            if (!visitados.contains(vecino) && adjEdge.getLibros() > 0) {
                camino.add(adjEdge);
                if (dfs(vecino, destino, grafoResidual, camino, visitados)) {
                    return true;
                }
                camino.remove(camino.size() - 1);
            }
        }
        return false;
    }

    private Map<Integer, List<Camion>> generarGrafoResidual(Map<Integer, List<Camion>> listaAdj) {
        Map<Integer, List<Camion>> grafoResidual = new HashMap<>();
        for (Map.Entry<Integer, List<Camion>> entrada : listaAdj.entrySet()) {
            int nodoFuente = entrada.getKey();
            List<Camion> adyacentes = entrada.getValue();
            for (Camion camion : adyacentes) {
                grafoResidual.computeIfAbsent(nodoFuente, k -> new ArrayList<>()).add(
                    new Camion(camion.getFuente(), camion.getTipoFuente(), camion.getDestino(), camion.getTipoDestino(), camion.getLibros())
                );
                grafoResidual.computeIfAbsent(camion.getDestino(), k -> new ArrayList<>()).add(
                    new Camion(camion.getDestino(), camion.getTipoDestino(), camion.getFuente(), camion.getTipoFuente(), 0)
                );
            }
        }
        return grafoResidual;
    }

    private Set<Integer> listarElem(Map<Integer, List<Camion>> listaAdj, String tipoElem) {
        Set<Integer> elementos = new HashSet<>();
        for (List<Camion> lista : listaAdj.values()) {
            if (!lista.isEmpty()) {
                Camion camion = lista.get(0);
                int elem = camion.getFuente();
                String tipo = camion.getTipoFuente();
                if (tipo.equals(tipoElem)) {
                    elementos.add(elem);
                }
            }
        }
        return elementos;
    }

    private Map<Integer, List<Camion>> listaAdyacencia(List<Camion> listaCamiones, int librosPorCamion, int librosPorBodega) {
        Map<Integer, List<Camion>> respuesta = new HashMap<>();
        for (Camion camion : listaCamiones) {
            respuesta.computeIfAbsent(camion.getFuente(), k -> new ArrayList<>());

            if (camion.getTipoFuente().equals("F")) {
                camion.setLibros(librosPorCamion);
                respuesta.get(camion.getFuente()).add(camion);
            } else if (camion.getTipoFuente().equals("B")) {
                Camion haciaBodegaPrima = new Camion(camion.getFuente(), camion.getTipoFuente(), -camion.getFuente(), "B");
                haciaBodegaPrima.setLibros(librosPorBodega);
                respuesta.get(camion.getFuente()).add(haciaBodegaPrima);

                camion.setFuente(-camion.getFuente());
                camion.setLibros(librosPorCamion);
                respuesta.computeIfAbsent(camion.getFuente(), k -> new ArrayList<>());
                respuesta.get(camion.getFuente()).add(camion);
            }
        }
        return respuesta;
    }

    public int countVertex(List<Camion> edgesList) {
        return findMaxVertex(edgesList) + 1;
    }

    public int findMaxVertex(List<Camion> graph) {
        int max = 0;
        for (Camion edge : graph) {
            max = Math.max(max, edge.getFuente());
            max = Math.max(max, edge.getDestino());
        }
        return max;
    }
}