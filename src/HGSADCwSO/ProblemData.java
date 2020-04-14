package HGSADCwSO;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;


public class ProblemData {

    private HashMap<String, String> problemInstanceParameters, heuristicParameters;
    private ArrayList<Installation> installations, customerInstallations;
    private ArrayList<Vessel> vessels;
    private ArrayList<Order> orders;
    private HashMap<Installation, HashMap<Installation, Double>> distances;
    private HashMap<Integer, Installation> installationByNumber;
    private HashMap<Integer, Vessel> vesselByNumber;
    private HashMap<Integer, Order> ordersByNumber;
    private ArrayList<Integer> weatherStatesByHour;
    private HashMap<Integer, Double> weatherImpactByState;

    public ProblemData(HashMap<String, String> problemInstanceParameters,
                       HashMap<String, String> heuristicParameters,
                       ArrayList<Installation> installations,
                       ArrayList<Vessel> vessels,
                       HashMap<Installation, HashMap<Installation, Double>> distances,
                       ArrayList<Order> orders,
                       ArrayList<Integer> weatherStatesByHour,
                       HashMap<Integer, Double> weatherImpactByState) {
        this.problemInstanceParameters = problemInstanceParameters;
        this.heuristicParameters = heuristicParameters;
        this.installations = installations;
        this.vessels = vessels;
        this.distances = distances;
        this.orders = orders;
        this.weatherStatesByHour = weatherStatesByHour;
        this.weatherImpactByState = weatherImpactByState;
        setCustomerInstallations();
        setInstallationsByNumber();
        setVesselsByNumber();
        setOrdersByNumber();
    }

    public void setCustomerInstallations() {
        customerInstallations = new ArrayList<>();
        customerInstallations.addAll(installations);
        Installation depot = null;
        for (Installation installation : installations) {
            if (installation.getNumber() == 0) {
                depot = installation;
            }
        }
        customerInstallations.remove(depot);
    }

    private void setInstallationsByNumber() {
        installationByNumber = new HashMap<Integer, Installation>();
        for (Installation installation : installations) {
            installationByNumber.put(installation.getNumber(), installation);
        }
    }

    private void setVesselsByNumber() {
        vesselByNumber = new HashMap<Integer, Vessel>();
        for (Vessel vessel : vessels) {
            vesselByNumber.put(vessel.getNumber(), vessel);
        }
    }

    private void setOrdersByNumber() {
        ordersByNumber = new HashMap<Integer, Order>();
        for (Order order : orders) {
            int orderNumber = order.getNumber();
            ordersByNumber.put(orderNumber, order);
        }
    }

    public HashMap<String, String> getProblemInstanceParameters() {
        return problemInstanceParameters;
    }

    public ArrayList<Installation> getInstallations() {
        return installations;
    }

    public ArrayList<Vessel> getVessels() {
        return vessels;
    }

    public HashMap<Integer, Installation> getInstallationByNumber() {
        return installationByNumber;
    }

    public HashMap<Integer, Vessel> getVesselByNumber() {
        return vesselByNumber;
    }

    public HashMap<String, String> getHeuristicParameters() {
        return heuristicParameters;
    }

    public double getDistance(Installation fromInstallation, Installation toInstallation) {
        return distances.get(fromInstallation).get(toInstallation);
    }

    public double getDistanceByIndex(int fromInstallationIndex, int toInstallationIndex) {
        return getDistance(getInstallationByNumber().get(fromInstallationIndex), getInstallationByNumber().get(toInstallationIndex));
    }

    public int getInstallationNumberByOrderNumber(int orderNumber) {
        return getOrdersByNumber().get(orderNumber).getInstallation().getNumber();
    }

    public int getHeuristicParameterInt(String parameterName) {
        return Integer.parseInt(heuristicParameters.get(parameterName));
    }

    public double getHeuristicParameterDouble(String parameterName) {
        return Utilities.parseDouble(heuristicParameters.get(parameterName));
    }

    public double getProblemInstanceParameterDouble(String parameterName) {
        return Utilities.parseDouble(problemInstanceParameters.get(parameterName));
    }

    public int getNumberOfOrders() {
        return orders.size();
    }

    public ArrayList<Installation> getCustomerInstallations() {
        return customerInstallations;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public HashMap<Integer, Order> getOrdersByNumber() {
        return ordersByNumber;
    }

    public int getNumberOfVessels() {
        return vessels.size();
    }

    public void printProblemData() {
    }

    public ArrayList<Integer> getWeatherStateByHour() {
        return weatherStatesByHour;
    }

    public HashMap<Integer, Double> getWeatherImpactByState(){
        return weatherImpactByState;
    }

    public Double getWeatherImpactByHour(int hour) {
        return getWeatherImpactByState().get(getWeatherStateByHour().get(hour));
    }
}
