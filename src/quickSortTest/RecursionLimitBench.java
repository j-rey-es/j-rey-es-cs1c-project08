package quickSortTest;


import java.time.chrono.MinguoChronology;

public class RecursionLimitBench
{
    public static Integer[] generateRandomArray(int size){
        int i;
        Integer RandomArray[] = new Integer [size];
        for (i = 0; i<RandomArray.length; i++)
            RandomArray[i] = (int) (Math.random() * size);
        return RandomArray;

    }

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

        int [] arraySizes = {20000,50000,100000,200000,500000,1000000,5000000,10000000};
        final int MIN_ARRAY_SIZE = 200000;
        final int MAX_ARRAY_SIZE = 10000000;
        final int NUM_OF_SIZE_INTERVALS = 20;
        final int MIN_RECURSION_LIMIT =  2;
        final int MAX_RECURSION_LIMIT = 300;
        //arraySizes = new int[NUM_OF_SIZE_INTERVALS];

        Integer randomArray [] = generateRandomArray(MAX_ARRAY_SIZE);

       /* for (int i = 0; i<arraySizes.length;  i++)
        {
            arraySizes[i] = MIN_ARRAY_SIZE + (((MAX_ARRAY_SIZE - MIN_ARRAY_SIZE) / (NUM_OF_SIZE_INTERVALS)) * i);
        } */

        for (int j = MIN_RECURSION_LIMIT; j < MAX_RECURSION_LIMIT; j = j+2)
        {
            String runtimeRow = new String();
            FHsort.setRecursionLimit(j);
            System.out.print(j + ",");
                for (int k = 0; k < arraySizes.length; k++)
                {
                    Integer [] arrayOfInts = new Integer[arraySizes[k]];
                    for (int n = 0; n <  arrayOfInts.length; n++)
                        arrayOfInts[n] = randomArray[n];
                    runtimeRow = runtimeRow + qSortRuntime(arrayOfInts) + ",";
                }
            System.out.println(runtimeRow);
        }
    }
}