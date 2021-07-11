package model.pathfinding;

import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// TODO: Pathfinder Test
public class PathfinderTest {
    Pathfinder pathfinder;
    Position startPosition;
    Position endPosition;
    HashSet<Position> obstacles;

    @BeforeEach
    void setup() {
        pathfinder = new Pathfinder();
        startPosition = new Position(0,0);
        endPosition = new Position(3,2);
        obstacles = new HashSet<>();
        obstacles.add(new Position(1,2));
        obstacles.add(new Position(1,4));
        obstacles.add(new Position(2,2));
        obstacles.add(new Position(3,0));
        obstacles.add(new Position(3,1));
        obstacles.add(new Position(3,3));
    }

//    @Test
//    void testShortestPathFromHasSolution() {
//        List<Position> path = pathfinder.shortestPathFrom(startPosition,endPosition,obstacles);
//        for (Position p: path) {
//            System.out.println(p.getX() + "," + p.getY());
//        }
//    }
//
//    @Test
//    void testShortestPathFromHasNoSolution() {
//        // goes into infinite loop if position you're trying to reach is unreachable
//        obstacles.add(new Position(4,2));
//        assertNull(pathfinder.shortestPathFrom(startPosition,endPosition,obstacles));
//    }
//
//    @Test
//    void testNodeInOpenWithLowestFCost() {
//        List<Node> openNodes = new ArrayList<>();
//        Node parentNode = new Node(new Position(0,3),null,endPosition);
//        Node node1 = new Node(new Position(0,2),parentNode,endPosition);
//        Node node2 = new Node(new Position(1,3),parentNode,endPosition);
//
//        openNodes.add(node1);
//        openNodes.add(node2);
//        pathfinder.setOpenNodes(openNodes);
//        assertTrue(node2.equals(pathfinder.nodeWithLowestFCost(openNodes)));
//    }
//
//    @Test
//    void testFindNeighboursOfCurrentNode() {
//        Node currentNode = new Node(new Position(2,1),null,endPosition);
//        Set<Node> expectedSet = new HashSet<>();
//        expectedSet.add(new Node(new Position(1,1),null,endPosition));
//        expectedSet.add(new Node(new Position(2,0),null,endPosition));
////        for (Node node: pathfinder.findNeighboursOfCurrentNode(currentNode,endPosition,obstacles)) {
////            System.out.println(node.getPosition().getX() + "," + node.getPosition().getY());
////        }
//        assertTrue(expectedSet.equals(pathfinder.findNeighboursOfCurrentNode(currentNode,endPosition,obstacles)));
//
//    }
}
