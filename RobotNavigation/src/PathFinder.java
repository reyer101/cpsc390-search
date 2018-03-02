import java.util.ArrayList;
import java.util.HashSet;

public class PathFinder {
    char[][] pathMap;
    State startState, goalState;

    public PathFinder(char[][] map) {
        pathMap = map;
        startState = MapUtils.getStartState(map);
        goalState = MapUtils.getGoalState(map);
    }

    public void findEPath() {
        ArrayList<State> neighbors = new ArrayList<>();
        ArrayList<State> visited = new ArrayList<>();
        HashSet<State> searchTree = new HashSet<>();

        int pathCost = 0;

        // initialize the search tree w/ the start state
        // add the start state to the "visited" list
        searchTree.add(startState);
        visited.add(startState);

        // initialize the current state w/ the start state
        State currentState = startState;

        // while the goal state hasn't been reached
        while(!currentState.equals(goalState)) {
            // mark visited state if it isn't the initial state
            if(currentState != startState) {
                pathMap[currentState.x][currentState.y] = 'o';
            }

            // find all traversable neighbors of the current state
            // add them to the search tree (HashSet will not add duplicates)
            neighbors = MapUtils.getValidNeighbors(currentState, pathMap);
            searchTree.addAll(neighbors);

            // remove previously visited neighbors
            neighbors.removeAll(visited);

            // find neighbor with lowest Euclidean distance from goal
            currentState = MapUtils.getLowestEDistanceState(neighbors, goalState);
            visited.add(currentState);
            ++pathCost;
        }

        // output resulting path information
        MapUtils.outputMap(pathMap);
        System.out.print("\nPath cost: " + pathCost + "\nSearch tree size: " + searchTree.size());
    }

    public void findMPath() {
        ArrayList<State> neighbors = new ArrayList<>();
        ArrayList<State> visited = new ArrayList<>();
        HashSet<State> searchTree = new HashSet<>();

        int pathCost = 0;

        searchTree.add(startState);
        visited.add(startState);

        State currentState = startState;

        while(!currentState.equals(goalState)) {
            if(currentState != startState) {
                pathMap[currentState.x][currentState.y] = 'o';
            }

            neighbors = MapUtils.getValidNeighbors(currentState, pathMap);
            searchTree.addAll(neighbors);

            neighbors.removeAll(visited);

            currentState = MapUtils.getLowestMDistanceState(neighbors, goalState);
            visited.add(currentState);
            ++pathCost;
        }

        // output resulting path information
        MapUtils.outputMap(pathMap);
        System.out.print("\nPath cost: " + pathCost + "\nSearch tree size: " + searchTree.size());
    }
}
