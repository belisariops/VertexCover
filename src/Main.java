import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        for (int i= 10; i<=10; i++) {
            n = (int) Math.pow(2, i);
            fileName = "Experimento_i" + i + ".txt";
            fileNameData = "Experimento_i" + i + "Data.csv";
            path = Paths.get(fileName);
            pathData = Paths.get(fileNameData);
            try (BufferedWriter writer = Files.newBufferedWriter(path);
                 BufferedWriter writerData = Files.newBufferedWriter(pathData)) {
                writerData.write("NumVertices,NumGrafo,Probabilidad,tiempoContruccion,Tiempo2Aproximacion,TiempoMaximumDegree,Tiempo2ApriximacionMejorada");
                writerData.newLine();
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 5; k++) {
                        p = pArray[k]/n;
                        timer.start();
                        g1 = generator.create(n, p);
                        constructionTime = timer.stop();
                        g2 = new Graph(g1);
                        g3 = new Graph(g1);

                        timer.start();
                        twoAproximation = new TwoAproximation(g1);
                        twoAproximationTime = timer.stop();

                        timer.start();
                        maximumDegreeHeuristic = new MaximumDegreeHeuristic(g2);
                        maximumDegreeHeuristicTime = timer.stop();

                        timer.start();
                        improvedTwoAproximation = new ImprovedTwoAproximation(g3);
                        improvedTwoAproximationTime = timer.stop();

                        writerData.write(n+","+j+","+p+","+constructionTime+","+twoAproximationTime+","+maximumDegreeHeuristicTime+","+improvedTwoAproximationTime);
                        writerData.newLine();
                        writer.write("Tiempo Construccion Grafo con p="+p+", "+g1.getV().size()+" nodos, "+g1.getE().size()/2+" aristas, construido en "+constructionTime+" segundos.");
                        writer.newLine();
                        writer.newLine();
                        writer.write("2-Aproximacion:");
                        writer.newLine();
                        writer.write("                 Cantidad de vertices solucion:"+twoAproximation.getVertexCoverSize());
                        writer.newLine();
                        writer.write("                 Tiempo de ejecucion:"+twoAproximationTime+" segundos.");
                        writer.newLine();
                        writer.newLine();
                        writer.write("Heuristica de grado mayor:");
                        writer.newLine();
                        writer.write("                 Cantidad de vertices solucion:"+maximumDegreeHeuristic.getVertexCoverSize());
                        writer.newLine();
                        writer.write("                 Tiempo de ejecucion:"+maximumDegreeHeuristicTime+" segundos.");
                        writer.newLine();
                        writer.newLine();
                        writer.write("2-Aproximacion mejorada:");
                        writer.newLine();
                        writer.write("                 Cantidad de vertices solucion:"+improvedTwoAproximation.getVertexCoverSize());
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

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}


