import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class MapUtils {
    public enum Distance {
        MANHATTAN,
        EUCLIDEAN
    }

    public static char[][] getMapFromFile(String file) {
        char[][] mapGrid = new char[0][0];
        try {
            int i = 0, j = 0, n = 0;
            FileReader fr = new FileReader(file);
            String line = null;
            char[] spaces;
            BufferedReader br = new BufferedReader(fr);

            while((line = br.readLine()) != null) {
                if(i == 0) {
                    n = Integer.valueOf(line);
                    mapGrid = new char[n][n];
                    ++i;
                } else {
                    spaces = line.toCharArray();
                    j = 0;
                    for(char space : spaces) {
                        mapGrid[i - 1][j] = space;
                        ++j;
                    }
                    ++i;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        } catch(IOException e) {
            System.out.println("Error reading file");
        }

        return mapGrid;
    }

    public static void outputMap(char[][] map) {
        for(int i = 0; i < map.length; ++i) {
            for(int j = 0; j < map.length; ++j) {
                if(j == map.length - 1) {
                    System.out.print(map[i][j] + "\n");
                    continue;
                }
                System.out.print(map[i][j]);
            }
        }
    }

    public static State getStartState(char[][] map) {
        State retval = null;
        for(int i = 0; i < map.length; ++i) {
            for(int j = 0; j < map.length; ++j) {
                if(map[i][j] == 'i') {
                    retval = new State(i, j);
                }
            }
        }
        return retval;
    }

    public static State getGoalState(char[][] map) {
        State retval = null;
        for(int i = 0; i < map.length; ++i) {
            for(int j = 0; j < map.length; ++j) {
                if(map[i][j] == 'g') {
                    retval = new State(i, j);
                }
            }
        }
        return retval;
    }

    public static ArrayList<State> getValidNeighbors(State state, char[][] map) {
        ArrayList<State> retval = new ArrayList<>();
        int i = state.x;
        int j = state.y;

        if( j - 1 >= 0 && map[i][j-1] != '+') {
            retval.add(new State(i, j-1, state.pathCost + 1, state));
        }

        if( j + 1 <= map.length -1 && map[i][j+1] != '+') {
            retval.add(new State(i, j+1, state.pathCost + 1, state));
        }

        if( i - 1 >= 0 && map[i-1][j] != '+') {
            retval.add(new State(i-1, j, state.pathCost + 1, state));
        }

        if( i + 1 <= map.length -1 && map[i+1][j] != '+') {
            retval.add(new State(i + 1, j, state.pathCost + 1, state));
        }

        return retval;
    }

    public static State getLowestCostState(Collection<State> states,
            State goalState, Distance distance) {
        State minstate = null;
        double cost = 0, minCost = 999;
        for(State state : states) {
            // evaluation function g(n) + h(n)
            if(distance == Distance.MANHATTAN) {
                cost = state.pathCost + getManhattanDistance(state, goalState);
            } else {
                cost = state.pathCost + getEuclideanDistance(state, goalState);
            }

            if(cost < minCost) {
                minstate = state;
                minCost = cost;
            }
        }

        return minstate;
    }

    public static State getLowestDistanceState(Collection<State> states, State goalState, Distance d) {
        State minstate = null;
        double distance, minDistance = 999;
        for(State state : states) {
            if(d == Distance.MANHATTAN) {
                distance = getManhattanDistance(state, goalState);
            } else {
                distance = getEuclideanDistance(state, goalState);
            }
            if(distance < minDistance) {
                minstate = state;
                minDistance = distance;
            }
        }

        return minstate;
    }

    private static double getManhattanDistance(State state1, State state2) {
        return Math.abs(state1.x - state2.x) + Math.abs(state1.x - state2.y);
    }

    private static double getEuclideanDistance(State state1, State state2) {
        return Math.sqrt(Math.pow((state2.x - state1.x), 2)
                + Math.pow((state2.y - state1.y), 2));
    }
}
