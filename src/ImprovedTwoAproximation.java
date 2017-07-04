import java.util.ArrayList;
import java.util.List;

/**
 * Created by belisariops on 7/4/17.
 */
public class ImprovedTwoAproximation {
    List<Vertex> vertexCover;

    public ImprovedTwoAproximation(Graph g) {
        List<Vertex> currentVertices = g.getV();
        List<Edge> currentEdges = g.getE();
        vertexCover = new ArrayList<Vertex>();

        while (currentEdges.size() > 0) {
            Vertex u =MaximumDegreeHeuristic.getVertexWithMaxDegree(currentVertices);
            Vertex v = getMaximumDegreeNeighbour(u,currentVertices,currentEdges);
            currentVertices.remove(u.index);
            currentVertices.remove(v.index);
            vertexCover.add(u);
            vertexCover.add(v);

            for (int i=u.first; i<=u.last; i++) {
                currentEdges.remove(currentEdges.get(i).cmp);
                currentEdges.remove(i);
            }

            for (int j=v.first; j<=v.last; j++) {
                currentEdges.remove(currentEdges.get(j).cmp);
                currentEdges.remove(j);
            }

        }
    }

    private Vertex getMaximumDegreeNeighbour(Vertex u, List<Vertex> currentVertices, List<Edge> currentEdges) {
        int maximumDegree = -1;
        Vertex maxNeighbourDegreeVertex = null;
        int index = 0;
        int neighbourDegree;
        for (int i=u.first;i<=u.last;i++) {
            Edge edge = currentEdges.get(i);
            int neighbourIndex  = edge.tgt;
            if (neighbourIndex == edge.src)
                continue;

            Vertex neighbour = currentVertices.get(neighbourIndex);
            neighbourDegree = neighbour.last - neighbour.first + 1;

            if (neighbourDegree > maximumDegree) {
                maximumDegree = neighbourDegree;
                neighbour.index = neighbourIndex;
                maxNeighbourDegreeVertex = neighbour;
            }

        }

        return maxNeighbourDegreeVertex;
    }


}
