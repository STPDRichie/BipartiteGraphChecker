import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var graphAnalyzer = new GraphAnalyzer();

        try {
            File input = new File("../input.txt");
            Scanner scanner = new Scanner(input);

            var nodeCount = Integer.parseInt(scanner.nextLine());
            GraphAnalyzer graph = new GraphAnalyzer(nodeCount);

            for (var i = 0; i < nodeCount; i++)
                graph.addNodeEdges(i + 1, scanner.nextLine());

            graphAnalyzer = graph;
        } catch (FileNotFoundException e) {
            System.out.println("File not found...");
            e.printStackTrace();
        }

        if (!graphAnalyzer.isGraphBipartite()) {
            try {
                FileWriter writer = new FileWriter("../output.txt");
                writer.write("N");
                writer.close();
                return;
            } catch (IOException e) {
                System.out.println("Writing failed...");
                e.printStackTrace();
            }
        }

        try {
            String result = "Y" +
                    graphAnalyzer.getPart(NodePart.FIRST) +
                    " 0" +
                    graphAnalyzer.getPart(NodePart.SECOND);

            FileWriter writer = new FileWriter("../output.txt");
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            System.out.println("Writing failed...");
            e.printStackTrace();
        }
    }
}
