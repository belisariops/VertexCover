import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /*FileManager fManager = new FileManager();
        Graph g = fManager.readGraphFromFile("unMillon.pg");

        TwoAproximation algorithm = new TwoAproximation(g);
        System.out.println(algorithm.getVertexCoverSize());*/
        List<Integer> a = new ArrayList<Integer>(4);
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(1, 4);
        for (Integer i : a)
            System.out.println(i);


        List<Edge> edges = new ArrayList<Edge>(4);
        Edge e1 = new Edge();
        Edge e2 = new Edge();
        Edge e3 = new Edge();
        Edge e4 = new Edge();
        e1.src = 1;
        e1.tgt = 2;
        e2.src = 1;
        e2.tgt = 1;
        e3.src = 2;
        e3.tgt = 2;
        e4.src = 2;
        e4.tgt = 1;
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        int n = 4;
        List<List<Edge>> buckets;
        int size = edges.size();
        for (int h = 0; h < 2; h++) {
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
                    int k = 0;
                    Edge temp = sortingList.get(j);
                    if (h == 0)
                        for (k = j - 1; j >= 0 && temp.src < sortingList.get(k).src; k++)
                            sortingList.set(k + 1, sortingList.get(k));
                    else
                        for (k = j - 1; j >= 0 && temp.tgt < sortingList.get(k).tgt; k++)
                            sortingList.set(k + 1, sortingList.get(k));
                    sortingList.set(k + 1, temp);

                }
            }
            edges = new ArrayList<Edge>();
            for (int i =0; i<n; i++) {
                edges.addAll(buckets.get(i));
            }

        }

        for (Edge e : edges)
            System.out.println("("+e.src+","+e.tgt+")");


    }
}


