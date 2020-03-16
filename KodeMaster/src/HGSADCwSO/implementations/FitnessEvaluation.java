package HGSADCwSO.implementations;

import HGSADCwSO.Individual;
import HGSADCwSO.Voyage;
import HGSADCwSO.protocols.FitnessEvaluationProtocol;

import java.util.ArrayList;

public class FitnessEvaluation implements FitnessEvaluationProtocol {

    @Override
    public void updateBiasedFitness(ArrayList<Individual> population) {

    }

    @Override
    public void addDiversityDistance(Individual individual) {

    }

    @Override
    public void removeDiersityDistance(Individual individual) {

    }

    @Override
    public double getHammingDistance(Individual individual1, Individual individual2) {
        return 0;
    }

    @Override
    public void setPenailizedCostIndividual(Individual individual, double durationViolationPenalty, double capacityViolationPenalty, double numberOfOrdersPenalty) {

    }

    @Override
    public void setPenalizedCostIndividual(Individual individual) {

    }

    @Override
    public double getPenalizedCost(Voyage voyage) {
        return 0;
    }

    @Override
    public double getPenalizedCost(Voyage voyage, double durationViolationPenalty, double capacityViolationPenalty, double numberOfOrdersPenalty) {
        return 0;
    }

    @Override
    public double getDurationViolationPenalty() {
        return 0;
    }

    @Override
    public double getCapacityViolationPenalty() {
        return 0;
    }

    @Override
    public double getNumberOfInstallationsPenalty() {
        return 0;
    }

    @Override
    public void setDurationViolationPenalty(double durationViolationPenalty) {

    }

    @Override
    public void setCapacityViolationPenalty(double capacityViolationPenalty) {

    }

    @Override
    public void setNumberOfInstallationsViolationPenalty(double numberOfInstallationsViolationPenalty) {

    }

    @Override
    public void setPenalizedCostPopulation(ArrayList<Individual> population) {

    }
}
