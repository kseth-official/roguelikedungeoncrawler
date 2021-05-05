package exceptions;

import java.io.IOException;

// An exception throw when the a method tries to retrieve a Node from an empty list of Nodes during A* pathfinding.
public class NodeListEmptyException extends IOException {
}
