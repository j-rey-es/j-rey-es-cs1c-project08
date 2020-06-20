package quickSortTest;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class RecursionLimitBench
{
    /**
     * Generates a random array given the size of a random array
     * @param size, int, size of random array
     * @return, Integer[], Array with random values
     */
    public static Integer[] generateRandomArray(int size){
        int i;
        Integer RandomArray[] = new Integer [size];
        for (i = 0; i<RandomArray.length; i++)
            RandomArray[i] = (int) (Math.random() * size);
        return RandomArray;

    }

    /**
     * Calculates the average qSortRuntime for an array of Integers
     * @param testArray, an array of Integers
     * @return double, the estimated average runtime
     */
    public static double qSortRuntime (Integer[] testArray)
    {
        long startTime, estimatedTime = 0;
        int i;
        final double NUM_OF_TRIALS = 3;
        for (i = 0; i < NUM_OF_TRIALS ; i++)
        {
            startTime = System.nanoTime();  // ------------------ start
            FHsort.quickSort(testArray);
            estimatedTime += System.nanoTime() - startTime;
        }
        return estimatedTime/NUM_OF_TRIALS;

    }

    // -------  main --------------
    public static void main(String[] args) throws Exception
    {

        int [] arraySizes = {20000,30000,40000,50000,60000,70000,80000,100000,200000,400000,600000,800000,1000000,2000000,4000000,6000000,8000000,10000000,15000000,20000000};
        final int MAX_ARRAY_SIZE = 20000000;
        int recursionLimit = 2;
        final int MAX_RECURSION_LIMIT = 300;
        PrintWriter BenchmarkResults;
        Integer randomArray [] = generateRandomArray(MAX_ARRAY_SIZE);


        try
        {
            BenchmarkResults = new PrintWriter("resources/BenchmarkResults.csv");
            String headerRow = "0,";
            for (int i = 0; i < arraySizes.length; i++)
                headerRow = headerRow + arraySizes[i] + ",";
            BenchmarkResults.println(headerRow);
            while(recursionLimit < MAX_RECURSION_LIMIT+2)
            {
                String runtimeRow = new String();
                FHsort.setRecursionLimit(recursionLimit);
                BenchmarkResults.print(recursionLimit + ",");
                for (int k = 0; k < arraySizes.length; k++)
                {
                    Integer [] arrayOfInts = new Integer[arraySizes[k]];
                    for (int n = 0; n <  arrayOfInts.length; n++)
                        arrayOfInts[n] = randomArray[n];
                    runtimeRow = runtimeRow + qSortRuntime(arrayOfInts) + ",";
                }
                BenchmarkResults.println(runtimeRow);
                recursionLimit +=2;
            }
            BenchmarkResults.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }

    }
}