import static java.util.Arrays.fill;

public class Cell{
    private int x;
    private int y;
    private int size;
    private int row;
    private int column;
    private CellState cellState;
    private MooreRules rules;

    /*
    class constructor
    @param  x and y position of the cell
    @param  size of the cell rectangle
    @param  row and column of the cell in the array/display
    @param  state of the cell
    @param  rules used
    */

    public Cell(int x, int y, int size, int row, int column, CellState cellState, MooreRules rules) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
    }

    /*
    applies the rules onto each individual cell & counts the live neighbors
    @param  2D array of Cells named cells
    */

    public void applyRules(Cell[][] cells) {
        int liveNeighbors = countLiveNeighbors(cells);
        if (liveNeighbors != 0){
            System.out.println(liveNeighbors);
        }
        cellState = rules.applyRules(cellState, liveNeighbors);
    }

    /*
    checks what the cellState for the cell is and makes WILL_REVIVE cells ALIVE and WILL_DIE cells DEAD
    */
    public void evolve() { //revive lives
        if (cellState == CellState.WILL_REVIVE){
            cellState = CellState.ALIVE;
        } else if (cellState == CellState.WILL_DIE){
            cellState = CellState.DEAD;
        }
    }

    /*
    checks for ALIVE/DEAD cells and fills them to black/white accordingly
    draws the rectangle
    */
    public void display() {
        if(cellState == CellState.ALIVE){
            Main.app.fill(0); //alive
        } else {
            Main.app.fill(255); //dead
        }

        Main.app.rect(x, y, size, size);
    }

    /*
    changes the cell from dead to alive or vice versa when clicked
    */
    public void handleClick() {
        if(cellState == CellState.DEAD){
            cellState = CellState.ALIVE;
        } else if(cellState == CellState.ALIVE){
            cellState = CellState.DEAD;
        }
        System.out.println("mouse clicked");
    }

    /*
    counts the live cells around the current cell then subtracts 1 if the current cell is ALIVE as well
    @param  2D array of Cells named cells
    */
    private int countLiveNeighbors(Cell[][] cells) {
        int count = 0;

        for (int r = row-1; r < row+2; r++){
            for (int c = column-1; c < column+2; c++){
                if (cells[r][c].cellState == CellState.ALIVE || cells[r][c].cellState == CellState.WILL_DIE){
                    count++;
                }
            }
        }

        if(cells[row][column].cellState == CellState.ALIVE){
            count--;
        }

        return count;
    }
}