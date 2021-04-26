package model.pathfinding;

import model.Position;

import java.util.*;

// A class that contains all pathfinding related functionality.
public class Pathfinder {
    // The list of nodes to be evaluated
    private List<Node> openNodes = new ArrayList<>();
    // The set of nodes that have been evaluated
    private Set<Node> closedNodes = new HashSet<>();

    // EFFECTS: Checks whether a target node is within a source node's radar.
    public static boolean detectedByRadar(int radarSize, Position source, Position target) {
        int radarRightBoundary = source.getX() + radarSize;
        int radarLeftBoundary  = source.getX() - radarSize;
        int radarLowerBoundary = source.getY() + radarSize;
        int radarUpperBoundary = source.getY() - radarSize;
        int targetX = target.getX();
        int targetY = target.getY();
        if (targetX >= radarLeftBoundary && targetX <= radarRightBoundary && targetY <= radarLowerBoundary && targetY >= radarUpperBoundary) {
            return true;
        }
        return false;
    }

    // REQUIRES: startPosition and endPosition must be positions on the current game map
    // EFFECTS: Produces the shortest path from the startPosition to the endPosition while navigating obstacles.
    // Uses Manhattan distance for calculations.
    public List<Position> shortestPathFrom(Position startPosition, Position endPosition, HashSet<Position> obstacles) {
        /*
            PSEUDO CODE for shortestPathFrom
            Definitions:
            Node: A position on the map with a gCost, hCost, and fCost
        */
        if (obstacles.contains(endPosition) || obstacles.contains(startPosition)) {
            return null;
        }
        Node sourceNode = new Node(startPosition, null, endPosition);
        Node targetNode = new Node(endPosition,null,endPosition);
        openNodes.add(sourceNode);
        Node currentNode;
        Set<Node> neighbours;

        while (!openNodes.isEmpty()) {
            currentNode = nodeInOpenWithLowestFCost();

            openNodes.remove(currentNode);
            closedNodes.add(currentNode);

            if (currentNode.equals(targetNode)) {
                return pathFromSourceToTarget(currentNode);
            }

            neighbours = findNeighboursOfCurrentNode(currentNode,endPosition,obstacles);

            for (Node neighbourNode: neighbours) {
                if (closedNodes.contains(neighbourNode)) {
                    continue;
                }

                int newMovementCostToNeighbour = currentNode.getGCost() + distanceBetween(currentNode,neighbourNode);
                if (newMovementCostToNeighbour < neighbourNode.getGCost() || !openNodes.contains(neighbourNode)) {
                    neighbourNode.setGCost(newMovementCostToNeighbour);
                    neighbourNode.setParent(currentNode);
                    neighbourNode.generateFCost();

                    if (!openNodes.contains(neighbourNode)) {
                        openNodes.add(neighbourNode);
                    }
                }
            }
        }

        return null;
    }

    private int distanceBetween(Node currentNode, Node neighbourNode) {
        int horizontalDistance = Math.abs(currentNode.getPosition().getX() - neighbourNode.getPosition().getX());
        int verticalDistance = Math.abs(currentNode.getPosition().getY() - neighbourNode.getPosition().getY());
        return horizontalDistance + verticalDistance;
    }

    // EFFECTS: Returns a list of all the positions from the source to the target.
    // Does so by reversing the path generated from backtracking from the currentNode = targetNode to the
    // source node.
    private List<Position> pathFromSourceToTarget(Node currentNode) {
        List<Position> pathFromTargetToSource = new ArrayList<>();
        while (currentNode != null) {
            pathFromTargetToSource.add(currentNode.getPosition());
            currentNode = currentNode.getParent();
        }
        Collections.reverse(pathFromTargetToSource);
        return pathFromTargetToSource;
    }

    // EFFECTS: Finds all the neighbours of the current node that are valid
    public Set<Node> findNeighboursOfCurrentNode(Node currentNode, Position targetPosition, Set<Position> obstacles) {
        int currentNodeX = currentNode.getPosition().getX();
        int currentNodeY = currentNode.getPosition().getY();
        Node nodeAbove = new Node(new Position(currentNodeX,currentNodeY - 1),currentNode,targetPosition);
        Node nodeBelow = new Node(new Position(currentNodeX,currentNodeY + 1),currentNode,targetPosition);
        Node nodeToLeft = new Node(new Position(currentNodeX - 1,currentNodeY),currentNode,targetPosition);
        Node nodeToRight = new Node(new Position(currentNodeX + 1,currentNodeY),currentNode,targetPosition);

        Set<Node> neighbours = new HashSet<>();
        neighbours.add(nodeAbove);
        neighbours.add(nodeBelow);
        neighbours.add(nodeToLeft);
        neighbours.add(nodeToRight);

        Set<Node> temp = new HashSet<>(neighbours);
        for (Node node: temp) {
            if (obstacles.contains(node.getPosition())) {
                neighbours.remove(node);
            }
        }

        return neighbours;
    }

    // EFFECTS: Finds the node in the open list with the lowest f cost
    public Node nodeInOpenWithLowestFCost() {
        Node smallestFCostNode = openNodes.get(0);
        for (Node node: openNodes) {
            if (node.getFCost() < smallestFCostNode.getFCost()) {
                smallestFCostNode = node;
            }
        }
        return smallestFCostNode;
    }

    public List<Node> getOpenNodes() {
        return this.openNodes;
    }

    // MODIFIES: this
    // EFFECTS: Sets the open nodes
    public void setOpenNodes(List<Node> openNodes) {
        this.openNodes = openNodes;
    }


}
