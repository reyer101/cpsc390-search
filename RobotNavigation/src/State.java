public class State {
    public int x, y, pathCost;
    public State parent;

    public State(int _x, int _y) {
        x = _x;
        y = _y;
        pathCost = 0;
        parent = null;
    }

    public State(int _x, int _y, int cost, State parentState) {
        x = _x;
        y = _y;
        pathCost = cost;
        parent = parentState;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof State)) {
            return false;
        }

        State state = (State) object;
        return x == state.x && y == state.y;
    }

    @Override
    public int hashCode() {
        return x + ((x + 1) / (y + 1));
    }
}
