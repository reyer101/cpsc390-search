import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapReader {

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
}
