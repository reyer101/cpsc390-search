public class Main {
    public static void main(String[] args) {
        char[][] map = MapReader.getMapFromFile("test.txt");

        int j = 0;
        for(char[] row : map)
        {
            j = 0;
            for(char space : row)
            {
                System.out.print(space);
                ++j;
                if(j == 4) {
                    System.out.print('\n');
                }
            }
        }
    }
}
