/**
 * Κλάση υπολογισμού απόστασης Hamming
 */
public class HammingCalculator extends Thread {
    private int[] a;
    private int[] b;
    private int hamming = 0;
    private int start;
    private int batchSize;

    // Constructor
    public HammingCalculator(int[] a, int[] b, int start, int batchSize) {
        this.a = a;
        this.b = b;
        this.start = start;
        this.batchSize = batchSize;
    }

    /**
     * Από εδώ αρχίζει να τρέχει το thread και εκτελεί τον υπολογισμό της απόστασης Hamming
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started. Επεξεργασία " + batchSize + " ψηφίων");
        calcSimilarBits();
        System.out.println(Thread.currentThread().getName() + " finished");
    }

    /**
     * Υπολογισμός της απόστασης Hamming, με σύγκριση των ψηφίων του κάθε array
     */
    private void calcSimilarBits() {
        for (int i=start; i<(start + batchSize); i++) {
            if(a[i] == b[i]) {
                hamming++;
            }
        }
    }

    /**
     * Hamming getter
     *
     * @return
     */
    public int getHamming() {
        return hamming;
    }
}
