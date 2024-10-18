public class MooreRules {

    private static final int NUM_NEIGHBORS = 9;
    private boolean[] birthRules;
    private boolean[] survivalRules;

    public MooreRules(int[] birthNeighbors, int[] survivalNeighbors){
        birthRules = new boolean[NUM_NEIGHBORS];
        survivalRules = new boolean[NUM_NEIGHBORS];
        for (int neighbors: birthNeighbors){
            birthRules[neighbors] = true;
        }
        for (int neighbors: survivalNeighbors){
            survivalRules[neighbors] = true;
        }
    }

    public boolean shouldBeBorn(int liveNeighbors) {
        return birthRules[liveNeighbors];
    }

    public boolean shouldSurvive(int liveNeighbors) {
        return survivalRules[liveNeighbors];
    }

    public CellState applyRules(CellState cellState, int liveNeighbors) {
        if (cellState == CellState.DEAD && shouldBeBorn(liveNeighbors)){
            return CellState.WILL_REVIVE;
        } else if (cellState == CellState.ALIVE && !shouldSurvive(liveNeighbors)){
            return CellState.WILL_DIE;
        } else {
            return cellState;
        }
    }
}