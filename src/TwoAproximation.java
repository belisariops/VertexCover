import java.util.ArrayList;
import java.util.List;

/**
 * Created by belisariops on 7/3/17.
 */
public class TwoAproximation {
    private List<Vertex> vertexCover;

    public TwoAproximation(Graph g) {

        List<Edge> currentEdges = g.getE();
        vertexCover = new ArrayList<Vertex>();

        while (currentEdges.size() > 0) {
            Edge edge = currentEdges.get(0);
            Vertex u = g.getVertexIn(edge.src);
            Vertex v = g.getVertexIn(edge.tgt);
            vertexCover.add(u);
            vertexCover.add(v);

            Edge edgeToDelete;
            for (int i = u.first; i <= u.last; i++) {
                edgeToDelete = currentEdges.get(i);
                /*Se borra la la copia de la arista (ej: (0,1)  (1,0) )*/
                currentEdges.remove(edgeToDelete.cmp);
                currentEdges.remove(i);

            }


            for (int j = v.first; j <= v.last; j++) {
                edgeToDelete = currentEdges.get(j);
                /*Se borra la la copia de la arista (ej: (0,1)  (1,0) )*/
                currentEdges.remove(edgeToDelete.cmp);
                currentEdges.remove(j);

            }




        }
    }

    public List<Vertex> getVertexCover() {
        return vertexCover;
    }

    public int getVertexCoverSize() {
        return vertexCover.size();
    }

}
