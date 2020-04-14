package HGSADCwSO;

import HGSADCwSO.protocols.FitnessEvaluationProtocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Individual {

    private double fitness;
    private Genotype genotype;
    private FitnessEvaluationProtocol fitnessEvaluationProtocol;

    //private double penalizedCost;

    private boolean feasibility;

    //private HashMap<Integer, Set<Integer>> vesselOrderChromosome;

    public Individual(HashMap<Integer, ArrayList<Integer>>  vesselTourChromosome, FitnessEvaluationProtocol fitnessEvaluationProtocol) {
        this.genotype = new Genotype(vesselTourChromosome);
        this.fitnessEvaluationProtocol = fitnessEvaluationProtocol;
    }

    public HashMap<Integer, ArrayList<Integer>> getVesselTourChromosome() {
        return genotype.getVesselTourChromosome();
    }

    public Set<Integer> getDepartingVessels() {
        return genotype.getVesselTourChromosome().keySet();
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness() {
        this.fitness = fitnessEvaluationProtocol.evaluate();
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
