import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belisario Panay, Americo Ferrada on 7/4/17.
 *
 */
class ImprovedTwoAproximation {
    private int vertexCoverSize;

    /**
     * Se obtiene un covertura de vertices a partir de un grafo, utilizando el algoritmo de 2-Aproximacion mejorado.
     * @param g Grafo
     */
    ImprovedTwoAproximation(Graph g) {
        List<Vertex> currentVertices = g.getV();
        List<Edge> currentEdges = g.getE();
        List<Vertex> vertexCover = new ArrayList<>();
        int edgeCount = currentEdges.size();
        while (edgeCount > 0) {
            Vertex u = MaximumDegreeHeuristic.getVertexWithMaxDegree(currentVertices, currentEdges);
            Vertex v = getMaximumDegreeNeighbour(u, currentVertices, currentEdges);


            currentVertices.set(u.index,null);
            vertexCover.add(u);

            if (v == null) {
                if (currentVertices.size() == 1) {
                    break;
                }

                currentVertices.set(u.index,null);
                continue;

            }

            currentVertices.set(v.index,null);

            vertexCover.add(v);

            for (int i = u.first; i <= u.last; i++) {

                if (currentEdges.get(i) == null)
                    continue;
                currentEdges.set(currentEdges.get(i).cmp,null);
                currentEdges.set(i,null);
                edgeCount -=2;
            }

            for (int j = v.first; j <= v.last; j++) {
                if (currentEdges.get(j) == null)
                    continue;
                currentEdges.set(currentEdges.get(j).cmp,null);
                currentEdges.set(j,null);
                edgeCount -=2;
            }

        }

        vertexCoverSize = vertexCover.size();
    }


    /**
     * Obtiene el vecino con mayor grado a partir de un vertice del grafo.
     * @param u vertice
     * @param currentVertices lista de vertices.
     * @param currentEdges lista de aristas.
     * @return el vecino con mayor grado.
     */
    private Vertex getMaximumDegreeNeighbour(Vertex u, List<Vertex> currentVertices, List<Edge> currentEdges) {
        int maximumDegree = -1;
        Vertex maxNeighbourDegreeVertex = null;
        int neighbourDegree;
        for (int i=u.first;i<=u.last;i++) {
            if (i < 0)
                return null;
            Edge edge = currentEdges.get(i);
            if (edge == null)
                continue;
            int neighbourIndex  = edge.tgt;
            if (neighbourIndex == edge.src)
                continue;

            Vertex neighbour = currentVertices.get(neighbourIndex);
            try {
                neighbourDegree = neighbour.last - neighbour.first + 1;
            }
            catch (NullPointerException e) {
                return null;
            }
            if (neighbourDegree > maximumDegree) {
                maximumDegree = neighbourDegree;
                neighbour.index = neighbourIndex;
                maxNeighbourDegreeVertex = neighbour;
            }

        }

        return maxNeighbourDegreeVertex;
    }


    /**
     * Retorna el tamano de la solucion
     * @return size of vertex cover
     */
    int getVertexCoverSize() {
        return vertexCoverSize;
    }
}
