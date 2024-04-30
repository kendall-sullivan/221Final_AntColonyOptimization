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
        this.initializePheromoneLevels(G, count);
        while (!this.terminated) {
            List<List<Edge>> allPaths = new ArrayList<>();
            for (int k = 0; k < m; k++) {
                List<Edge> path = this.oneAntsPath(G, a, b);
            }
        }

        return bestPath;
    }


    public void initializePheromoneLevels(AdjacencyListGraph G, int c) {
        for (Edge edge : G.getEdges()) {
            edge.setPheromoneLevel(c);
        }
    }

// create a path for an ant 
    public List<Edge> oneAntsPath(AdjacencyListGraph G, double a, double b) {
        List<Edge> path = new ArrayList<>();
        double pathTotalCost = 0;
        int randomVertex = this.rand.nextInt(G.getAdj().length);
        boolean[] visited = new boolean[G.getAdj().length];  
        visited[randomVertex] = true;

        while (this.notAllVisited(visited)) {
            double[] probabilities = this.getProbabilities(randomVertex, G, visited, a, b);
            Edge next = this.chooseNextVertex(probabilities);  
            if (next == null) break; 

            pathTotalCost += next.getCost();
            path.add(next);
            randomVertex = next.getEnd();
            visited[randomVertex] = true;
        }
        return path;
    }

    private boolean notAllVisited(boolean[] visited) {
        for (boolean v : visited) {
            if (!v) return true;
        }
        return false;
    }

    private double[] getProbabilities(int currentVertex, AdjacencyListGraph G, boolean[] visited, double a, double b) {
        
        
    
    }
        
    // private Edge chooseNextVertex(double[] probabilities) {
    // }

    
    
}