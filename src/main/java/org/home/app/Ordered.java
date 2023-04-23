package org.home.app;


import java.util.*;

public class Ordered {

    public static final Graph graph = new Graph();

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        Collection<Node> nodes = loadGraph();

        determineBounds(nodes);

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Execution time: " + timeElapsed);

        displayResults(nodes);
    }


    private static Collection<Node> loadGraph() {

        int nodeNb = 1;
        Scanner sc = new Scanner(System.in);

        String[] values = sc.nextLine().split(" ");
        String root = values[0];
        graph.addNode(root, nodeNb);

        while (sc.hasNextLine()) {

            values = sc.nextLine().split(" ");
            String child = values[0];
            String father = values[1];

            Node childNode = graph.addNode(child, ++nodeNb);
            childNode.parent(graph.get(father));
        }

        return graph.nodes();
    }


    private static void determineBounds(Collection<Node> nodes) {

        nodes.forEach(node -> {
            if (node.getParent() != null) {
                node.getParent().updateUpper(node.getNumber());
            }
        });
    }


    private static void displayResults(Collection<Node> nodes) {
        List<Node> sorted = new ArrayList<>(nodes)
                .stream()
                .sorted(Comparator.comparingInt(Node::getLower))
                .toList();

        sorted.forEach(node -> System.out.printf("%2s [%2s, %2s]%n", node.getName(), node.getLower(), node.getUpper()));
    }

}


