import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * Created by Belisario Panay, Americo Ferrada on 7/5/17
 */
public class Main {

    public static void main(String[] args) {

        Timer timer = new Timer();
        timer.start();
        Path path;
        Path pathData;

        Graph g1,g2,g3;
        GraphGenerator generator = new GraphGenerator();

        TwoAproximation twoAproximation;
        MaximumDegreeHeuristic maximumDegreeHeuristic;
        ImprovedTwoAproximation improvedTwoAproximation;

        int n;
        double p;
        double[] pArray = new double[]{4, 16, 64, 256, 1024};
        String fileName;
        String fileNameData;
        double constructionTime,twoAproximationTime,maximumDegreeHeuristicTime,improvedTwoAproximationTime;
        int twoAproximationResult,maximumDegreeHeuristicResult,improvedTwoAproximationResult,graphSize,edgesSize;

        for (int i= 15 ; i<=15; i++) {
            n = (int) Math.pow(2, i);
            fileName = "Experimento_i" + i + ".txt";
            fileNameData = "Experimento_i" + i + "Data.csv";
            path = Paths.get(fileName);
            pathData = Paths.get(fileNameData);
            try (BufferedWriter writer = Files.newBufferedWriter(path);
                 BufferedWriter writerData = Files.newBufferedWriter(pathData)) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 5; k++) {
                        p = pArray[k]/n;
                        timer.start();
                        g1 = generator.create(n, p);
                        constructionTime = timer.stop();
                        graphSize = g1.getV().size();
                        edgesSize = g1.getE().size();
                        g2 = new Graph(g1);
                        g3 = new Graph(g1);


                        timer.start();
                        twoAproximation = new TwoAproximation(g1);
                        twoAproximationTime = timer.stop();
                        twoAproximationResult = twoAproximation.getVertexCoverSize();
                        g1= null;
                        twoAproximation =null;


                        timer.start();
                        maximumDegreeHeuristic = new MaximumDegreeHeuristic(g2);
                        maximumDegreeHeuristicTime = timer.stop();
                        maximumDegreeHeuristicResult = maximumDegreeHeuristic.getVertexCoverSize();
                        g2 = null;
                        maximumDegreeHeuristic = null;


                        timer.start();
                        improvedTwoAproximation = new ImprovedTwoAproximation(g3);
                        improvedTwoAproximationTime = timer.stop();
                        improvedTwoAproximationResult = improvedTwoAproximation.getVertexCoverSize();
                        g3 = null;
                        improvedTwoAproximation = null;

                        writerData.write(n+","+edgesSize+","+j+","+p+","+constructionTime+","+twoAproximationTime+","+maximumDegreeHeuristicTime+","+improvedTwoAproximationTime+","+twoAproximationResult+","+maximumDegreeHeuristicResult+","+improvedTwoAproximationResult);
                        writerData.newLine();
                        writer.write("Tiempo Construccion Grafo con p="+p+", "+graphSize+" nodos, "+edgesSize/2+" aristas, construido en "+constructionTime+" segundos.");
                        writer.newLine();
                        writer.newLine();
                        writer.write("2-Aproximacion:");
                        writer.newLine();
                        writer.write("                 Cantidad de vertices solucion:"+twoAproximationResult);
                        writer.newLine();
                        writer.write("                 Tiempo de ejecucion:"+twoAproximationTime+" segundos.");
                        writer.newLine();
                        writer.newLine();
                        writer.write("Heuristica de grado mayor:");
                        writer.newLine();
                        writer.write("                 Cantidad de vertices solucion:"+maximumDegreeHeuristicResult);
                        writer.newLine();
                        writer.write("                 Tiempo de ejecucion:"+maximumDegreeHeuristicTime+" segundos.");
                        writer.newLine();
                        writer.newLine();
                        writer.write("2-Aproximacion mejorada:");
                        writer.newLine();
                        writer.write("                 Cantidad de vertices solucion:"+improvedTwoAproximationResult);
                        writer.newLine();
                        writer.write("                 Tiempo de ejecucion:"+improvedTwoAproximationTime+" segundos.");
                        writer.newLine();
                        writer.newLine();
                        writer.write("------------------------------------------------------------------------------------------------------------------------------------");
                        writer.newLine();

                    }
                    writer.newLine();
                    writer.write("***********************************************************************************************************************************");
                    writer.write("***********************************************************************************************************************************");
                    writer.newLine();
                    writer.newLine();

                }
                writer.close();
                writerData.close();

            } catch (IOException e) {
                e.printStackTrace();
            } 
        }

    }
}


