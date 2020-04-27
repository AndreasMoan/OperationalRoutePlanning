package HGSADCwSO.implementations;

import HGSADCwSO.Individual;
import HGSADCwSO.ProblemData;
import HGSADCwSO.Utilities;
import HGSADCwSO.protocols.FitnessEvaluationProtocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FitnessEvaluation implements FitnessEvaluationProtocol {
    private double nCloseProp;
    protected double nEliteProp;
    private double numberOfOrders;
    private double durationViolationPenalty;
    private double capacityViolationPenalty;
    private double deadlineViolationPenalty;
    private HashMap<Individual, HashMap<Individual, Double>> hammingDistances;


    public FitnessEvaluation(ProblemData problemData) {
        this.hammingDistances = new HashMap<Individual, HashMap<Individual,Double>>();
        this.nCloseProp = problemData.getHeuristicParameterDouble("Proportion of individuals considered for distance evaluation");
        this.nEliteProp = problemData.getHeuristicParameterDouble("Proportion of elite individuals");
        this.numberOfOrders = problemData.getNumberOfOrders();

        this.capacityViolationPenalty = problemData.getHeuristicParameterDouble("Capacity constraint violation penalty");
        this.durationViolationPenalty = problemData.getHeuristicParameterDouble("Duration constraint violation penalty");
        this.deadlineViolationPenalty = problemData.getHeuristicParameterDouble("Deadline constraint violation penalty");

    }

    @Override
    public void evaluate(Individual individual){ //TODO

    }

    //@Override
    public void updateBiasedFitness(ArrayList<Individual> population) {
        updateDiversityContribution(population);
        updatePenalizedCostRank(population);
        updateDiversityContributionRank(population);
        calculateBiasedFitness(population);
    }

    protected void updateDiversityContribution(ArrayList<Individual> population) {
        int nClose = (int) (population.size() * nCloseProp);
        for (Individual individual : population){
            ArrayList<Individual> nClosest = getnClosest(individual, nClose);
            double distance = 0;
            for (Individual close : nClosest){
                distance += hammingDistances.get(individual).get(close);
            }
            double diversityContribution = distance / nClose;
            individual.setDiversityContribution(diversityContribution);
        }
    }

    protected void updatePenalizedCostRank(ArrayList<Individual> population) {
        Collections.sort(population, Utilities.getPenalizedCostComparator());
        for (int i = 0; i < population.size(); i++){
            Individual individual = population.get(i);
            individual.setCostRank(i+1);
        }
    }

    protected void updateDiversityContributionRank(ArrayList<Individual> population) {
        Collections.sort(population, Utilities.getDiversityContributionComparator());
        for(int i = 0; i < population.size(); i++){
            Individual individual = population.get(i);
            individual.setDiversityRank(i+1);
        }
    }

    protected void calculateBiasedFitness(ArrayList<Individual> population) {
        int nIndividuals = population.size();
        double nElite = nIndividuals * nEliteProp;
        for (Individual individual : population){
            double biasedFitness = individual.getCostRank() + (1 - (nElite/nIndividuals)) * individual.getDiversityRank();
            individual.setBiasedFitness(biasedFitness);
        }
    }

    //@Override //The normalized Hamming distance counts the number of orders that are delivered with a different PSVs. Bør man kanskje også telle antall ganger ordre blir levert på ulike dager? Tenker kanskje dette kan droppes i og med at man har deadlines. Da blir jo naturligvis de tidligste fristene besøkt først
    public double getHammingDistance(Individual individual1, Individual individual2) {
        return hammingDistances.get(individual1).get(individual2);
    }

    //@Override
    public void addDiversityDistance(Individual individual) {
        HashMap<Individual, Double> individualHammingDistances = new HashMap<Individual, Double>();
        for (Individual existingIndividual : hammingDistances.keySet()){
            HashMap<Individual, Double> existingIndividualDistances = hammingDistances.get(existingIndividual);
            double normalizedHammingDistance = getNormalizedHammingDistance(individual, existingIndividual);
            existingIndividualDistances.put(individual, normalizedHammingDistance);
            individualHammingDistances.put(existingIndividual, normalizedHammingDistance);
            hammingDistances.put(existingIndividual, existingIndividualDistances);
        }
        hammingDistances.put(individual, individualHammingDistances);
    }

    //@Override
    public void removeDiversityDistance(Individual individual) {
        hammingDistances.remove(individual);
        for (Individual otherIndividual : hammingDistances.keySet()){
            hammingDistances.get(otherIndividual).remove(individual);
        }
    }

    public double getNormalizedHammingDistance(Individual individual1, Individual individual2) { //The normalized Hamming distance counts the number of orders that are delivered with different PSVs.
        HashMap<Integer, ArrayList<Integer>> chromosome1 = individual1.getVesselTourChromosome();
        HashMap<Integer, ArrayList<Integer>> chromosome2 = individual2.getVesselTourChromosome();

        int vesselDifference = 0;
        for (int vessel : chromosome1.keySet()){
            for (int order : chromosome1.get(vessel)){
                if (!chromosome2.get(vessel).contains(order)){
                    vesselDifference++;
                }
            }
        }

        int voyageDifference = 0;
        for (int vessel : chromosome1.keySet())
            for(int index = 0; index < chromosome1.get(vessel).size(); index++){
                if (chromosome1.get(vessel).size() > chromosome2.get(vessel).size()) {
                    if (index < chromosome2.get(vessel).size()-1){
                        if (chromosome2.get(vessel).get(index) != chromosome1.get(vessel).get(index)) {
                            voyageDifference++;
                        }
                    }
                    else if (index >= chromosome2.get(vessel).size()-1) {
                        voyageDifference += chromosome1.get(vessel).size() - chromosome2.get(vessel).size();
                    }
                }
                else {
                    if (chromosome2.get(vessel).get(index) != chromosome1.get(vessel).get(index)) {
                        voyageDifference++;
                    }
                }
            }
        return (vesselDifference + voyageDifference)/ (2*numberOfOrders);
    }

    private ArrayList<Individual> getnClosest(Individual individual, int nClose){
        ArrayList<Individual> nClosest = new ArrayList<Individual>();
        ArrayList<Map.Entry<Individual, Double>> otherIndividuals = new ArrayList<Map.Entry<Individual, Double>>(hammingDistances.get(individual).entrySet());
        Collections.sort(otherIndividuals, Utilities.getMapEntryWithDoubleComparator());
        for (int i = 0; i < nClose; i++){
            nClosest.add(otherIndividuals.get(i).getKey());
        }
        return nClosest;
    }

    //@Override
    public void setPenalizedCostIndividual(Individual individual, double durationViolationPenalty, double capacityViolationPenalty, double deadlineViolationPenalty) { //TODO
        double penalizedCost = individual.getScheduleCost() + individual.getDurationViolation()*durationViolationPenalty + individual.getCapacityViolation()*capacityViolationPenalty+ individual.getDeadlineViolation()*deadlineViolationPenalty;
        penalizedCost = Math.round(penalizedCost);
        individual.setPenalizedCost(penalizedCost);
    }

    //@Override
    public void setPenalizedCostIndividual(Individual individual) {//TODO

    }

    @Override
    public double getPenalizedCostOfVoyage(ArrayList<Integer> orderSequence) {
        return 0;
    }

    @Override
    public double getPenalizedCostOfVoyage(ArrayList<Integer> orderSequence, double durationViolationPenalty, double capacityViolationPenalty, double deadlineViolationPenalty) { //har bytta Voyage voyage med ArrayList<Integer> orderSequence
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
    public double getDeadlineViolationPenalty() {
        return 0;
    }

    @Override
    public void setDurationViolationPenalty(double durationViolationPenalty) {

    }

    @Override
    public void setCapacityViolationPenalty(double capacityViolationPenalty) {

    }

    @Override
    public void setDeadlineViolationPenalty(double deadlineViolationPenalty) {

    }

    @Override
    public void setPenalizedCostPopulation(ArrayList<Individual> population) {
        for (Individual individual : population){
            //evaluate(Individual individual);
        }
    }

    public void setCapacityFeasibility(){}
}
