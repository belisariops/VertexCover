import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belisario Panay, Americo Ferrada on 7/4/17.
 */
public class MaximumDegreeHeuristic {
    private List<Vertex> vertexCover;

    /**
     * Algoritmo de la Heuristica de grado mayor. Se guarda la solucion en vertexCover.
     * @param g
     */
    public MaximumDegreeHeuristic(Graph g) {
        List<Edge> currentEdges = g.getE();
        List<Vertex> currentVertices = g.getV();
        int edgesCount = currentEdges.size();
        vertexCover = new ArrayList<Vertex>();
        while (edgesCount > 0) {
            Vertex u = getVertexWithMaxDegree(currentVertices, currentEdges);
            if (u == null)
                break;
            currentVertices.set(u.index,null);
            vertexCover.add(u);

            for (int i = u.first; i <= u.last; i++) {
                if (currentEdges.get(i) == null)
                    continue;
                currentEdges.set(currentEdges.get(i).cmp,null);
                currentEdges.set(i,null);
                edgesCount-=2;
            }


        }

    }

    /**
     * Metodo utilizado por la heuristica y la 2-Aproximacion mejorada para obtener el vertice con mayor grado del grafo.
     * @param currentVertices
     * @param currentEdges
     * @return
     */
    public static Vertex getVertexWithMaxDegree(List<Vertex> currentVertices,List<Edge> currentEdges) {
        int maxDegree = -1;
        int localDegree;
        Vertex maxDegreeVertex = null;
        int index = 0;
        int nullEdges;
        for (Vertex vertex : currentVertices) {
            if (vertex == null) {
                index++;
                continue;
            }
            nullEdges =0;
            for (int i = vertex.first; i<=vertex.last; i++) {
                if (i < 0)
                    break;
                if (currentEdges.get(i) == null)
                    nullEdges++;
            }

            localDegree = vertex.last - vertex.first + 1 - nullEdges;
            if (localDegree > maxDegree) {
                vertex.index = index;
                maxDegreeVertex = vertex;
                maxDegree = localDegree;

            }
            index++;
        }

        return maxDegreeVertex;
    }

    /**
     * Retorna el arreglo de los vertices que cubren el grafo.
     * @return
     */
    public List<Vertex> getVertexCover() {
        return vertexCover;
    }

    /**
     * Retorna el tamano de la solucion
     * @return
     */
    public int getVertexCoverSize() {
        return vertexCover.size();
    }
}
