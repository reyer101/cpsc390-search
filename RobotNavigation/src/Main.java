
public class Main {
    public static void main(String[] args) {
        char[][] map = MapUtils.getMapFromFile("test.txt");

        PathFinder pathFinder = new PathFinder(map);
        //pathFinder.findEPath();
        pathFinder.findMPath();
    }
}
