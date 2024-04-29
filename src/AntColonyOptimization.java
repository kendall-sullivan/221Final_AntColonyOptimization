package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.Comp128Graph.AdjacencyListGraph;

/**
 * Ant Colony Optimization Implementation
 */
public class AntColonyOptimization {
    private boolean terminated = false;
    private Random rand = new Random();
    
    public List<Edge> antColonyOptimization(AdjacencyListGraph G, double c, int n, int m, double a, double b, double rho) {
        
        List<Edge> bestPath = null;
        double bestPathCost = Double.MAX_VALUE;
        int count = 0;
        initializePheromoneLevels(G, count);
        while (!terminated) {
            List<List<Edge>> allPaths = new ArrayList<>();
            for (int k = 0; k < m; k++) {
                List<Edge> path = oneAntsPath(G, a, b);
            }
        }

        return bestPath;
    }


    public void initializePheromoneLevels(AdjacencyListGraph G, int c) {
        for (Edge edge : G.getEdges()) {
            edge.setPheromoneLevel(c);
        }
    }


    public List<Edge> oneAntsPath(AdjacencyListGraph G, double a, double b) {
        List<Edge> path = new ArrayList<>();
        double pathTotalCost = 0;
        int randomVertex = rand.nextInt(G.getAdj().length);
        // int v = G.getAdj().get(randomVertex);
        

        return path;
    }
    
}