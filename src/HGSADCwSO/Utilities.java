package HGSADCwSO;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.*;

public class Utilities {

    public static <T> T pickRandomElementFromList(List<T> list) {
        int randomIndex = new Random().nextInt(list.size());
        return list.get(randomIndex);
    }

    public static double parseDouble(String commaSeparatedDouble) {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(symbols);
        try {
            return df.parse(commaSeparatedDouble).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Something went wrong when parsing doubles");
            return -1.0;
        }
    }

    public static <T> ArrayList<T> getAllElements(ArrayList<T> list1, ArrayList<T> list2) {
        ArrayList<T> allElements = new ArrayList<T>();
        allElements.addAll(list1);
        allElements.addAll(list2);
        return allElements;
    }

    public static <T> T pickAndRemoveRandomElementFromSet(Set<T> set) {
        int randomIndex = new Random().nextInt(set.size());
        int counter = 0;
        for (T t : set) {
            if (counter == randomIndex){
                set.remove(t);
                return t;
            }
            counter++;
        }
        return null;
    }

    public static Comparator<Individual> getFitnessComparator() {
        return new Comparator<Individual>() {
            @Override
            public int compare(Individual individual1, Individual individual2) {
                if (individual1.getFitness() < individual2.getFitness()){
                    return  1;
                }
                else if (individual1.getFitness() > individual2.getFitness()) {
                    return  -1;
                }
                else {
                    return 0;
                }
            }
        };
    }
}

