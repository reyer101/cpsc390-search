public class State {
    public int x, y;

    public State(int _x, int _y) {
        x = _x;
        y = _y;
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
