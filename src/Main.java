import java.util.Random;

public class Main {
    private static final int arraySize = (int) Math.pow(2, 30);   // Μέγεθος array σε δυνάμεις του 2
    private static final Random random = new Random();  // Αρχικοποίηση του random
    private static HammingCalculator hammingThreads[]; // Array των threads
    private static final int maxThreads = 3;    // Μέγιστος αριθμός threads, σε δυνάμεις του 2

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

    /**
     * Εκκίνηση όλων των threads, περνώντας τις αντίστοιχες παραμέτρους δεδομένων σε κάθε ένα
     *
     * @param a int[]
     * @param b int[]
     * @param batchSize int
     */
    private static void startThreads(int[] a, int[] b, int batchSize) {
        for(int i=0; i<hammingThreads.length; i++) {
            hammingThreads[i] = new HammingCalculator(a, b, i * batchSize, batchSize);
            hammingThreads[i].start();
        }
    }

    /**
     * Αναμονή από το thread της main, για να τερματίσουν όλα τα threads
     */
    private static void waitThreads() {
        for (HammingCalculator calculator: hammingThreads) {
            try {
                calculator.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Υπολογισμός της τελικής απόστασης Hamming, προσθέτοντας την απόσταση Hamming
     * που υπολόγισε το κάθε thread
     *
     * @return int
     */
    private static int calcTotalHamming() {
        int totalHamming = 0;

        for (HammingCalculator calculator: hammingThreads) {
            totalHamming += calculator.getHamming();

        }

        return totalHamming;
    }

    public static void main(String[] args) {
        System.out.println("Δημιουργία arrays...\n");

        // Δημιουργία των arrays
        int[] a = generateArray();
        int[] b = generateArray();

        // Δοκιμή επεξεργασίας με διαφορετικό πλήθος threads
        for (int i=0; i<=maxThreads; i++) {
            int threadsNumber = (int) Math.pow(2, i);  // Πλήθος threads σε δυνάμεις του 2

            // Αρχικοποίηση του array των threads με την κλάση HammingCalculator
            hammingThreads = new HammingCalculator[threadsNumber];

            System.out.println("\n-----------------------------------------------------------------");
            System.out.println("Επεξεργασία " + arraySize + " ψηφίων, με "
                    + threadsNumber
                    + ((threadsNumber>1) ? " threads" : " thread")
                    + "\n");

            // Αρχικοποίηση του χρόνου που αρχίζει η επεξεργασία
            long start = System.currentTimeMillis();

            // Εκκίνηση και αναμονή των threads
            startThreads(a, b, (arraySize / threadsNumber));

            waitThreads();

            // Τερματισμός του χρόνου επεξεργασίας
            long end = System.currentTimeMillis();

            // Τελικές εκτυπώσεις
            System.out.println("\nΤερματισμός όλων των threads");
            System.out.println("\nΑπόσταση Hamming: " + calcTotalHamming());
            System.out.println("\nΧρονική διάρκεια επεξεργασίας: " + (end - start) + "msec");
        }

    }
}