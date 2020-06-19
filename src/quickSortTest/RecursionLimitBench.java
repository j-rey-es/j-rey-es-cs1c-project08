package quickSortTest;


public class RecursionLimitBench
{
    public static double compareSortAlgos(int testSize)
    {
        int k, randomInt;
        long startTime, estimatedTime1, estimatedTime2;

        Integer[] arrayOfInts1 = new Integer[testSize];
        Integer[] arrayOfInts2 = new Integer[testSize];

        // build two arrays for comparing sorts
        for (k = 0; k < testSize; k++)
        {
            randomInt = (int) (Math.random() * testSize);
            arrayOfInts1[k] = randomInt;
            arrayOfInts2[k] = randomInt;
        }
        startTime = System.nanoTime();  // ------------------ start 

        FHsort.heapSort(arrayOfInts1);

        estimatedTime1 = System.nanoTime() - startTime;    // ---------------------- stop
        System.out.println("Heap sort Elapsed Time: "
                + " size: " + testSize + ", "
                + estimatedTime1
                + " = " + estimatedTime1 + "ns");

        startTime = System.nanoTime();  // ------------------ start 

        FHsort.quickSort(arrayOfInts2);


        estimatedTime2 = System.nanoTime() - startTime;    // ---------------------- stop
        System.out.println("Quick sort Elapsed Time: "
                + " size: " + testSize + ", "
                + estimatedTime2
                + " = " + estimatedTime2 + "ns");

        // Note: un-comment  to verify sort
        //displayArray(arrayOfInts1, "Heap");
        //displayArray(arrayOfInts2, "Quick");

        return (double)estimatedTime1 / (double)estimatedTime2;
    }


    public static void displayArray(Integer [] theArray, String message)
    {
        for (int k = 0; k < theArray.length; k+= theArray.length/10)
        {
            System.out.println( message + " #" + k + ": " + theArray[k] + "");
        }
    }


    // -------  main --------------
    public static void main(String[] args) throws Exception
    {

        int [] ARRAY_SIZES;
        ARRAY_SIZES = new int[20];
        int size = 500000;

        for (int i = 0; i<ARRAY_SIZES.length;  i++)
        {
            ARRAY_SIZES[i] = size;
            size = size + 500000;
        }
        double ratio;

        for (int test = 0; test < ARRAY_SIZES.length; test++)
        {
            int currentSize = ARRAY_SIZES[test];
            ratio = compareSortAlgos(currentSize);
            System.out.printf("For data size %d ratio = %.2f \n\n", currentSize, ratio);
        }
    }
}