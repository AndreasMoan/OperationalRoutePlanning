package HGSADCwSO.implementations;

import HGSADCwSO.Individual;
import HGSADCwSO.Order;
import HGSADCwSO.ProblemData;
import HGSADCwSO.Vessel;
import HGSADCwSO.protocols.InitialPopulationProtocol;

import java.util.*;

public class InitialPopulationStandard implements InitialPopulationProtocol {

    private ProblemData problemData;
    private int numberOfRestarts;

    public InitialPopulationStandard(ProblemData problemData) {
        this.problemData = problemData;
        numberOfRestarts = 0;
    }

    public Individual createIndividual(){
        HashMap<Integer, Set<Integer>> vesselOrderChromsome = createVesselOrderChromosome();
        HashMap<Integer, ArrayList<Integer>> vesselTourChromosome = createVesselTourChromosome(vesselOrderChromsome);
        Individual kid = new Individual(vesselTourChromosome);
        return kid;
    }

    public int getNumberOfConstructionHeuristicRestarts() {
        return 0;
    }


    private HashMap<Integer, Set<Integer>> createVesselOrderChromosome() {

        HashMap<Integer, Set<Integer>> vesselOrderChromosome = new HashMap<Integer, Set<Integer>>();
        HashMap<Integer, Order> orders = problemData.getOrdersByNumber();

        int n = 0;
        while (orders.get(n).getDay() == 0) {
            int randomVessel = new Random().nextInt(problemData.getNumberOfVessels());
            vesselOrderChromosome.get(randomVessel).add(n);
            n++;
        }
        return vesselOrderChromosome;
    }

    private HashMap<Integer, ArrayList<Integer>> createVesselTourChromosome(HashMap<Integer,Set<Integer>> vesselOrderChromosome) {

        HashMap<Integer, ArrayList<Integer>> vesselTourChromosome = new HashMap<Integer, ArrayList<Integer>>();

        for (int i = 0; i < problemData.getNumberOfVessels(); i++) {
            for (int j : vesselOrderChromosome.get(i)) {
                vesselTourChromosome.get(i).add(j);
            }
            Collections.shuffle(vesselTourChromosome.get(i));
        }
        return vesselTourChromosome;
    }


}
