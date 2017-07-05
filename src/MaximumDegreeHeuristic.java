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

    public List<Vertex> getVertexCover() {
        return vertexCover;
    }

    public int getVertexCoverSize() {
        return vertexCover.size();
    }
}
