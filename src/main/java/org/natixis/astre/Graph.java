package org.natixis.astre;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Graph {

    Map<String, Node> graphMap = new HashMap<>();

    public Node addNode(String name) {

        if (!graphMap.containsKey(name)) {
            Node childNode = new Node(name);
            graphMap.put(name, childNode);
            return childNode;
        }

        return graphMap.get(name);
    }

    public Node addNode(String name, int nodeNb) {
        Node node = new Node(name, nodeNb);
        graphMap.put(name, node);
        return node;
    }

    public Node get(String key) {
        return graphMap.get(key);
    }

    public Collection<Node> nodes() {
        return this.graphMap.values();
    }
}
