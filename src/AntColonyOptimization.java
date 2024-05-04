package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;

import src.Comp128Graph.AdjacencyListGraph;
import src.Comp128Graph.Bag;

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
            for (int k = 0; k < m; k++) {
                HashMap<List<Edge>, Double> pathAndCost = this.oneAntsPath(G, a, b);
                double pathCost = 0;
                List<Edge> path = null;
                for (double value : pathAndCost.values()) {
                    pathCost = value;
                }
                for (List<Edge> key : pathAndCost.keySet()) {
                    path = key;
                }
                if (bestPath == null || pathCost < bestPathCost) {
                    bestPath = path;
                    bestPathCost = pathCost;
                }
            }
            updatePheromones(G, rho);
            if (count == n) {
                break;
            } else {
                count++;
            }
        }
        return bestPath;
    }


    private void initializePheromoneLevels(AdjacencyListGraph G, int c) {
        for (Edge edge : G.getEdges()) {
            edge.setPheromoneLevel(c);
        }
    }

    // create a path for an ant 
    private HashMap<List<Edge>, Double> oneAntsPath(AdjacencyListGraph G, double a, double b) {
        List<Edge> path = new ArrayList<>();
        double pathTotalCost = 0;
        int current = this.rand.nextInt(G.getAdj().length);
        boolean[] visited = new boolean[G.getAdj().length];  
        visited[current] = true;

        while (this.notAllVisited(visited)) {
            HashMap<Edge, Double> probabilities = this.getProbabilities(current, G, visited, a, b);
            Edge next = this.chooseNextVertex(probabilities);  
            if (next == null) break; 
            pathTotalCost += next.getCost();
            path.add(next);
            current = next.getStart();
            visited[current] = true;
        }
        HashMap<List<Edge>, Double> pathAndCost = new HashMap<>();
        pathAndCost.put(path, pathTotalCost);
        return pathAndCost;
    }

    // condition for if visited
    private boolean notAllVisited(boolean[] visited) {
        for (boolean v : visited) {
            if (!v) return true;
        }
        return false;
    }

    private HashMap<Edge, Double> getProbabilities(int currentVertex, AdjacencyListGraph G, boolean[] visited, double a, double b) {
        Bag<Integer> edgesInts = G.getAdj()[currentVertex];
        List<Edge> edges = findEdges(G, edgesInts, currentVertex);
        HashMap<Edge, Double> probabilitiesMap = new HashMap<>();
        double sum = 0.0; 
        for (Edge edge : edges) {
            if (!visited[edge.getEnd()]) { 
                double heuristic = 1.0 / edge.getCost(); 
                double pheromoneLevel = edge.getPheromoneLevel();
                double numerator = Math.pow(pheromoneLevel, a) * Math.pow(heuristic, b);
                double denominator = Math.pow(pheromoneLevel, a) + Math.pow(heuristic, b);
                double probability = numerator / denominator; 
                probabilitiesMap.put(edge, probability);
                sum += probability;
            }
        }
        //normalizing
        for (Edge edge : probabilitiesMap.keySet()) {
            double currProb = probabilitiesMap.get(edge);
            currProb = probabilitiesMap.get(edge) / sum;
            probabilitiesMap.replace(edge, currProb);
        }
        return probabilitiesMap;
    }

    // code to choose probability modified from https://stackoverflow.com/questions/9330394/how-to-pick-an-item-by-its-probability
    private Edge chooseNextVertex(HashMap<Edge, Double> probabilities) {
        double totalSum = 0.0;
        for (double probability : probabilities.values()) {
            totalSum += probability;
        }
        double index = rand.nextDouble(totalSum);
        double sum = 0;
        double chosenProb = 0;
        for (double probability : probabilities.values()) {
            if(sum < index) {
                sum += probability;
                index++;
            } else {
                chosenProb = probability;
                break;
            }
        }
        for (Entry<Edge, Double> entry : probabilities.entrySet()) {
            if (Objects.equals(chosenProb, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void updatePheromones(AdjacencyListGraph G, double rho) {
        for (Edge edge : G.getEdges()) { 
            edge.setPheromoneLevel((1 - rho) * edge.getPheromoneLevel());
        }
    }

    private List<Edge> findEdges(AdjacencyListGraph G, Bag<Integer> edgesInts, int currentVertex) {
        List<Edge> edges = new ArrayList<>();
        List<Edge> allEdges = G.getEdges();
        while (edgesInts.iterator().hasNext()) {
            for (Edge e : allEdges) {
                if (e.getStart() == currentVertex) {
                    edges.add(e);
                }
            }
        }
        return edges;
    }
}