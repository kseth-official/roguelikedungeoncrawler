package model.pathfinding;

import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

// TODO: Pathfinder Test
public class PathfinderTest {
    Pathfinder pathfinder;
    Position startPosition;
    Position endPosition;
    Node startNode;
    Node endNode;
    HashSet<Position> obstacles;

    Node nodeToFindNeighbours;
    Node nodeAbove;
    Node nodeBelow;
    Node nodeRight;
    Node nodeLeft;

    @BeforeEach
    void setup() {
        pathfinder = new Pathfinder();
        startPosition = new Position(1,0);
        endPosition = new Position(3,4);
        startNode = new Node();
        startNode.setPosition(startPosition);
        endNode = new Node();
        endNode.setPosition(endPosition);

        obstacles = new HashSet<>();
        obstacles.add(new Position(0,0));
        obstacles.add(new Position(0,2));
        obstacles.add(new Position(2,2));
        obstacles.add(new Position(2,0));

        nodeToFindNeighbours = new Node(new Position(1,1),null,endPosition);

        nodeAbove = new Node(new Position(1,2),null,endPosition);
        nodeBelow = new Node(new Position(1,0),null,endPosition);
        nodeRight = new Node(new Position(2,1),null,endPosition);
        nodeLeft = new Node(new Position(0,1),null,endPosition);

        assertNotNull(pathfinder);
    }

    @Test
    void testDistanceBetween() {
        // call method and check for appropriate output
        assertEquals(pathfinder.distanceBetween(startNode,endNode),6);
    }

    @Test
    void testPathFromSourceToTarget() {
        // setup
        Node targetNode = new Node();
        targetNode.setPosition(new Position(0,0));
        Node secondNode = new Node();
        secondNode.setPosition(new Position(0,1));
        Node thirdNode = new Node();
        thirdNode.setPosition(new Position(1,1));
        Node sourceNode = new Node();
        sourceNode.setPosition(new Position(2,1));

        targetNode.setParent(null);
        secondNode.setParent(targetNode);
        thirdNode.setParent(secondNode);
        sourceNode.setParent(thirdNode);

        List<Position> actualPath = new ArrayList<>();
        actualPath.add(new Position(2,1));
        actualPath.add(new Position(1,1));
        actualPath.add(new Position(0,1));
        actualPath.add(new Position(0,0));
        Collections.reverse(actualPath);

        // call method
        List<Position> generatedPath = pathfinder.pathFromSourceToTarget(sourceNode);
        // check for appropriate output
        assertTrue(actualPath.equals(generatedPath));
    }
// TODO: 16 Tests for neighbours: 4C0 4C1 4C2 4C3 4C4

    // 4C0

