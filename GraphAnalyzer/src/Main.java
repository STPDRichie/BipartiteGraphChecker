import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File input = new File("../input.txt");
            Scanner scanner = new Scanner(input);

            var nodeCount = Integer.parseInt(scanner.nextLine());
            GraphAnalyzer graphAnalyzer = new GraphAnalyzer(nodeCount);

            for (var i = 0; i < nodeCount; i++)
                graphAnalyzer.addNodeEdges(i + 1, scanner.nextLine());

            FileWriter writer = new FileWriter("../output.txt");
            if (graphAnalyzer.isGraphBipartite()) {
                String result = "Y" +
                        graphAnalyzer.getPart(NodePart.FIRST) +
                        " 0" +
                        graphAnalyzer.getPart(NodePart.SECOND);

                writer.write(result);
            }
            else {
                writer.write("N");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Writing failed...");
            e.printStackTrace();
        }
    }
}
