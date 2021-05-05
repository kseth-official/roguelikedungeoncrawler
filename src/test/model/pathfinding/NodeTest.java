package model.pathfinding;

import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// TODO
public class NodeTest {
    Node node;
    Position nodePosition;
    Node parent;
    Position targetPosition;

    @BeforeEach
    void setup() {
        nodePosition = new Position(1,0);
        parent = new Node(new Position(0,0),null,targetPosition);
        targetPosition = new Position(11,4);
        node = new Node(nodePosition,parent,targetPosition);
    }

    @Test
    void testConstructor() {
        // check setup
        assertEquals(node.getPosition(),nodePosition);
        assertEquals(node.getParent(),parent);
        assertEquals(node.getTarget(),targetPosition);
        // call respective method
        // check if method does its task appropriately
        // check if method only does its task
    }

    @Test
    void testCalculateGCost() {
        // check setup
        // call respective method
        // check if method does its task appropriately
        // check if method only does its task
    }

    @Test
    void testCalculateHCost() {
        // check setup
        // call respective method
        // check if method does its task appropriately
        // check if method only does its task
    }

    @Test
    void testCalculateFCost() {
        // check setup
        // call respective method
        // check if method does its task appropriately
        // check if method only does its task
    }
}
