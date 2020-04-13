package HGSADCwSO;

import HGSADCwSO.protocols.FitnessEvaluationProtocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Individual {

    private double fitness;
    //private double penalizedCost;

    private boolean feasibility;

    //private HashMap<Integer, Set<Integer>> vesselOrderChromosome;
    private HashMap<Integer, ArrayList<Integer>> vesselTourChromosome;

    public Individual(HashMap<Integer, ArrayList<Integer>>  vesselTourChromosome, FitnessEvaluationProtocol fitnessEvaluationProtocol) {
        this.vesselTourChromosome = vesselTourChromosome;
        fitnessEvaluationProtocol.evaluate(this);
    }

    public HashMap<Integer, ArrayList<Integer>> getVesselTourChromosome() {
        return vesselTourChromosome;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public boolean isFeasible(){
        return feasibility;
    }

    /*

    public double getPenalizedCost() {
        return penalizedCost;
    }

    public void setPenalizedCost(Double cost) {
        penalizedCost = cost;
    }

     */
}
