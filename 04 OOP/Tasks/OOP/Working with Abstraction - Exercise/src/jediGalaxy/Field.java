package jediGalaxy;

public class Field {
    private int[][] matrix;


    public Field(int rows, int cols){
        this(rows,cols,0);
    }

    public Field(int rows, int cols, int startNum){
        this.matrix=new int[rows][cols];
        this.FillMatrix(0);

    }

    private void FillMatrix(int startNum){
        for (int row = 0; row < this.matrix.length; row++) {
            for (int col = 0; col < this.matrix[row].length; col++) {
                this.matrix[row][col]=startNum++;
            }
        }
    }



    public boolean isInsideFiled(int row, int col) {
        return row >= 0 && row < this.matrix.length && col >= 0 && col < this.matrix[row].length;
    }

    public void set(int row, int col, int newValue) {
        this.matrix[row][col]=newValue;
    }

    public int getColLength(int row) {

        return this.matrix[row].length;
    }

    public int getValue(int row, int col) {
        return this.matrix[row][col];
    }
}
