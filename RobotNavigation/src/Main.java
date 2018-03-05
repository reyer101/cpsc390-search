import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Please include your input file name");
            //return;
        }

        PathFinder pathFinder = new PathFinder("test1.txt");
        BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
        int choice = 0;

        while(choice != 5) {
            printMenu();

            try {
                choice = Integer.parseInt(input.readLine());
            } catch(Exception e){
                System.out.println("Enter a number choice (1-5)");
                continue;
            }

            if(choice < 1 || choice > 5) {
                System.out.println("Enter a number choice (1-5)");
                continue;
            }

            switch (choice) {
                case 1:
                    pathFinder.findPath(MapUtils.Distance.EUCLIDEAN, false);
                    break;
                case 2:
                    pathFinder.findPath(MapUtils.Distance.MANHATTAN, false);
                    break;
                case 3:
                    pathFinder.findPath(MapUtils.Distance.EUCLIDEAN, true);
                    break;
                case 4:
                    pathFinder.findPath(MapUtils.Distance.MANHATTAN, true);
                    break;
                default:
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.print("\n 1. Euclidean Distance \n 2. Manhattan Distance \n "
        + "3. Euclidean Distance w/ Path Cost \n 4. Manhattan Distance w/ Path Cost "
        + "\n 5. Exit \n Enter your choice (1-5): ");
    }
}