    @Test
    void testFindNeighboursOfCurrentNodeNoNodesInvalid() {
        // setup

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),4);
        assertTrue(neighbours.contains(nodeAbove));
        assertTrue(neighbours.contains(nodeBelow));
        assertTrue(neighbours.contains(nodeRight));
        assertTrue(neighbours.contains(nodeLeft));
    }

    // 4C1

    @Test
    void testFindNeighboursOfCurrentNodeNodeAboveInvalid() {
        // setup
        obstacles.add(new Position(1,2));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),3);
        assertFalse(neighbours.contains(nodeAbove));
        assertTrue(neighbours.contains(nodeBelow));
        assertTrue(neighbours.contains(nodeRight));
        assertTrue(neighbours.contains(nodeLeft));
    }

    @Test
    void testFindNeighboursOfCurrentNodeNodeBelowInvalid() {
        // setup
        obstacles.add(new Position(1,0));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),3);
        assertTrue(neighbours.contains(nodeAbove));
        assertFalse(neighbours.contains(nodeBelow));
        assertTrue(neighbours.contains(nodeRight));
        assertTrue(neighbours.contains(nodeLeft));
    }

    @Test
    void testFindNeighboursOfCurrentNodeNodeRightInvalid() {
        // setup
        obstacles.add(new Position(2,1));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),3);
        assertTrue(neighbours.contains(nodeAbove));
        assertTrue(neighbours.contains(nodeBelow));
        assertFalse(neighbours.contains(nodeRight));
        assertTrue(neighbours.contains(nodeLeft));
    }

    @Test
    void testFindNeighboursOfCurrentNodeNodeLeftInvalid() {
        // setup
        obstacles.add(new Position(0,1));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),3);
        assertTrue(neighbours.contains(nodeAbove));
        assertTrue(neighbours.contains(nodeBelow));
        assertTrue(neighbours.contains(nodeRight));
        assertFalse(neighbours.contains(nodeLeft));
    }

    // 4C2

    @Test
    void testFindNeighboursOfCurrentNodeUpDownInvalid() {
        // setup
        obstacles.add(new Position(1,2));
        obstacles.add(new Position(1,0));


        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),2);
        assertFalse(neighbours.contains(nodeAbove));
        assertFalse(neighbours.contains(nodeBelow));
        assertTrue(neighbours.contains(nodeRight));
        assertTrue(neighbours.contains(nodeLeft));
    }

    @Test
    void testFindNeighboursOfCurrentNodeUpRightInvalid() {
        // setup
        obstacles.add(new Position(1,2));
        obstacles.add(new Position(2,1));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),2);
        assertFalse(neighbours.contains(nodeAbove));
        assertTrue(neighbours.contains(nodeBelow));
        assertFalse(neighbours.contains(nodeRight));
        assertTrue(neighbours.contains(nodeLeft));
    }

    @Test
    void testFindNeighboursOfCurrentNodeUpLeftInvalid() {
        // setup
        obstacles.add(new Position(1,2));
        obstacles.add(new Position(0,1));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),2);
        assertFalse(neighbours.contains(nodeAbove));
        assertTrue(neighbours.contains(nodeBelow));
        assertTrue(neighbours.contains(nodeRight));
        assertFalse(neighbours.contains(nodeLeft));
    }

    @Test
    void testFindNeighboursOfCurrentNodeDownRightInvalid() {
        // setup
        obstacles.add(new Position(1,0));
        obstacles.add(new Position(2,1));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),2);
        assertTrue(neighbours.contains(nodeAbove));
        assertFalse(neighbours.contains(nodeBelow));
        assertFalse(neighbours.contains(nodeRight));
        assertTrue(neighbours.contains(nodeLeft));
    }

    @Test
    void testFindNeighboursOfCurrentNodeDownLeftInvalid() {
        // setup
        obstacles.add(new Position(1,0));
        obstacles.add(new Position(0,1));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),2);
        assertTrue(neighbours.contains(nodeAbove));
        assertFalse(neighbours.contains(nodeBelow));
        assertTrue(neighbours.contains(nodeRight));
        assertFalse(neighbours.contains(nodeLeft));
    }

    @Test
    void testFindNeighboursOfCurrentNodeRightLeftInvalid() {
        // setup
        obstacles.add(new Position(2,1));
        obstacles.add(new Position(0,1));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),2);
        assertTrue(neighbours.contains(nodeAbove));
        assertTrue(neighbours.contains(nodeBelow));
        assertFalse(neighbours.contains(nodeRight));
        assertFalse(neighbours.contains(nodeLeft));
    }

    // 4C3

    @Test
    void testFindNeighboursOfCurrentNodeUpDownRightInvalid() {
        // setup
        obstacles.add(new Position(1,2));
        obstacles.add(new Position(1,0));
        obstacles.add(new Position(2,1));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),1);
        assertFalse(neighbours.contains(nodeAbove));
        assertFalse(neighbours.contains(nodeBelow));
        assertFalse(neighbours.contains(nodeRight));
        assertTrue(neighbours.contains(nodeLeft));
    }

    @Test
    void testFindNeighboursOfCurrentNodeDownRightLeftInvalid() {
        // setup
        obstacles.add(new Position(1,0));
        obstacles.add(new Position(2,1));
        obstacles.add(new Position(0,1));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),1);
        assertTrue(neighbours.contains(nodeAbove));
        assertFalse(neighbours.contains(nodeBelow));
        assertFalse(neighbours.contains(nodeRight));
        assertFalse(neighbours.contains(nodeLeft));
    }

    @Test
    void testFindNeighboursOfCurrentNodeRightLeftUpInvalid() {
        // setup
        obstacles.add(new Position(1,2));
        obstacles.add(new Position(2,1));
        obstacles.add(new Position(0,1));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),1);
        assertFalse(neighbours.contains(nodeAbove));
        assertTrue(neighbours.contains(nodeBelow));
        assertFalse(neighbours.contains(nodeRight));
        assertFalse(neighbours.contains(nodeLeft));
    }

    @Test
    void testFindNeighboursOfCurrentNodeLeftUpDownInvalid() {
        // setup
        obstacles.add(new Position(1,2));
        obstacles.add(new Position(1,0));
        obstacles.add(new Position(0,1));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),1);
        assertFalse(neighbours.contains(nodeAbove));
        assertFalse(neighbours.contains(nodeBelow));
        assertTrue(neighbours.contains(nodeRight));
        assertFalse(neighbours.contains(nodeLeft));
    }

    // 4C4

    @Test
    void testFindNeighboursOfCurrentNodeAllNodesInvalid() {
        // setup
        obstacles.add(new Position(1,2));
        obstacles.add(new Position(1,0));
        obstacles.add(new Position(2,1));
        obstacles.add(new Position(0,1));

        // call appropriate method
        Set<Node> neighbours = pathfinder.findNeighboursOfCurrentNode(nodeToFindNeighbours,endPosition,obstacles);
        // check for expected output
        assertEquals(neighbours.size(),0);
        assertFalse(neighbours.contains(nodeAbove));
        assertFalse(neighbours.contains(nodeBelow));
        assertFalse(neighbours.contains(nodeRight));
        assertFalse(neighbours.contains(nodeLeft));
    }

    @Test
    void testFindNodeWithSmallestFCostNodeListEmpty() {
        // setup
        // check setup
        // call appropriate method
        // check for expected outcome
    }

    @Test
    void testFindNodeWithSmallestFCostNodeListNotEmpty() {
        // setup
        // check setup
        // call appropriate method
        // check for expected outcome
    }

    @Test
    void testFindNodeWithSmallestFCostNodeListContainsMultipleNodesWithSameFCost() {
        // setup
        // check setup
        // call appropriate method
        // check for expected outcome
    }
}
