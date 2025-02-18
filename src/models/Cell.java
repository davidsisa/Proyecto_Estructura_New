package models;

import java.util.Objects;

public class Cell {

    public int row, col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
        // Override hashCode() y equals() para asegurar el correcto funcionamiento del HashMap
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row && col == cell.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}


