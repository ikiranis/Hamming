import java.util.Random;

public class Main {
    private static final int arraySize = 16777216;   // Μέγεθος array (2^24)
    private static final int threadsNumber = 16;
    private static final Random random = new Random();

    /**
     * Δημιουργία array με τυχαίες τιμές 0,1
     *
     * @return int[]
     */
    private static int[] generateArray() {
        int[] array = new int[arraySize];

        for(int i=0; i<arraySize; i++) {
            array[i] = random.nextInt(2);
        }

        return array;
    }

    public static void main(String[] args) {
        int[] A = generateArray();
        int[] B = generateArray();
        int totalHamming = 0;

        HammingCalculator hammingThreads[] = new HammingCalculator[threadsNumber];

        int batchSize = arraySize / threadsNumber;
        int start = 0;
        int end = batchSize - 1;

        System.out.println("Calculating...");
        // Mark the time that the processing started.
        long startTime = System.currentTimeMillis();

        for(int i=0; i<hammingThreads.length; i++) {
            hammingThreads[i] = new HammingCalculator(A, B, start, end);
            hammingThreads[i].start();

            start = end + 1;
            end += batchSize -1;
        }


        for(int i=0; i<hammingThreads.length; i++) {
            try {
                // Wait for the threads to finish.
                hammingThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mark the time that the processing ended.
        long endTime = System.currentTimeMillis();



        for(int i=0; i<hammingThreads.length; i++) {
            System.out.println("Hamming from batch " + i + ": " + hammingThreads[i].getHamming());
            totalHamming += hammingThreads[i].getHamming();
        }

        System.out.println("\nTotal hamming: " + totalHamming);
        System.out.println("\nDuration: " + (endTime - startTime) + "msec");

    }
}