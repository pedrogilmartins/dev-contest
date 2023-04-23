package org.natixis.astre;


import java.util.*;

public class UnOrdered {

    static Integer nodeNb = 1;
    static Graph graph = new Graph();


    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Collection<Node> graph = loadGraph();
        determineBounds(graph);

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Execution time: " + timeElapsed);

        displayResults(graph);
    }


    private static Collection<Node> loadGraph() {

        Node root = readNodes();
        numberNodes(root);

        return graph.nodes();
    }


    private static Node readNodes() {
        Scanner sc = new Scanner(System.in);
        Node root = new Node("root");

        while (sc.hasNextLine()) {

            String line = sc.nextLine();
            String[] values = line.split(" ");
            String child = values[0];

            Node childNode = graph.addNode(child);

            if (values.length > 1) {

                String father = values[1];
                Node fatherNode = graph.addNode(father);

                childNode.parent(fatherNode);
                fatherNode.getChildren().add(childNode);

            } else {
                childNode.setNumber(1);
                root = childNode;
            }
        }
        return root;
    }


    private static void numberNodes(Node node) {

        node.number(nodeNb++);
        List<Node> sortedChildren = node.getChildren()
                .stream()
                .sorted(Comparator.comparing(Node::getName))
                .toList();

        for (Node child : sortedChildren) {
            numberNodes(child);
        }
    }


    private static void determineBounds(Collection<Node> graph) {

        graph.forEach(node -> {
            if (node.getParent() != null) {
                node.getParent().updateUpper(node.getNumber());
            }
        });
    }


    private static void displayResults(Collection<Node> graph) {
        List<Node> nodes = new ArrayList<>(graph)
                .stream()
                .sorted(Comparator.comparingInt(Node::getLower))
                .toList();
        for (Node node : nodes) {
            System.out.printf("%2s [%2s, %2s]%n", node.getName(), node.getLower(), node.getUpper());
        }
    }
}


