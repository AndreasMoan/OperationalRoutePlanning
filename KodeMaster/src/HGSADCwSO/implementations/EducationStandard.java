package HGSADCwSO.implementations;

import HGSADCwSO.Individual;
import HGSADCwSO.ProblemData;
import HGSADCwSO.protocols.EducationProtocol;
import HGSADCwSO.protocols.FitnessEvaluationProtocol;

public class EducationStandard implements EducationProtocol {

    protected ProblemData problemData;
    protected FitnessEvaluationProtocol fitnessEvaluationProtocol;
    protected PenaltyAdjustmentProtocol penaltyAdjustmentProtocol;
    //protected GenoToPhenoConverterProtocol genoToPhenoConverter;
    protected boolean isRepair;
    protected int penaltyMultiplier;

    public EducationStandard(ProblemData problemData,FitnessEvaluationProtocol fitnessEvaluationProtocol) {
        this.problemData = problemData;
        this.fitnessEvaluationProtocol = fitnessEvaluationProtocol;
        this.isRepair = false;
        this.penaltyMultiplier = 1;
    }

    @Override
    public void educate(Individual individual) {
        //TODO
    }

    /*
    protected void updatePenaltyAdjustmentCounter(Individual individual) {
        if (!isRepair) {
            penaltyAdjustmentProtocol.countAddedIndividual(individual);
        }
    }
    */

    @Override
    public void repairEducate(Individual individual, int penaltyMultiplier) {
        isRepair = true;
        this.penaltyMultiplier = penaltyMultiplier;

        educate(individual);

        isRepair = false;
        this.penaltyMultiplier = 1;
    }

}
