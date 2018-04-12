import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        /** Variables for Graph **/
        int V = 0;
        int E = 0;
        int m = 0;



        /** Variables for Casting **/
        int N = 0;
        int S = 0;
        int k = 0;


        /** Edges or Scenes **/
        int[][] scenes = null;

        /** Check if role is in scene **/
        boolean[] inScene = null;

        /** Actor not in scene **/
        ArrayList<Integer> notInScene = new ArrayList<>();

        /** New scenes **/
        ArrayList<int[]> newScenes = new ArrayList<>();


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int lineCounter = 0;
        while((line = br.readLine()) != null){

            switch (lineCounter){

                // Num Vertices
                case 0:
                {
                    V = Integer.parseInt(line);
                    inScene = new boolean[V];
                    break;
                }
                // Num Edges
                case 1:{
                    E = Integer.parseInt(line);
                    scenes = new int[E][2];
                    break;
                }
                // Num colors
                case 2:{
                    m = Integer.parseInt(line);
                    break;
                }
                // Connected Edges
                default:{

                    int index = lineCounter - 3;
                    String[] vertices = line.split(" ");
                    int v1 = Integer.parseInt(vertices[0]);
                    int v2 = Integer.parseInt(vertices[1]);
                    scenes[index][0] = v1;
                    scenes[index][1] = v2;
                    inScene[v1-1] = true;
                    inScene[v2-1] = true;

                }
            }
            lineCounter++;
        }


        /** Check isolated **/
        int isolated = 0;
        for (int i = 0; i < inScene.length; i++) {
            if (!inScene[i]) {
                isolated++;
                notInScene.add(i+1);

            }
        }


        if(isolated > 1){
            /** Add edges to nodes that are isolated **/
            if(isolated % 2 == 0){
                for (int i = 0; i < isolated; i++) {
                    int[] scene = new int[2];
                    scene[0] = notInScene.get(i);
                    i++;
                    scene[1] = notInScene.get(i);
                    newScenes.add(scene);

                }
            }
            else{
                for (int i = 1; i < isolated; i++) {
                    int[] scene = new int[2];
                    scene[0] = notInScene.get(0);
                    scene[1] = notInScene.get(i);
                    newScenes.add(scene);

                }
                int[] scene = new int[2];
                scene[0] = notInScene.get(0);
                scene[1] = scenes[0][0];
                newScenes.add(scene);

            }
        }else if(isolated == 1) {
            int[] scene = new int[2];
            scene[0] = notInScene.get(0);
            scene[1] = scenes[0][0];
            newScenes.add(scene);

        }
        m = m + isolated;


        /** Check if V is less than number of colors **/
        if(m > V){
            m = V;
        }

        printInstance(V, E, m, scenes, isolated, newScenes);


    }
    public static void printInstance(int V, int E, int m, int[][] scenes, int isolated, ArrayList<int[]> newScenes){
        System.out.println(V);
        System.out.println(E + newScenes.size());
        System.out.println(m);
        StringBuilder sb = new StringBuilder();
        sb.append(m);
        for (int i = 0; i < m; i++) {
            sb.append(" "+(i+1));
        }

        for (int i = 0; i < V; i++) {
            System.out.println(sb.toString());
        }

        if(E == 0){
            for (int i = 0; i < newScenes.size()-1; i++) {
                System.out.println(2 + " " + newScenes.get(i)[0] + " " + newScenes.get(i)[1]);
            }
            System.out.print(2 + " " + newScenes.get(newScenes.size()-1)[0] + " " + newScenes.get(newScenes.size()-1)[1]);

        }else{
            for (int i = 0; i < E-1; i++) {
                System.out.println(2 + " " + scenes[i][0] + " " + scenes[i][1]);
            }
            if (isolated > 0){
                for (int i = 0; i < newScenes.size(); i++) {
                    System.out.println(2 + " " + newScenes.get(i)[0] + " " + newScenes.get(i)[1]);
                }
            }
            System.out.print(2 + " " + scenes[E-1][0] + " " + scenes[E-1][1]);
        }

    }
}
