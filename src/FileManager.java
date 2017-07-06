import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Belisario Panay, Americo Ferrada on 7/3/17.
 */
public class FileManager {


    /**
     * Este codigo pertenece al profesor José Fuentes Sepúlveda (https://users.dcc.uchile.cl/~jfuentess/),
     * fue traducido desde C.
     * La funcion recibe un nombre de archivo (donde hay un grafo con el formato especificado
     * en https://users.dcc.uchile.cl/~jfuentess/datasets/graphs.php) y devuelve un objeto
     * Graph con toda la informacion del archivo.
     * @param fileName Nombre de Archivo
     * @return Grafo
     */
    public Graph readGraphFromFile(String fileName) {
        BufferedReader br = null;
        FileReader fr = null;
        Graph g = null;
        int m =0;

        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String currentLine;

            /*Cantidad de vertices y aristas que se van a leer*/
            currentLine = br.readLine();
            int n = Integer.parseInt(currentLine);
            currentLine = br.readLine();
            m = Integer.parseInt(currentLine);
            /*Se crea el grafo vacio, que tendra n vertices y m aristas*/
            g = new Graph(n, m);
            int target = 0;
            int source = 0;
            int otherSource = -1;



            for (int edgeNum = 0; edgeNum < m; edgeNum++) {
                currentLine = br.readLine();
                String[] auxLine = currentLine.split(" ");
                source = Integer.parseInt(auxLine[0]);
                target = Integer.parseInt(auxLine[1]);
                g.changeEdge(edgeNum,source,target,-1);

                if (edgeNum==0) {
                    g.setFirstTo(source,edgeNum);
                }
                else if(source != (otherSource = g.getE().get(edgeNum-1).src)) {
                    g.setLastTo(otherSource,edgeNum-1);
                    g.setFirstTo(source,edgeNum);

                }
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

        for (int i =0;i < 2*m; i++) {
            Edge currentEdge = g.getEdgeIn(i);
            Vertex target = g.getVertexIn(currentEdge.tgt);
            int cmp = -1;

            if (currentEdge.cmp != -1)
                continue;

            int j = 0;
            for (j = target.first; j <= target.last; j++) {
                Edge jEdge = g.getEdgeIn(j);
                if ((jEdge.cmp == -1) && (jEdge.tgt == currentEdge.src) && (i!=j))      
                    cmp = j;
            }

            if (cmp != -1) {
                g.getEdgeIn(i).cmp = cmp;
                g.getEdgeIn(cmp).cmp = i;
            }
        }



        return g;
    }
}
