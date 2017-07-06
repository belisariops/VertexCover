import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belisario Panay, Americo Ferrada on 7/3/17.
 * Representa al grafo, posee dos listas, una de vertices y otra de aristas.
 */
class Graph {
    private List<Vertex> V;
    private List<Edge> E;

    /**
     * Crea una grafo vacio, con aristas  y vertices inicializados.
     * @param numV v
     * @param numE e
     */
    Graph(int numV, int numE) {
        V = new ArrayList<>(numV);
        E = new ArrayList<>(2*numE);

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

    /**
     * Crea una copia del grafo g.
     * @param g g
     */
    Graph(Graph g) {
        List<Vertex> verticesToCopy = g.getV();
        List<Edge> edgesToCopy = g.getE();
        int n = verticesToCopy.size();
        int m = edgesToCopy.size();
        this.V = new ArrayList<>(n);
        this.E = new ArrayList<>(m);
        Vertex vCopy;

        for(Vertex v : verticesToCopy){
            vCopy = new Vertex();
            vCopy.first = v.first;
            vCopy.last = v.last;
            vCopy.index = v.index;
            this.V.add(vCopy);
        }

        Edge eCopy;
        for(Edge e: edgesToCopy){
            eCopy = new Edge();
            eCopy.src = e.src;
            eCopy.tgt = e.tgt;
            eCopy.cmp = e.cmp;
            this.E.add(eCopy);
        }

    }

    /**
     * Crea un grafo a partir de dos listas, una de vertices  otra de aristas.
     * Es llamado por el generador aleatorio de grafos.
     * @param vertices vertice
     * @param edges arista
     */
    Graph(List<Vertex> vertices,List<Edge> edges) {
        this.V = vertices;
        this.E = edges;
    }

    /**
     *
     * @param position pos
     * @param first first
     */
    void setFirstTo(int position, int first) {
        Vertex vertex = this.V.get(position);
        vertex.first = first;
    }

    void setLastTo(int position, int last) {
        Vertex vertex = this.V.get(position);
        vertex.last = last;
    }

    void changeEdge(int position, int src, int tgt, int cmp) {
        Edge edge = this.E.get(position);
        edge.src = src;
        edge.tgt = tgt;
        edge.cmp = cmp;
    }

    Vertex getVertexIn(int position) {
        return V.get(position);
    }

    Edge getEdgeIn(int position) {
        return E.get(position);
    }

    List<Edge> getE() {

        return E;
    }

    List<Vertex> getV() {
        return V;
    }
}
