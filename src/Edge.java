package src;

public class Edge {
    private int start;
    private int end;
    private double cost;
    private double weight;
    private double pheromoneLevel;

    public Edge(int start, int end, double weight, double pheromoneLevel) {
        this.start = start;
        this.end = end;
        this.cost = this.cost;
        this.weight = weight;
        this.pheromoneLevel = pheromoneLevel;
    }

    public double getCost() {
        return this.cost;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }
    
    public double getWeight() {
        return this.weight;
    }

    public double setWeight(double w) {
        return this.weight = w;
    }

    public double getPheromoneLevel() {
        return this.pheromoneLevel;
    }

    public double setPheromoneLevel(double pL) {
        return this.pheromoneLevel = pL;
    }
}
