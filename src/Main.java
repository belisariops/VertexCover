import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        FileManager fManager = new FileManager();
        Graph g = fManager.readGraphFromFile("unMillon.pg");

        TwoAproximation algorithm = new TwoAproximation(g);
        System.out.println(algorithm.getVertexCoverSize());
    }


}


