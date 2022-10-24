public class HammingCalculator extends Thread {
    private int[] A;
    private int[] B;
    private int hamming = 0;
    private int start;
    private int end;

    public HammingCalculator(int[] A, int[] B, int start, int end) {
        this.A = A;
        this.B = B;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        calcSimilarBits();
    }

    private void calcSimilarBits() {
        for (int i=start; i<end; i++) {
            if(A[i] == B[i]) {
                hamming++;
            }
        }
    }

    public int getHamming() {
        return hamming;
    }
}
