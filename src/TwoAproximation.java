import java.util.ArrayList;
import java.util.List;

/**
 * Created by belisariops on 7/3/17.
 */
public class TwoAproximation {
    private List<Vertex> vertexCover;

    public TwoAproximation(Graph g) {

        List<Edge> currentEdges = g.getE();
        List<Vertex> C = new ArrayList<Vertex>();

        while (currentEdges.size() > 0) {
            Edge edge = currentEdges.get(0);
            Vertex u = g.getVertexIn(edge.src);
            Vertex v = g.getVertexIn(edge.tgt);
            C.add(u);
            C.add(v);

            for (int i = u.first; i <= u.last; i++)
                currentEdges.remove(i);


            for (int j = v.first; j <= v.last; j++)
                currentEdges.remove(j);




        }
        this.vertexCover = C;
    }

    public List<Vertex> getVertexCover() {
        return vertexCover;
    }

    public int getVertexCoverSize() {
        return vertexCover.size();
    }

}
