import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by belisariops on 7/4/17.
 */
public class GraphGenerator {

    public Graph create(int n, double p) {
        int f=0;
        List<Vertex> vertices = new ArrayList<Vertex>(n);

        List<Edge> edges = new ArrayList<Edge>();
        Random r = new Random();
        float chance;
        int m = 0;

        /*Se crean las aristas con probabilidad p*/
        Edge randEdge;

        List<List<Edge>> buckets = new ArrayList<List<Edge>>(n);

        for (int i =0; i<n; i++)
            buckets.add(new ArrayList<Edge>(n-1));


        /*Segun enunciado*/
        for(int j=0; j < n ; j++) {
            for (int k = j+1; k < n; k++) {
                chance = r.nextFloat();
                if (chance < p) {
                    randEdge = new Edge();
                    randEdge.src = j;
                    randEdge.tgt = k;
                    randEdge.cmp = -1;
                    buckets.get(j).add(randEdge);

                }
            }
        }

        /*Se crean las aristas con las dos direcciones (ej: (u,v) y (v,u)), para la lista de adyacencia*/
        boolean foundEdge = false;

        for (int i =0; i<n; i++) {
            m = buckets.get(i).size();
            for (int j =0; j < m; j++) {
                Edge bucketEdge = buckets.get(i).get(j);
                if (bucketEdge.src == bucketEdge.tgt)
                    continue;
                List<Edge> otherBucket = buckets.get(bucketEdge.tgt);
                int otherBucketSize = otherBucket.size();

                for (int k =0; k < otherBucketSize; k++) {
                    Edge thisEdge = otherBucket.get(k);
                    if (thisEdge.tgt == bucketEdge.src) {
                        foundEdge = true;
                        break;
                    }
                }

                Edge mirrorEdge = new Edge();
                mirrorEdge.src = bucketEdge.tgt;
                mirrorEdge.tgt = bucketEdge.src;
                mirrorEdge.cmp = -1;

                if (foundEdge == false)
                    buckets.get(bucketEdge.tgt).add(mirrorEdge);
                foundEdge = false;
            }
        }
        Vertex v;
        int position=0;
        for (List<Edge> bucket : buckets) {
            v = new Vertex();
            v.first = position;
            v.last = bucket.size() + position - 1;

            if (v.first > v.last) {
                v.first =-1;
                v.last = -2;
                f++;
                vertices.add(v);
                continue;
            }
            vertices.add(v);
            edges.addAll(bucket);
            position = v.last+1;
        }


        /*for (Vertex vert : vertices)
            System.out.println("("+vert.first+","+vert.last+")");
        System.out.println("----------------------------------------");*/


        m = edges.size();
        Vertex target;
        boolean cmpFound = false;
        for (int i=0; i<m; i++) {
            target = vertices.get(edges.get(i).tgt);

            for (int j = target.first; j <= target.last; j++) {
                if (j < 0)
                    continue;
                if (edges.get(j).cmp != -1) {
                    cmpFound = true;
                    continue;
                }
                if (edges.get(j).tgt == edges.get(i).src) {
                    edges.get(j).cmp = i;
                    edges.get(i).cmp = j;
                    cmpFound = true;
                    break;
                }
            }
            if (!cmpFound)
                System.out.println("No se encontro arista espejo de ("+edges.get(i).src+","+edges.get(i).tgt+")");
            cmpFound = false;
        }

        /*for (Edge e : edges)
            System.out.println("("+e.src+","+e.tgt+") cmp="+ e.cmp);*/

        Graph g = new Graph(vertices,edges);







        /*Bucket-Sort aristas*/
        /*List<List<Edge>> buckets;
        int size = edges.size();
        for (int h = 0; h < 1; h++) {
            buckets = new ArrayList<List<Edge>>(size);

            for (int i = 0; i < n; i++)
                buckets.add(new ArrayList<Edge>());


            for (int i = 0; i <= n-1; i++)
                if (h == 0)
                    buckets.get((edges.get(i).src)).add(edges.get(i));
                else
                    buckets.get( (edges.get(i).tgt)).add(edges.get(i));

            for (int i = 0; i < n; i++) {
                List<Edge> sortingList = buckets.get(i);
                for (int j = 1; j < sortingList.size(); j++) {
                    int k;
                    Edge temp = sortingList.get(j);
                    if (h == 0)
                        for (k = j - 1; k >= 0 && temp.tgt < sortingList.get(k).tgt; k--)
                            sortingList.set(k + 1, sortingList.get(k));
                    else
                        for (k = j - 1; k >= 0 && temp.tgt < sortingList.get(k).tgt; k--)
                            sortingList.set(k + 1, sortingList.get(k));
                    sortingList.set(k + 1, temp);

                }
            }
            edges = new ArrayList<Edge>();
            for (int i =0; i<n; i++) {
                edges.addAll(buckets.get(i));
            }
        }*/
/*

        Edge currentEdge,auxEdge;
        int target;
        int j;
        int i = 0;
        boolean found = false;
        while((currentEdge = edges.get(i)) != null) {
            target = currentEdge.tgt;
            j=i+1;

            if (target < currentEdge.src) {
                i++;
                continue;
            }

            while ((auxEdge = edges.get(j))!= null) {
                if (auxEdge.src == target) {
                    while (auxEdge.tgt != currentEdge.src ) {
                        if (auxEdge.src != currentEdge.tgt) {
                            Edge e = new Edge();
                            e.src = currentEdge.tgt;
                            e.tgt = currentEdge.src;
                            try {
                                edges.add(j,e);
                            }
                            catch (IndexOutOfBoundsException error) {
                                edges.add(e);
                            }
                            break;
                        }



                    }
                }
                else {
                    j++;
                    continue;
                }
                break;

            }
            i++;
        }
*/





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
       //System.out.println(f);
       return g;
    }


}
