package model.pathfinding;

import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {
    Node testNode;
    Position nodePosition;
    Node parent;
    Position parentPosition;
    Position targetPosition;

    @BeforeEach
    void setup() {
        testNode = new Node();
        parent = new Node();
    }

    @Test
    void testConstructorWithoutParameters() {
        assertNull(testNode.getParent());
        assertEquals(testNode.getGCost(),0);
        assertEquals(testNode.getHCost(),0);
        assertEquals(testNode.getFCost(),0);
        assertNull(testNode.getTarget());
        assertNull(testNode.getPosition());
    }

    @Test
    void testConstructorWithParameters() {
        nodePosition = new Position(0,0);
        targetPosition = new Position(4,3);
        parentPosition = new Position(1,0);
        parent = new Node(parentPosition,null,targetPosition);
        testNode = new Node(nodePosition,parent,targetPosition);
        assertEquals(testNode.getPosition(),nodePosition);
        assertEquals(testNode.getParent(),parent);
        assertEquals(testNode.getTarget(),targetPosition);
        assertEquals(testNode.getGCost(),1);
        assertEquals(testNode.getHCost(),7);
        assertEquals(testNode.getFCost(),8);
    }

    @Test
    void testCalculateGCostParentNull() {
        // check setup
        Position position = new Position(0,0);
        Position target = new Position(4,3);

        testNode.setPosition(position);
        testNode.setParent(null);
        testNode.setTarget(target);

        assertEquals(testNode.getPosition(),position);
        assertNull(testNode.getParent());
        assertEquals(testNode.getTarget(),target);

        // call respective method
        testNode.calculateGCost();

        // check if method does its task appropriately
        assertEquals(testNode.getGCost(),0);

        // check if method only does its task
        assertEquals(testNode.getPosition(),position);
        assertNull(testNode.getParent());
        assertEquals(testNode.getTarget(),target);
        assertEquals(testNode.getHCost(),0);
        assertEquals(testNode.getFCost(),0);
    }

    @Test
    void testCalculateGCostParentNonNull() {
        // check setup
        Position position = new Position(0,0);
        Position target = new Position(4,3);

        Node parent = new Node();
        Position parentPosition = new Position(1,0);

        parent.setPosition(parentPosition);
        parent.setParent(null);
        parent.setTarget(target);
        parent.setGCost(4);

        testNode.setPosition(position);
        testNode.setParent(parent);
        testNode.setTarget(target);

        assertEquals(testNode.getPosition(),position);
        assertEquals(testNode.getParent(),parent);
        assertEquals(testNode.getTarget(),target);

        // call respective method
        testNode.calculateGCost();

        // check if method does its task appropriately
        assertEquals(testNode.getGCost(),4 + Node.MANHATTAN_NODE_DISTANCE);

        // check if method only does its task
        assertEquals(testNode.getPosition(),position);
        assertEquals(testNode.getParent(),parent);
        assertEquals(testNode.getTarget(),target);
        assertEquals(testNode.getHCost(),0);
        assertEquals(testNode.getFCost(),0);
    }

    @Test
    void testCalculateHCost() {
        // check setup
        Position position = new Position(0,0);
        Position target = new Position(4,3);

        Node parent = new Node();
        Position parentPosition = new Position(1,0);

        parent.setPosition(parentPosition);
        parent.setParent(null);
        parent.setTarget(target);
        parent.setGCost(4);

        testNode.setPosition(position);
        testNode.setParent(parent);
        testNode.setTarget(target);

        // call respective method
        testNode.calculateHCost();

        // check if method does its task appropriately
        assertEquals(testNode.getHCost(),7);

        // check if method only does its task
        assertEquals(testNode.getPosition(),position);
        assertEquals(testNode.getParent(),parent);
        assertEquals(testNode.getTarget(),target);
        assertEquals(testNode.getGCost(),0);
        assertEquals(testNode.getFCost(),0);
    }

    @Test
    void testCalculateFCostGCostAndFCostCalculated() {
        // check setup
        Position position = new Position(0,0);
        Position target = new Position(4,3);

        Node parent = new Node();
        Position parentPosition = new Position(1,0);

        parent.setPosition(parentPosition);
        parent.setParent(null);
        parent.setTarget(target);
        parent.setGCost(4);

        testNode.setPosition(position);
        testNode.setParent(parent);
        testNode.setTarget(target);

        // call respective method
        testNode.calculateGCost();
        testNode.calculateHCost();
        testNode.calculateFCost();

        // check if method does its task appropriately
        assertEquals(testNode.getFCost(),12);

        // check if method only does its task
        assertEquals(testNode.getPosition(),position);
        assertEquals(testNode.getParent(),parent);
        assertEquals(testNode.getTarget(),target);
        assertEquals(testNode.getGCost(),5);
        assertEquals(testNode.getHCost(),7);
    }
}
