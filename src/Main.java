import java.util.Random;

public class Main {
    private static final int N = 16777216;   // Μέγεθος array (2^24)
    private static final Random random = new Random();

    /**
     * Δημιουργία array με τυχαίες τιμές 0,1
     *
     * @return int[]
     */
    private static int[] generateArray() {
        int[] array = new int[N];

        for(int i=0; i<N; i++) {
            array[i] = random.nextInt(2);
        }

        return array;
    }

    public static void main(String[] args) {
        int[] A = generateArray();
        int[] B = generateArray();

    }
}