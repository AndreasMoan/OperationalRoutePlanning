package HGSADCwSO;

import HGSADCwSO.protocols.FitnessEvaluationProtocol;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Individual {

    private int fitness;
    private double penalizedCost, scheduleCost, durationViolation, capacityViolation;
    private Set<Integer> departingVessels; //TODO må initialiseres.

    private boolean feasibility;

    //private HashMap<Integer, Set<Integer>> vesselOrderChromosome;

    private HashMap<Integer, ArrayList<Integer>> vesselTourChromosome;

    public Individual(HashMap<Integer, ArrayList<Integer>>  vesselTourChromosome) {
        this.vesselTourChromosome = vesselTourChromosome;
    }

    public Set<Integer> getDepartingVessels() {return departingVessels;}

    public HashMap<Integer, ArrayList<Integer>> getVesselTourChromosome() {
        return vesselTourChromosome;
    }

    public void setVesselTourChromosome(HashMap<Integer, ArrayList<Integer>> vesselTour){
        this.vesselTourChromosome = vesselTour;
    }

    public int getFitness() {
        return fitness;
    }

    public boolean isFeasible(){
        return feasibility;
    }

    public double getPenalizedCost() { return penalizedCost; }

    public void setPenalizedCost(Double cost) {
        penalizedCost = cost;
    }

    public void updatePenalizedCostForChromosome(FitnessEvaluationProtocol fitnessEvaluationProtocol) {
        fitnessEvaluationProtocol.setPenalizedCostIndividual(this);
    }

    public double getScheduleCost() {return scheduleCost; }

    public void setScheduleCost(double cost) {this.scheduleCost = cost;}

    public double getDurationViolation() {return durationViolation; }

    public void setDurationViolation(double violation) {this.durationViolation = violation; }

    public double getCapacityViolation() {return capacityViolation; }

    public void setCapacityViolation(double violation) {this.capacityViolation = violation; }
}
