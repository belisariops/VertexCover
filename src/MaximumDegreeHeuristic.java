import java.util.ArrayList;
import java.util.List;

/**
 * Created by belisariops on 7/4/17.
 */
public class MaximumDegreeHeuristic {
    private List<Vertex> vertexCover;

    public MaximumDegreeHeuristic(Graph g) {
        List<Edge> currentEdges = g.getE();
        List<Vertex> currentVertices = g.getV();
        vertexCover = new ArrayList<Vertex>();
        while (currentEdges.size() > 0) {
            Vertex u = getVertexWithMaxDegree(currentVertices);
            currentVertices.remove(u.index);
            vertexCover.add(u);

            for (int i = u.first; i <= u.last; i++) {
                currentEdges.remove(currentEdges.get(i).cmp);
                currentEdges.remove(i);
            }


        }

    }

    public static Vertex getVertexWithMaxDegree(List<Vertex> currentVertices) {
        int maxDegree = -1;
        int localDegree;
        Vertex maxDegreeVertex = null;
        int index = 0;
        for (Vertex vertex : currentVertices) {
            localDegree = vertex.last - vertex.first + 1;
            if (localDegree > maxDegree) {
                vertex.index = index;
                maxDegreeVertex = vertex;
                maxDegree = localDegree;

            }
            index++;
        }

        return maxDegreeVertex;
    }

    public List<Vertex> getVertexCover() {
        return vertexCover;
    }

    public int getVertexCoverSize() {
        return vertexCover.size();
    }
}
