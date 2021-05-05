package model.pathfinding;

import model.Position;

import java.util.Objects;

/* A Node that represents a position on the map with a parent Node, position, target position, gCost, hCost, and fCost.
 * These Nodes are generated only for the purposes of pathfinding. Normally the map is simply a set of positions.
 * Definitions:
 * Parent Node: The parent of this node (the Node who's neighbour is this node).
 * Position: The x,y coordinate of this node.
 * Target Position: The position of the target Node.
 * gCost (movement cost): The distance between the starting Node and this node calculated using the parent node's gCost
 * + the cost of moving onto this node.
 * hCost (heuristic cost): The distance between the ending Node and this node. Normally calculated using a heuristic
 * function that adds up all the distances. For this implementation, the heuristic uses Taxicab geometry, i.e., the
 * distance between two nodes is the sum of the differences in their x and y coordinates.
 * fCost (final cost): This is the sum of the gCost and hCost that is an indicator for how favourable moving to this
 * Node is.
 */
public class Node {
    public static final int MANHATTAN_NODE_DISTANCE = 1;
    private Position position;
    private Node parent;
    private Position target;
    private int ggCost;
    private int hhCost;
    private int ffCost;

    // EFFECTS: Sets up the node with a parent node, position, and target node position.
    // Calculates the gCost, hCost, and fCost using the parent node and target position.
    public Node(Position position, Node parent, Position target) {
        // g cost is dependent on the parent node. h cost, on the other hand, is set empirically based on the target
        // position and does not depend on the path taken till now.
        // Once a node is created, hCost never changes.
        this.position = position;
        this.parent = parent;
        this.target = target;
        calculateGCost();
        calculateHCost();
        calculateFCost();
    }

    public Position getPosition() {
        return position;
    }

    public Node getParent() {
        return this.parent;
    }

    public Position getTarget() {
        return this.target;
    }

    public int getGCost() {
        return this.ggCost;
    }

    public int getHCost() {
        return this.hhCost;
    }

    public int getFCost() {
        return this.ffCost;
    }

    // MODIFIES: this
    // EFFECTS: Sets the parent of this Node.
    public void setParent(Node parent) {
        this.parent = parent;
    }

    // MODIFIES: this
    // EFFECTS: Sets the position of this Node.
    public void setPosition(Position position) {
        this.position = position;
    }

    // MODIFIES: this
    // EFFECTS: Sets the gCost of this Node.
    public void setGCost(int score) {
        this.ggCost = score;
    }

    // MODIFIES: this
    // EFFECTS: Sets the hCost of this Node.
    public void setHCost(int score) {
        this.hhCost = score;
    }

    // MODIFIES: this
    // EFFECTS: Sets the fCost of this Node.
    public void setFCost(int score) {
        this.ffCost = score;
    }

    // MODIFIES: this
    // EFFECTS: If parent is null (this is the source Node)
    //              set gCost to 0
    //          Else
    //              set gCost to parent's gCost + MANHATTAN_NODE_DISTANCE
    public void calculateGCost() {
        if (parent == null) {
            this.ggCost = 0;
        } else {
            this.ggCost = parent.getGCost() + MANHATTAN_NODE_DISTANCE;
        }
    }

    // MODIFIES: this
    // EFFECTS: Calculates the Node's hCost using the node's current position and the target position.
    public void calculateHCost() {
        int horizontalDistance = Math.abs(this.target.getX() - this.position.getX());
        int verticalDistance = Math.abs(this.target.getY() - this.position.getY());
        this.ggCost = horizontalDistance + verticalDistance;
    }

    // MODIFIES: this
    // EFFECTS: Calculates the fCost by adding the gCost and hCost.
    public void calculateFCost() {
        this.ffCost = this.ggCost + this.hhCost;
    }

    // EFFECTS: Designates that two Node's are equal if their position's are equal.
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
