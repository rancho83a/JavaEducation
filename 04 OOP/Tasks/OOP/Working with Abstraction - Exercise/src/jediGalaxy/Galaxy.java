package jediGalaxy;

public class Galaxy {
    private Field field;

    public Galaxy(Field field) {
        this.field = field;
    }

    public void moveSith(int row, int col) {
        while (row >= 0 && col >= 0) {
            if (field.isInsideFiled(row, col)) {
                field.set(row, col, 0);
            }
            row--;
            col--;
        }
    }

    public long moveJedi(int row, int col) {
        long starPower = 0;
        while (row >= 0 && col < this.field.getColLength(1)) {
            if (field.isInsideFiled(row, col)) {
                starPower += field.getValue(row, col);
            }
            col++;
            row--;
        }
        return starPower;
    }
}
