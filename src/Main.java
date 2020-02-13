import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    static List<String> words = new ArrayList<>();

    public static void main(String[] args) {

        List<Long> linearTimes = new ArrayList<>();
        List<Long> binaryTimes = new ArrayList<>();
        Random ayn = new Random();
        int testCount = 216216;


        try {
            words = Files.readAllLines(Paths.get("words.txt"));
        } catch (Exception bullshit) {
            System.out.println("Fuck you if this happens");
        }

        System.out.println("Starting linear search tests.");

        for (int i = 0; i != testCount; i++) {
            String find = words.get(ayn.nextInt(words.size()));
            long startTime = System.currentTimeMillis();
            if (linearSearch(find) != -1){
                linearTimes.add(System.currentTimeMillis() - startTime);
                //System.out.println("Found " + find + " in " + (System.currentTimeMillis() - startTime) + " milliseconds.");
            }
        }

        System.out.println("Starting binary search tests.");

        for (int i = 0; i != testCount; i++) {
            String find = words.get(ayn.nextInt(words.size()));
            long startTime = System.currentTimeMillis();
            if (binarySearch(find, 0, words.size()) != -1){
                binaryTimes.add(System.currentTimeMillis() - startTime);
                //System.out.println("Found " + find + " in " + (System.currentTimeMillis() - startTime) + " milliseconds.");
            } else {
                System.out.println("Something went wrong.");
                System.exit(0);
            }
        }

        System.out.println("Tests complete. Total tests: " + testCount);
        System.out.println("Average time (milliseconds):");
        System.out.println("Linear: " + linearTimes.stream().mapToDouble(a -> a).average().getAsDouble());
        System.out.println("Binary: " + binaryTimes.stream().mapToDouble(a -> a).average().getAsDouble());
        System.out.println("Total time (milliseconds):");
        System.out.println("Linear: " + linearTimes.stream().mapToDouble(a -> a).sum());
        System.out.println("Binary: " + binaryTimes.stream().mapToDouble(a -> a).sum());


    }

    static int linearSearch(String search){
        for(int i = 0; i != words.size(); i++){
            if(words.get(i).equals(search)){
                return i;
            }
        }
        return -1;
    }

    static int binarySearch(String search, int left, int right) {
        if (right >= left) {
            int mid = (left + right) / 2;
            if (words.get(mid).equals(search)) {
                return mid;
            } else {
                if(words.get(mid).compareToIgnoreCase(search) > 0){
                    //If it returns a positive number, it's closer to the start of the array.
                    return binarySearch(search, left, mid);
                } else {
                    return binarySearch(search, mid, right);
                }
            }
        } else {
            return -1;
        }

    }

}

