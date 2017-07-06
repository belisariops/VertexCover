import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        Timer timer = new Timer();
        timer.start();
        Path path;
        
        Graph g1,g2,g3;
        GraphGenerator generator = new GraphGenerator();

        TwoAproximation twoAproximation;
        MaximumDegreeHeuristic maximumDegreeHeuristic;
        ImprovedTwoAproximation improvedTwoAproximation;

        int n;
        double p;
        double[] pArray = new double[]{0.00005, 0.001, 0.0005, 0.001, 0.05};
        String fileName;
        double constructionTime,twoAproximationTime,maximumDegreeHeuristicTime,improvedTwoAproximationTime;

        for (int i= 10; i<=10; i++) {
            n = (int) Math.pow(2, i);
            fileName = "Experimento_i" + i + ".txt";
            path = Paths.get(fileName);
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 5; k++) {
                        p = pArray[k];
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


