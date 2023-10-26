import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int measurements = 100;

    public static void main(String[] args) throws IOException {
        TXTFileReader fileReaderS = new TXTFileReader("data/short.txt");
        TXTFileReader fileReaderL = new TXTFileReader("data/long.txt");
        String fileContentS = fileReaderS.GetContent();
        String fileContentL = fileReaderL.GetContent();

        List<String> wordsS = new ArrayList<>(
                List.of(fileContentS.split("\\s+"))); // split the string based on spaces
        List<String> wordsL = new ArrayList<>(
                List.of(fileContentL.split("\\s+"))); // split the string based on spaces

        System.out.println("##### Short Text Document #####");
        StreamOperations(wordsS);
        ParallelStreamOperations(wordsS);

        System.out.println("##### Long Text Document #####");
        StreamOperations(wordsL);
        ParallelStreamOperations(wordsL);
    }

    static void StreamOperations(List<String> words){
        long start = 0;
        long end = 0;
        long value = 0;

        System.out.println("### Stream ###");

        start = System.nanoTime();
        for (int i=0; i < measurements; i++) {
            value = words.stream().count();
        }
        end = System.nanoTime();
        System.out.println("Word Count: " + value);
        PrintAverageExecTime(end - start, measurements);

        start = System.nanoTime();
        for (int i=0; i < measurements; i++) {
            value = words.stream().distinct().count();
        }
        end = System.nanoTime();
        System.out.println("Distinct Word Count: " + value);
        PrintAverageExecTime(end - start, measurements);

        start = System.nanoTime();
        for (int i=0; i < measurements; i++) {
            // the following two lines work similarly
            // value = words.stream().map(word -> word.length()).distinct().count();
            value = words.stream().map(String::length).distinct().count();
        }
        end = System.nanoTime();
        System.out.println("Distinct Word Sizes: " + value);
        PrintAverageExecTime(end - start, measurements);

        start = System.nanoTime();
        for (int i=0; i < measurements; i++) {
            value = words.stream().filter(word -> word.compareTo("the") == 0).count();
        }
        end = System.nanoTime();
        System.out.println("Occurrences of \"the\": " + value);
        PrintAverageExecTime(end - start, measurements);
    }

    static void ParallelStreamOperations(List<String> words){
        long start = 0;
        long end = 0;
        long value = 0;

        System.out.println("### Parallel Stream ###");

        start = System.nanoTime();
        for (int i=0; i < measurements; i++) {
            value = words.parallelStream().count();
        }
        end = System.nanoTime();
        System.out.println("Word Count: " + value);
        PrintAverageExecTime(end - start, measurements);

        start = System.nanoTime();
        for (int i=0; i < measurements; i++) {
            value = words.parallelStream().distinct().count();
        }
        end = System.nanoTime();
        System.out.println("Distinct Word Count: " + value);
        PrintAverageExecTime(end - start, measurements);

        start = System.nanoTime();
        for (int i=0; i < measurements; i++) {
            value = words.parallelStream().map(String::length).distinct().count();
        }
        end = System.nanoTime();
        System.out.println("Distinct Word Sizes: " + value);
        PrintAverageExecTime(end - start, measurements);

        start = System.nanoTime();
        for (int i=0; i < measurements; i++) {
            value = words.parallelStream().filter(word -> word.compareTo("the") == 0).count();
        }
        end = System.nanoTime();
        System.out.println("Occurrences of \"the\": " + value);
        PrintAverageExecTime(end - start, measurements);
    }

    static void PrintAverageExecTime(long executionTimeSum, int measurements){
        System.out.println("Average Execution time (ns): " +
                executionTimeSum/measurements);
        System.out.println("- ");
    }
}
