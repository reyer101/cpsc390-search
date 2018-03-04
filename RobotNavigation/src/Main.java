
public class Main {
    public static void main(String[] args) {
        char[][] map = MapUtils.getMapFromFile("test1.txt");

        PathFinder pathFinder = new PathFinder(map);
        pathFinder.findPath(MapUtils.Distance.MANHATTAN, false);
    }
}
