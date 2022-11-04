import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
//        TXTFileReader fileReader = new TXTFileReader("data/small.txt");
        TXTFileReader fileReader = new TXTFileReader("data/big.txt");
        String fileContent = fileReader.GetContent();

        ArrayList<String> words = new ArrayList<>(List.of(fileContent.split("\\s+"))); // split the string based on spaces

        long start = 0;
        long end = 0;
        long value = 0;
        int measurements = 100;
        long executionTimeSum;

        System.out.println("### Stream ###");
        //
        executionTimeSum = 0;
        for (int i=0; i<measurements; i++) {
            start = System.nanoTime();
            value = words.stream().count();
            end = System.nanoTime();
            executionTimeSum += (end - start);
        }
        System.out.println("Word Count: " + value);
        System.out.println("Average Execution time (ns): " + executionTimeSum/measurements);
        System.out.println("- ");
        //
        executionTimeSum = 0;
        for (int i=0; i<measurements; i++) {
            start = System.nanoTime();
            value = words.stream().distinct().count();
            end = System.nanoTime();
            executionTimeSum += (end - start);
        }
        System.out.println("Distinct Word Count: " + value);
        System.out.println("Average Execution time (ns): " + executionTimeSum/measurements);
        System.out.println("- ");
        //
        executionTimeSum = 0;
        for (int i=0; i<measurements; i++) {
            start = System.nanoTime();
            value = words.stream().map(word -> word.length()).distinct().count();
            end = System.nanoTime();
            executionTimeSum += (end - start);
        }
        System.out.println("Distinct Word Sizes: " + value);
        System.out.println("Average Execution time (ns): " + executionTimeSum/measurements);
        System.out.println("- ");
        //
        executionTimeSum = 0;
        for (int i=0; i<measurements; i++) {
            start = System.nanoTime();
            value = words.stream().filter(word -> word.compareTo("the") == 0).count();
            end = System.nanoTime();
            executionTimeSum += (end - start);
        }
        System.out.println("Occurrences of \"the\": " + value);
        System.out.println("Average Execution time (ns): " + executionTimeSum/measurements);
        System.out.println("- ");


        System.out.println("### Parallel Stream ###");
        //
        executionTimeSum = 0;
        for (int i=0; i<measurements; i++) {
            start = System.nanoTime();
            value = words.parallelStream().count();
            end = System.nanoTime();
            executionTimeSum += (end - start);
        }
        System.out.println("Word Count: " + value);
        System.out.println("Average Execution time (ns): " + executionTimeSum/measurements);
        System.out.println("- ");
        //
        executionTimeSum = 0;
        for (int i=0; i<measurements; i++) {
            start = System.nanoTime();
            value = words.parallelStream().distinct().count();
            end = System.nanoTime();
            executionTimeSum += (end - start);
        }
        System.out.println("Distinct Word Count: " + value);
        System.out.println("Average Execution time (ns): " + executionTimeSum/measurements);
        System.out.println("- ");
        //
        executionTimeSum = 0;
        for (int i=0; i<measurements; i++) {
            start = System.nanoTime();
            value = words.parallelStream().map(word -> word.length()).distinct().count();
            end = System.nanoTime();
            executionTimeSum += (end - start);
        }
        System.out.println("Distinct Word Sizes: " + value);
        System.out.println("Average Execution time (ns): " + executionTimeSum/measurements);
        System.out.println("- ");
        //
        executionTimeSum = 0;
        for (int i=0; i<measurements; i++) {
            start = System.nanoTime();
            value = words.parallelStream().filter(word -> word.compareTo("the") == 0).count();
            end = System.nanoTime();
            executionTimeSum += (end - start);
        }
        System.out.println("Occurrences of \"the\": " + value);
        System.out.println("Average Execution time (ns): " + executionTimeSum/measurements);
        System.out.println("- ");
    }
}