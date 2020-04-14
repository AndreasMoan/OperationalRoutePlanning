package HGSADCwSO.protocols;

import HGSADCwSO.Genotype;
import HGSADCwSO.Individual;
import HGSADCwSO.Voyage;

import java.util.ArrayList;

public interface FitnessEvaluationProtocol {

    public void evaluate(Genotype genotype);

    /*
    public void updateBiasedFitness(ArrayList<Individual> population);

    public void addDiversityDistance(Individual individual);

    public void removeDiersityDistance(Individual individual);

    public double getHammingDistance(Individual individual1, Individual individual2);

    public void setPenailizedCostIndividual(Individual individual, double durationViolationPenalty, double capacityViolationPenalty, double numberOfOrdersPenalty);

    public void setPenalizedCostIndividual(Individual individual);

    public double getPenalizedCost(Voyage voyage);

    public double getPenalizedCost(Voyage voyage,  double durationViolationPenalty, double capacityViolationPenalty, double numberOfOrdersPenalty);

    public double getDurationViolationPenalty();

    public double getCapacityViolationPenalty();

    public double getNumberOfInstallationsPenalty();

    public void setDurationViolationPenalty(double durationViolationPenalty);

    public void setCapacityViolationPenalty(double capacityViolationPenalty);

    public void setNumberOfInstallationsViolationPenalty(double numberOfInstallationsViolationPenalty);

    void setPenalizedCostPopulation(ArrayList<Individual> population);

    */
}
