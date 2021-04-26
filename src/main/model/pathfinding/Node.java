package model.pathfinding;

import model.Position;

import java.util.Objects;

// A Node that represents a position on the map with a gCost, hCost, and fCost that is calculated based on the
// source and target positions on the map. These Nodes are generated only for the purposes of pathfinding.
// Normally the map is simply a set of positions.
// TODO: Refactor setter and getter names to reflect names of fields.
// TODO:
public class Node {
    private Node parent;
    private Position position;
    private Position target;
    private int gggCost = 0;
    private int hhhCost;
    private int fffCost;

    // EFFECTS: Sets up the node with a gCost, hCost, and fCost by calculating their values using the
    // source and target positions.
    public Node(Position position, Node parent, Position target) {
        // g cost is dependent on the parent node. h cost, on the other hand, is set empirically based on the target
        // position and does not depend on the path taken till now. Once a node is created, it never changes.
        this.position = position;
        this.parent = parent;
        this.target = target;
        generateGCost();
        generateHCost();
        generateFCost();
    }

    // MODIFIES: this
    // EFFECTS: If parent is null
    //              set g cost to 0
    //          else
    //              set g cost to parent's g cost + 1
    public void generateGCost() {
        if (parent == null) {
            this.gggCost = 0;
        } else {
            this.gggCost = parent.getGCost() + 1;
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets up the h cost using the node's current position and the source and target positions.
    public void generateHCost() {
        int horizontalDistance = Math.abs(this.target.getX() - this.position.getX());
        int verticalDistance = Math.abs(this.target.getY() - this.position.getY());
        this.gggCost = horizontalDistance + verticalDistance;
    }

    // MODIFIES: this
    // EFFECTS: Sets up the f cost by adding the g and h costs.
    public void generateFCost() {
        this.fffCost = this.gggCost + this.hhhCost;
    }

    public Node getParent() {
        return parent;
    }

    public Position getPosition() {
        return position;
    }

    public int getGCost() {
        return this.gggCost;
    }

    public int getHCost() {
        return this.hhhCost;
    }

    public int getFCost() {
        return this.fffCost;
    }

    // MODIFIES: this
    // EFFECTS: Sets the parent of this node
    public void setParent(Node parent) {
        this.parent = parent;
    }

    // MODIFIES: this
    // EFFECTS: Sets the position of this node
    public void setPosition(Position position) {
        this.position = position;
    }

    // MODIFIES: this
    // EFFECTS: Sets the g cost of this node
    public void setGCost(int score) {
        this.gggCost = score;
    }

    // MODIFIES: this
    // EFFECTS: Sets the h cost of this node
    public void setHCost(int score) {
        this.hhhCost = score;
    }

    // MODIFIES: this
    // EFFECTS: Sets the f cost of this node
    public void setFCost(int score) {
        this.fffCost = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(position, node.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
