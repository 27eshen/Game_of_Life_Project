import processing.core.PApplet;
import java.lang.Math;

public class Main extends PApplet {
    private int NUM_ROWS;
    private int NUM_COLUMNS;
    private int CELL_SIZE;
    private boolean doEvolve;
    public static PApplet app;
    private Cell[][] cells;


    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public Main(){
        app = this;
        NUM_ROWS = 50;
        NUM_COLUMNS = 100;
        CELL_SIZE = 10;
        cells = new Cell[NUM_ROWS][NUM_COLUMNS];
        doEvolve = false;
    }

    public void settings(){
        size(NUM_COLUMNS * CELL_SIZE, NUM_ROWS * CELL_SIZE);
    }

    public void setup(){
        MooreRules rules = new MooreRules(new int[]{3}, new int[]{2, 3});
        for (int r = 0; r < NUM_ROWS; r++){
            for(int c = 0; c < NUM_COLUMNS; c++){
                double rand = Math.random();
                if (rand > 0.4 && r > 0 && c > 0 && r < NUM_ROWS-1 && c < NUM_COLUMNS-1){
                    cells[r][c] = new Cell(c*CELL_SIZE, r*CELL_SIZE, CELL_SIZE, r, c, CellState.ALIVE, rules);
                } else {


                    cells[r][c] = new Cell(c*CELL_SIZE, r*CELL_SIZE, CELL_SIZE, r, c, CellState.DEAD, rules);
                }
            }
        }
    }

    public void draw(){
        for(int r = 0; r < NUM_ROWS; r++){
            for(int c = 0; c < NUM_COLUMNS; c++){
                cells[r][c].display();
            }
        }
        if (doEvolve){
            delay(500);
            applyRules();
            evolve();
        }
    }

    public void mouseClicked(){
        cells[mouseY/CELL_SIZE][mouseX/CELL_SIZE].handleClick();
    }

    public void keyPressed(){
        doEvolve = !doEvolve;
    }

    public void applyRules(){
        for(int r = 1; r < NUM_ROWS-1; r++){
            for(int c = 1; c < NUM_COLUMNS-1; c++){
                cells[r][c].applyRules(cells);
            }
        }
    }

    public void evolve(){
        for(int r = 0; r < NUM_ROWS; r++){
            for(int c = 0; c < NUM_COLUMNS; c++){
                cells[r][c].evolve();
            }
        }
    }

}