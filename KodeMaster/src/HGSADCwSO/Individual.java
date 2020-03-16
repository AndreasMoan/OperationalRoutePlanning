package HGSADCwSO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Individual {

    private int fitness;
    private double penalizedCost;

    private boolean feasability;

    //private HashMap<Integer, Set<Integer>> vesselOrderChromosome;
    private HashMap<Integer, ArrayList<Integer>> vesselTourChromosome;

    public Individual(HashMap<Integer, ArrayList<Integer>>  vesselTourChromosome) {
        this.vesselTourChromosome = vesselTourChromosome;
    }

    public HashMap<Integer, ArrayList<Integer>> getVesselTourChromosome() {
        return vesselTourChromosome;
    }

    public int getFitness() {
        return fitness;
    }

    public boolean isFeasible(){
        return feasability;
    }

    public double getPenalizedCost() {
        return penalizedCost;
    }

    public void setPenalizedCost(Double cost) {
        penalizedCost = cost;
    }
}
