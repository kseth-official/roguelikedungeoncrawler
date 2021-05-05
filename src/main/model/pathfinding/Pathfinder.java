package model.pathfinding;

import exceptions.NodeListEmptyException;
import exceptions.PathNotFoundException;
import model.Position;

import java.util.*;

// A class that contains all pathfinding related functionality.
public class Pathfinder {
    // REQUIRES: startPosition and endPosition must be positions on the current game map
    // EFFECTS: Produces the shortest path from the startPosition to the endPosition while navigating obstacles.
    // Uses Manhattan distance for calculations.
    public List<Position> shortestPathFrom(Position startPosition, Position endPosition, HashSet<Position> obstacles) throws PathNotFoundException {
        /*
            PSEUDO CODE for shortestPathFrom
            Definitions:
            Node: A position on the map with a gCost, hCost, and fCost
        */

        List<Node> openNodes = new ArrayList<>();
        Set<Node> closedNodes = new HashSet<>();

        if (obstacles.contains(endPosition) || obstacles.contains(startPosition)) {
            throw new PathNotFoundException();
        }

        Node sourceNode = new Node(startPosition, null, endPosition);
        Node targetNode = new Node(endPosition,null,endPosition);

        openNodes.add(sourceNode);

        Node currentNode;
        Set<Node> neighbours;

        while (!openNodes.isEmpty()) {
            try {
                currentNode = nodeWithLowestFCost(openNodes);
            } catch (NodeListEmptyException e) {
                throw new PathNotFoundException();
            }

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
                    neighbourNode.calculateFCost();

                    if (!openNodes.contains(neighbourNode)) {
                        openNodes.add(neighbourNode);
                    }
                }
            }
        }

        return null;
    }

    // EFFECTS: Calculates the Manhattan distance between two Nodes.
    private int distanceBetween(Node currentNode, Node neighbourNode) {
        int horizontalDistance = Math.abs(currentNode.getPosition().getX() - neighbourNode.getPosition().getX());
        int verticalDistance = Math.abs(currentNode.getPosition().getY() - neighbourNode.getPosition().getY());
        return horizontalDistance + verticalDistance;
    }

    // EFFECTS: Returns a list of all the positions from the source to the target. Does so by reversing the path
    // generated from backtracking from the target Node to the source Node.
    private List<Position> pathFromSourceToTarget(Node currentNode) {
        List<Position> pathFromTargetToSource = new ArrayList<>();
        while (currentNode != null) {
            pathFromTargetToSource.add(currentNode.getPosition());
            currentNode = currentNode.getParent();
        }
        Collections.reverse(pathFromTargetToSource);
        return pathFromTargetToSource;
    }

    // EFFECTS: Finds all the neighbours of the current node that are valid.
    public Set<Node> findNeighboursOfCurrentNode(Node currentNode, Position targetPosition, Set<Position> obstacles) {
        Set<Node> neighbours = new HashSet<>();
        int currentNodeX = currentNode.getPosition().getX();
        int currentNodeY = currentNode.getPosition().getY();

        Position positionAbove = new Position(currentNodeX,currentNodeY - 1);
        Position positionBelow = new Position(currentNodeX,currentNodeY + 1);
        Position positionToLeft = new Position(currentNodeX - 1,currentNodeY);
        Position positionToRight = new Position(currentNodeX + 1,currentNodeY);

        addValidNeighbours(
                currentNode,
                targetPosition,
                obstacles,
                neighbours,
                currentNodeX,
                currentNodeY,
                positionAbove,
                positionBelow,
                positionToLeft,
                positionToRight);

        return neighbours;
    }

    // EFFECTS: Adds all the valid neighbours to the set of neighbours provided.
    private void addValidNeighbours(
            Node currentNode,
            Position targetPosition,
            Set<Position> obstacles,
            Set<Node> neighbours,
            int currentNodeX,
            int currentNodeY,
            Position positionAbove,
            Position positionBelow,
            Position positionToLeft,
            Position positionToRight) {
        if (!obstacles.contains(positionAbove)) {
            Node nodeAbove = new Node(new Position(currentNodeX, currentNodeY - 1), currentNode, targetPosition);
            neighbours.add(nodeAbove);
        }
        if (!obstacles.contains(positionBelow)) {
            Node nodeBelow = new Node(new Position(currentNodeX, currentNodeY + 1), currentNode, targetPosition);
            neighbours.add(nodeBelow);
        }
        if (!obstacles.contains(positionToLeft)) {
            Node nodeToLeft = new Node(new Position(currentNodeX - 1, currentNodeY), currentNode, targetPosition);
            neighbours.add(nodeToLeft);
        }
        if (!obstacles.contains(positionToRight)) {
            Node nodeToRight = new Node(new Position(currentNodeX + 1, currentNodeY), currentNode, targetPosition);
            neighbours.add(nodeToRight);
        }
    }

    // EFFECTS: Finds the Node in the list with the lowest fCost.
    // Produces null if there are no Nodes in the list.
    public Node nodeWithLowestFCost(List<Node> nodes) throws NodeListEmptyException {
        if (nodes.isEmpty()) {
            throw new NodeListEmptyException();
        }

        Node smallestFCostNode = nodes.get(0);
        for (Node node: nodes) {
            if (node.getFCost() < smallestFCostNode.getFCost()) {
                smallestFCostNode = node;
            }
        }
        return smallestFCostNode;
    }
}
