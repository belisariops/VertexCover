import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by belisariops on 7/4/17.
 */
public class GraphGenerator {

    public Graph create(int n, int p) {
        List<Vertex> vertices = new ArrayList<Vertex>(n);
        for (int i=0; i<n; i++)
            vertices.add(new Vertex());

        List<Edge> edges = new ArrayList<Edge>();
        Random r = new Random();
        float chance;

        /*Se crean las aristas con probabilidad p*/
        Edge randEdge;
        Edge reflexEdge;
        List<Integer> verticesDegrees = new ArrayList<Integer>();

        for (int l = 0; l < n; l++)
            verticesDegrees.add(0);

        /*Segun enunciado*/
        for(int j=0; j < n ; j++) {
            for (int k = j+1; k < n; k++) {
                chance = r.nextFloat();
                if (chance < p) {
                    randEdge = new Edge();
                    randEdge.src = j;
                    randEdge.tgt = k;
                    randEdge.cmp = -1;
                    verticesDegrees.set(j,verticesDegrees.get(j)+1);
                    edges.add(randEdge);

                    reflexEdge = new Edge();
                    reflexEdge.src = k;
                    reflexEdge.tgt = j;
                    reflexEdge.cmp = -1;
                    verticesDegrees.set(k,verticesDegrees.get(k)+1);
                    edges.add(reflexEdge);
                }
            }
        }

        List<List<Edge>> buckets;
        int size = edges.size();
        for (int h=0; h<2; h++) {
            buckets = new ArrayList<List<Edge>>(size);

            for (int i=0; i < n; i++)
                buckets.set(i,new ArrayList<Edge>());


            for (int i=1; i <= n; i++)
                if (h==0)
                    buckets.get((int)Math.floor(n*(edges.get(i).src))).add(edges.get(i));
                else
                    buckets.get((int)Math.floor(n*(edges.get(i).tgt))).add(edges.get(i));

            for (int i=0; i<n; i++) {
                List<Edge> sortingList = buckets.get(i);
                for (int j=1; j<sortingList.size(); j++) {
                    int k=0;
                    Edge temp = sortingList.get(i);
                    if (h==0)
                        for (k = j-1; j>=0 && temp.src < sortingList.get(k).src; k++)
                            sortingList.set(k+1,sortingList.get(k));
                    else
                        for (k = j-1; j>=0 && temp.tgt < sortingList.get(k).tgt; k++)
                            sortingList.set(k+1,sortingList.get(k));
                    sortingList.set(k+1,temp);

                }
            }
            edges = new ArrayList<Edge>();
            for (int i =0; i<n; i++) {
                edges.addAll(buckets.get(i));
            }


        }








       /* for (int i = 0; i < (n*(n-1))/2; i++) {
            chance = r.nextFloat();

            if (chance < p) {

            }
        }*/
       /*List<Vertex> auxVertices = vertices;
       int index = 0;
       int auxIndex = 1;
       int currentStart = 0;
       int neighbours;
       Edge auxEdge;
       for (Vertex vertex : vertices) {
           auxVertices.remove(index);
           vertex.first = currentStart;
           neighbours = 0;
           for (Vertex aux: auxVertices) {


                chance = r.nextFloat();

                if (chance < p) {
                    auxEdge = new Edge();
                    auxEdge.src = index;
                    auxEdge.tgt = auxIndex;
                    neighbours++;
                    edges.add(auxEdge);
                }
                auxIndex++;
           }
           vertex.last = currentStart + neighbours - 1;
           currentStart = currentStart + neighbours;
           index++;
       }*/
       return null;
    }


}
