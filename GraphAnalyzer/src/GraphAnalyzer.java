import java.util.*;

public class GraphAnalyzer {
    public int nodeCount;
    public HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    private NodeState[] nodeStates;
    private NodePart[] nodeParts;

    public HashSet<Integer> firstPart = new HashSet<>();
    public HashSet<Integer> secondPart = new HashSet<>();

    public GraphAnalyzer(){}

    public GraphAnalyzer(int nodeCount) {
        this.nodeCount = nodeCount;

        nodeParts = new NodePart[nodeCount];

        nodeStates = new NodeState[nodeCount];
        for (var i = 0; i < nodeCount; i++)
            nodeStates[i] = NodeState.NOT_VISITED;
    }

    public void addNodeEdges(int nodeNumber, String edges) {
        var edgesArray = edges.split(" ");
        var edgesList = new ArrayList<Integer>();

        for (int i = 0; i < edgesArray.length - 1; i++)
            edgesList.add(Integer.parseInt(edgesArray[i]));

        graph.put(nodeNumber, edgesList);
    }

    public boolean isGraphBipartite() {
        if (nodeCount <= 1) return false;

        firstPart.add(1);
        nodeParts[0] = NodePart.FIRST;

        var queue = new LinkedList<Integer>();
        queue.add(1);

        while (!queue.isEmpty()) {
            var currentNode = queue.getFirst();
            queue.removeFirst();

            nodeStates[currentNode - 1] = NodeState.VISITED;

            var currentPart = nodeParts[currentNode - 1];
            var otherPart = (currentPart == NodePart.FIRST) ? NodePart.SECOND : NodePart.FIRST;

            var adjacentNodes = graph.get(currentNode);
            for (var node : adjacentNodes) {
                if (nodeParts[node - 1] == nodeParts[currentNode - 1])
                    return false;

                nodeParts[node - 1] = otherPart;
                if (currentPart == NodePart.FIRST)
                    secondPart.add(node);
                else
                    firstPart.add(node);

                if (nodeStates[node - 1] == NodeState.NOT_VISITED)
                    queue.add(node);
            }
        }

        return true;
    }

    public String getPart(NodePart part) {
        var result = new StringBuilder();
        if (part == NodePart.FIRST) {
            for (var node : firstPart)
                result.append(" ").append(node);
        }
        else {
            for (var node : secondPart)
                result.append(" ").append(node);
        }

        return result.toString();
    }
}
