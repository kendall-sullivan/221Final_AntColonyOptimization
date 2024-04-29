package src;

public class Edge {
    private int start;
    private int end;
    private double weight;
    private double pheromoneLevel;

    public Edge(int start, int end, double weight, double pheromoneLevel) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.pheromoneLevel = pheromoneLevel;
    }

    public double getWeight() {
        return weight;
    }

    public double setWeight(double w) {
        return weight = w;
    }

    public double getPheromoneLevel() {
        return pheromoneLevel;
    }

    public double setPheromoneLevel(double pL) {
        return pheromoneLevel = pL;
    }
}
