import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by belisariops on 7/3/17.
 */
public class Graph {
    private List<Vertex> V;
    private List<Edge> E;

    public Graph(int numV, int numE) {
        V = new ArrayList<Vertex>(numV);
        E = new ArrayList<Edge>(2*numE);

        for (int i=0; i < numV; i++) {
            V.add(new Vertex());
        }

        Edge e;
        for (int j=0; j < 2*numE; j++) {
            e = new Edge();
            e.cmp=-1;
            E.add(e);
        }

    }

    public Graph(Graph g) {
        List<Vertex> verticesToCopy = g.getV();
        List<Edge> edgesToCopy = g.getE();
        int n = verticesToCopy.size();
        int m = edgesToCopy.size();
        this.V = new ArrayList<Vertex>(n);
        this.E = new ArrayList<Edge>(m);
        Vertex v;
        Vertex vCopy;
        for (int i=0; i<n; i++) {
            vCopy = new Vertex();
            v = verticesToCopy.get(i);
            vCopy.first = v.first;
            vCopy.last = v.last;
            vCopy.index = v.index;
            this.V.add(vCopy);
        }

        Edge e;
        Edge eCopy;
        for (int j=0; j<m; j++) {
            eCopy = new Edge();
            e = edgesToCopy.get(j);
            eCopy.src = e.src;
            eCopy.tgt = e.tgt;
            eCopy.cmp = e.cmp;
            this.E.add(eCopy);
        }



    }

    public Graph(List<Vertex> vertices,List<Edge> edges) {
        this.V = vertices;
        this.E = edges;
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

    public List<Vertex> getV() {
        return V;
    }
}
