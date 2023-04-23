package org.home.app;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Node {

    String name;
    int number;

    int lower;
    int upper;

    Node parent;
    List<Node> children = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, int number) {
        this.name = name;
        this.number = number;
        this.lower = this.upper = number;
    }

    public Node number(int number) {
        this.number = number;
        this.lower = this.upper = number;
        return this;
    }

    public Node parent(Node parent) {
        this.parent = parent;
        return this;
    }

    public void updateUpper(int childNumber) {
        if (this.upper < childNumber) {
            this.upper = childNumber;
            if (this.parent != null) {
                this.parent.updateUpper(childNumber);
            }
        }
    }
}
