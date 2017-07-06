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
        a.set(2,null);/*
        for (Integer i : a)
            System.out.println(i);*/


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
        e4.src = 0;
        e4.tgt = 0;
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);


/*
        for (Edge e : edges)
            System.out.println("("+e.src+","+e.tgt+")");*/

        GraphGenerator generator = new GraphGenerator();
        Graph g = generator.create((int)Math.pow(2,13) ,0.1f);
        /*Graph g2 = new Graph(g);
        Graph g3 = new Graph(g);
        Graph g4 = new Graph(g);

        TwoAproximation alg1 = new TwoAproximation(g);
        List<Vertex> vert = alg1.getVertexCover();

        MaximumDegreeHeuristic alg2 = new MaximumDegreeHeuristic(g2);
        List<Vertex> vert2 = alg2.getVertexCover();

        ImprovedTwoAproximation alg3 = new ImprovedTwoAproximation(g3);
        List<Vertex> vert3 = alg3.getVertexCover();
        for (int i =0;i < vert.size(); i++)
            System.out.println(vert.get(i).first);

        System.out.println("Cantidad de vertices: "+ g.getV().size() + "  Vertices que cubren: " + vert.size());
        System.out.println("Cantidad de vertices: "+ g.getV().size() + "  Vertices que cubren: " + vert2.size());
        System.out.println("Cantidad de vertices: "+ g.getV().size() + "  Vertices que cubren: " + vert3.size());*/





    }
}


