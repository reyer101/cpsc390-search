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

    public void findPath(MapUtils.Distance distance) {
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

            if(distance == MapUtils.Distance.EUCLIDEAN) {
                currentState = MapUtils.getLowestEDistanceState(neighbors, goalState);
            } else {
                currentState = MapUtils.getLowestMDistanceState(neighbors, goalState);
            }

            visited.add(currentState);
            ++pathCost;
        }

        // output resulting path information
        MapUtils.outputMap(pathMap);
        System.out.print("\nPath cost: " + pathCost + "\nSearch tree size: " + searchTree.size());
    }
}
