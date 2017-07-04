import java.util.ArrayList;
import java.util.List;

/**
 * Created by belisariops on 7/3/17.
 */
public class Graph {
    private List<Vertex> V;
    private List<Edge> E;

    public Graph(int numV, int numE) {
        V = new ArrayList<Vertex>();
        E = new ArrayList<Edge>();

        for (int i=0; i < numV; i++) {
            V.add(new Vertex());
        }

        for (int j=0; j < numE; j++) {
            E.add(new Edge());
        }

    }

    public void setFirstTo(int position, int first) {
        Vertex vertex = this.V.get(position);
        vertex.first = first;
    }

    public void setLastTo(int position, int last) {
        Vertex vertex = this.V.get(position);
        vertex.last = last;
    }

    public void changeEdge(int position, int src, int tgt, int cmp) {
        Edge edge = this.E.get(position);
        edge.src = src;
        edge.tgt = tgt;
        edge.cmp = cmp;
    }

    public Vertex getVertexIn(int position) {
        return V.get(position);
    }

    public Edge getEdgeIn(int position) {
        return E.get(position);
    }

    public List<Edge> getE() {
        return E;
    }
}
