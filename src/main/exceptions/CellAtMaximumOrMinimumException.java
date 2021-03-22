package exceptions;

import java.io.IOException;

// An exception for when addition to a cell in the inventory will cause the cell to have more items than the cell's
// capacity or subtraction will cause the cell to have less than 0 items.
public class CellAtMaximumOrMinimumException extends IOException {
}
