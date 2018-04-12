import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        /** Variables for Graph **/
        int V = 0;
        int E = 0;
        int m = 0;

        /** Edges or Scenes **/
        ArrayList<int[]> scenes = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int lineCounter = 0;
        while((line = br.readLine()) != null){

            switch (lineCounter){

                // Num Vertices
                case 0:
                {
                    V = Integer.parseInt(line) + 2;
                    break;
                }
                // Num Edges
                case 1:{
                    E = Integer.parseInt(line);
                    break;
                }
                // Num colors
                case 2:{
                    m = Integer.parseInt(line) + 2;
                    break;
                }
                // Connected Edges
                default:{

                    int index = lineCounter - 3;
                    String[] vertices = line.split(" ");
                    int v1 = Integer.parseInt(vertices[0]);
                    int v2 = Integer.parseInt(vertices[1]);
                    int[] scene = {v1, v2};
                    scenes.add(scene);
                }
            }
            lineCounter++;
        }
        
        int[] scene = new int[2];
        scene[0] = V;
        scene[1] = V-1;
        scenes.add(scene);

        for(int i = 1; i < V-1; i++){
            scene = new int[2];
            scene[0] = V-1;
            scene[1] = i;
            scenes.add(scene);
        }

        E = scenes.size();

        printInstance(V, E, m, scenes);
    }

    public static void printInstance(int V, int E, int m, ArrayList<int[]> scenes){
        System.out.println(V);
        System.out.println(E);
        System.out.println(m);
        StringBuilder sb = new StringBuilder();
        sb.append(m);
        for (int i = 0; i < m; i++) {
            sb.append(" "+(i+1));
        }

        for (int i = 0; i < V; i++) {
            System.out.println(sb.toString());
        }

        for (int i = 0; i < E-1; i++) {
            System.out.println(2 + " " + scenes.get(i)[0] + " " + scenes.get(i)[1]);
        }
        System.out.print(2 + " " + scenes.get(E-1)[0] + " " + scenes.get(E-1)[1]);
    }
}
