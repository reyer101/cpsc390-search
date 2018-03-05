import java.util.ArrayList;
import java.util.HashSet;

public class PathFinder {
    String fileName;
    State startState, goalState;

    public PathFinder(String file) {
        fileName = file;
    }

    public void findPath(MapUtils.Distance distance, boolean usePathCost) {
        ArrayList<State> neighbors = new ArrayList<>();
        ArrayList<State> visited = new ArrayList<>();
        HashSet<State> searchTree = new HashSet<>();
        HashSet<State> frontier = new HashSet<>();

        char[][] pathMap = MapUtils.getMapFromFile(fileName);

        State startState = MapUtils.getStartState(pathMap);
        State goalState = MapUtils.getGoalState(pathMap);

        if(pathMap.length < 1) {
            return;
        }

        int pathCost = 0;

        // initialize search tree and list of visited nodes w/ start state
        searchTree.add(startState);
        visited.add(startState);

        // initialize current state as start state
        State currentState = startState;
        State newState;

        while(!currentState.equals(goalState)) {
            neighbors = MapUtils.getValidNeighbors(currentState, pathMap);
            searchTree.addAll(neighbors);

            // the frontier will contain all unvisited, unexplored leaf nodes
            frontier.addAll(neighbors);
            frontier.removeAll(visited);

            if(usePathCost) {
                currentState = MapUtils.getLowestCostState(frontier, goalState, distance);
            } else {
                currentState = MapUtils.getLowestDistanceState(frontier, goalState, distance);
            }

            pathCost = currentState.pathCost;
            visited.add(currentState);
        }

        currentState = currentState.parent;
        while(!currentState.equals(startState)) {
            pathMap[currentState.x][currentState.y] = 'o';
            currentState = currentState.parent;
        }

        // output resulting path information
        MapUtils.outputMap(pathMap);
        System.out.print("\nPath cost: " + pathCost + "\nSearch tree size: " + searchTree.size() + '\n');
    }
}
