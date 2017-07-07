import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belisario Panay, Americo Ferrada on 7/3/17.
 *
 */
class TwoAproximation {
    private int vertexCoverSize;

    /**
     * Algoritmo de 2-Aproximacion, que itera mientres queden aristas.
     * Se guarda la lista de vertices que es un cubrimiento en la variable
     * vertexCover.
     * @param g grafo.
     */
    TwoAproximation(Graph g) {

        List<Edge> currentEdges = g.getE();
        List<Vertex> vertexCover = new ArrayList<>();
        int position =0;
        int edgesCount = currentEdges.size();
        while (edgesCount > 0) {

            while ((position < currentEdges.size()) && currentEdges.get(position) == null)
                position++;

            if (position >= currentEdges.size())
                break;
            Edge edge = currentEdges.get(position);
            Vertex u = g.getVertexIn(edge.src);
            Vertex v = g.getVertexIn(edge.tgt);
            vertexCover.add(u);
            vertexCover.add(v);

            Edge edgeToDelete;
            for (int i = u.first; i <= u.last; i++) {
                edgeToDelete = currentEdges.get(i);
                if (edgeToDelete == null)
                    continue;
                /*Se borra la la copia de la arista (ej: (0,1)  (1,0) )*/
                currentEdges.set(edgeToDelete.cmp,null);
                currentEdges.set(i,null);
                edgesCount-=2;

            }


            for (int j = v.first; j <= v.last; j++) {
                edgeToDelete = currentEdges.get(j);
                /*Se borra la la copia de la arista (ej: (0,1)  (1,0) )*/
                if (edgeToDelete == null)
                    continue;
                currentEdges.set(edgeToDelete.cmp,null);
                currentEdges.set(j,null);
                edgesCount-=2;
            }

        }
        vertexCoverSize = vertexCover.size();

//        for (Edge e : currentEdges)
//            System.out.println(e);
    }

    /**
     * @return retorna el tamano de la solucion
     */
    int getVertexCoverSize() {
        return vertexCoverSize;
    }

}
