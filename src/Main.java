import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }


    /**
     * Este codigo pertenece al profesor José Fuentes Sepúlveda (https://users.dcc.uchile.cl/~jfuentess/),
     * fue traducido desde C.
     * La funcion recibe un nombre de archivo (donde hay un grafo con el formato especificado
     * en https://users.dcc.uchile.cl/~jfuentess/datasets/graphs.php) y devuelve un objeto
     * Graph con toda la informacion del archivo.
     * @param fileName Nombre de Archivo
     * @return Grafo
     */
    private Graph readGraphFromFile(String fileName) {
        BufferedReader br = null;
        FileReader fr = null;
        Graph g = null;

        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String currentLine;

            /*Cantidad de vertices y aristas que se van a leer*/
            currentLine = br.readLine();
            int n = Integer.parseInt(currentLine);
            currentLine = br.readLine();
            int m = Integer.parseInt(currentLine);

            /*Se crea el grafo vacio, que tendra n vertices y m aristas*/
            g = new Graph(n, m);
            int target = 0;
            int source = 0;
            int edgeNum = 0;
            int otherSource = -1;

            while ((currentLine = br.readLine()) != null) {
                String[] auxLine = currentLine.split(" ");
                source = Integer.parseInt(auxLine[0]);
                target = Integer.parseInt(auxLine[1]);
                g.changeEdge(m,source,target,-1);

                if (m==0) {
                    g.setFirstTo(source,m);
                }
                else if(source != (otherSource = g.getE().get(m-1).src)) {
                    g.setLastTo(otherSource,m-1);
                    g.setFirstTo(source,m);

                }
                m++;
            }
            g.setLastTo(otherSource,m-1);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        int m = 0;
        if (g != null)
            m = g.getE().size();

        for (int i =0;i < 2*m; i++) {
            Edge currentEdge = g.getEdgeIn(i);
            Vertex target = g.getVertexIn(currentEdge.tgt);
            int cmp = -1;

            if (currentEdge.cmp != -1)
                continue;

            int j = 0;
            for (j = target.first; j <= target.last; j++) {
                Edge jEdge = g.getEdgeIn(j);
                if ((jEdge.cmp == -1) && (jEdge.tgt == jEdge.src) && (i!=j))
                    cmp = j;
            }

            if (cmp != -1) {
                currentEdge.cmp = cmp;
                g.getEdgeIn(cmp).cmp = i;
            }
        }



        return g;
    }
}


