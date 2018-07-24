// Java program for Kruskal's algorithm to find Minimum
// Spanning Tree of a given connected, undirected and 
// weighted graph
import java.util.*;
import java.lang.*;
import java.io.*;

class Graph
{
    public  int [][] adjacentMatrix;

    // A class to represent a graph edge
    class Edge implements Comparable<Edge>
    {
        int src, dest, weight;

        public int compareTo(Edge compareEdge)
        {
            return this.weight-compareEdge.weight;
        }
    };

    // A class to represent a subset for union-find
    class subset
    {
        int parent, rank;
    };

    int V, E;    // V-> no. of vertices & E->no.of edges
    Edge edge[]; // collection of all edges

    // Creates a graph with V vertices and E edges
    Graph(int v, int e)
    {

        V = v;
        E = e;
        edge = new Edge[E];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }

    int find(subset subsets[], int i)
    {
        // find root and make root as parent of i (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    public void addEdge(int src, int dest, int weight){
       // edge[0].src = src;
       // edge[0].dest = dest;
       // edge[0].weight = weight;
        if(adjacentMatrix == null){
            int s = 10;
            adjacentMatrix = new int[s][s];
        }
        adjacentMatrix[src][dest] = weight;

    }

    public static void convertMatrix(int matrix[][]) {
        int row = matrix.length + 2;
        int column = matrix[0].length + 1;
        String newMatrix[][] = new String[row][column];
        initializeFirstRow(newMatrix);
        initializeFirstColumn(newMatrix);
        copyMatrix(matrix, newMatrix);
        printAdjacentMatrix(newMatrix);
    }

    private static void initializeFirstColumn(String[][] newMatrix) {
        newMatrix[1][0] = "0";
        newMatrix[2][0] = "1";
        newMatrix[3][0] = "2";
        newMatrix[4][0] = "3";
        newMatrix[5][0] = "4";
        newMatrix[6][0] = "5";
        newMatrix[7][0] = "6";
        newMatrix[8][0] = "7";
        newMatrix[9][0] = "8";
        newMatrix[10][0] = "9" ;
    }

    private static void printAdjacentMatrix(String[][] newMatrix) {
        System.out.println("----------------------------------------");
        for (int row = 0; row < newMatrix.length -1; row++) {
            for (int column = 0; column < newMatrix[row].length; column++) {
                System.out.print(newMatrix[row][column] + " ");
            }
            System.out.println();
        }
    }


    private static void copyMatrix(int[][] matrix, String[][] newMatrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[i + 1][j + 1] = "" + matrix[i][j];
            }
        }
    }

    private static void initializeFirstRow(String[][] newMatrix) {
        newMatrix[0][0] = " ";
        newMatrix[0][1] = "0";
        newMatrix[0][2] = "1";
        newMatrix[0][3] = "2";
        newMatrix[0][4] = "3";
        newMatrix[0][5] = "4";
        newMatrix[0][6] = "5";
        newMatrix[0][7] = "6";
        newMatrix[0][8] = "7";
        newMatrix[0][9] = "8";
        newMatrix[0][10] = "9";
    }


    // The main function to construct MST using Kruskal's algorithm
    void KruskalAlgo()
    {
        Edge result[] = new Edge[V];  // Tnis will store the resultant MST
        int e = 0;  // An index variable, used for result[]
        int i = 0;  // An index variable, used for sorted edges
        for (i=0; i<V; ++i)
            result[i] = new Edge();
        Arrays.sort(edge);

        // Allocate memory for creating V ssubsets
        subset subsets[] = new subset[V];
        for(i=0; i<V; ++i)
            subsets[i]=new subset();

        // Create V subsets with single elements
        for (int v = 0; v < V; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;  // Index used to pick next edge

        // Number of edges to be taken is equal to V-1
        while (e < V - 1)
        {
            Edge next_edge = new Edge();
            next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // If including this edge does't cause cycle,
            // include it in result and increment the index 
            // of result for next edge
            if (x != y)
            {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }

        // print the contents of result[] to display
        // the built MST
        System.out.println("Following are the edges in " +
                "the constructed MST");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src+" -- " +
                    result[i].dest+" == " + result[i].weight);
    }



    // Driver Program
    public static void main (String[] args)
    {
        int V = 10;  // Number of vertices in graph
        int E = 17;  // Number of edges in graph
        Graph graph = new Graph(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 5;
        graph.addEdge(0,1,5);


        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 6;
        graph.edge[1].weight = 8;
        graph.addEdge(0,6,5);

        // add edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 7;
        graph.edge[2].weight = 8;
        graph.addEdge(0,7,8);


        // add edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 2;
        graph.edge[3].weight = 3;
        graph.addEdge(1,2,3);

        // add edge 2-3
        graph.edge[4].src = 1;
        graph.edge[4].dest = 7;
        graph.edge[4].weight = 4;
        graph.addEdge(1,7,4);

        graph.edge[5].src = 1;
        graph.edge[5].dest =3;
        graph.edge[5].weight =6;
        graph.addEdge(1,3,6);

        graph.edge[6].src = 2;
        graph.edge[6].dest = 3;
        graph.edge[6].weight =3;
        graph.addEdge(2,3,3);

        graph.edge[7].src =2;
        graph.edge[7].dest =7;
        graph.edge[7].weight = 4;
        graph.addEdge(2,7,4);

        graph.edge[8].src =3;
        graph.edge[8].dest =4;
        graph.edge[8].weight=1;
        graph.addEdge(3,4,1);

        graph.edge[9].src = 3;
        graph.edge[9].dest = 5;
        graph.edge[9].weight =2;
        graph.addEdge(3,5,2);

        graph.edge[10].src = 4;
        graph.edge[10].dest =5;
        graph.edge[10].weight=3;
        graph.addEdge(4,5,3);

        graph.edge[11].src = 4;
        graph.edge[11].dest =7;
        graph.edge[11].weight=4;
        graph.addEdge(4,7,4);

        graph.edge[12].weight =3;
        graph.edge[12].src = 5;
        graph.edge[12].dest =6;
        graph.addEdge(3,5,6);

        graph.edge[13].src = 6;
        graph.edge[13].dest =7;
        graph.edge[13].weight=4;
        graph.addEdge(6,7,4);

        graph.edge[14].src =1;
        graph.edge[14].dest =7;
        graph.edge[14].weight=4;
        graph.addEdge(1,7,4);

        graph.edge[15].src =8;
        graph.edge[15].dest =2;
        graph.edge[15].weight=9;
        graph.addEdge(8,2,9);

        graph.edge[16].src =9;
        graph.edge[16].dest =3;
        graph.edge[16].weight=4;
        graph.addEdge(9,3,4);
        graph.KruskalAlgo();
       graph.convertMatrix(graph.adjacentMatrix);

    }

}