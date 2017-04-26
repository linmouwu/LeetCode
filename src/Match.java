import java.util.ArrayList;
import java.util.List;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */
public class Match {
    private class Pair {

        private boolean isPair;
        private Pair left;
        private Pair right;
        private int leftN;
        private int rightN;

        Pair(int leftN, int rightN) {
            this.leftN = leftN;
            this.rightN = rightN;
            this.left = null;
            this.right = null;
            this.isPair = false;
        }

        Pair(Pair left, Pair right) {
            this.left = left;
            this.right = right;
            isPair = true;
            this.leftN = 0;
            this.rightN = 0;
        }

        public int getLeftN() {
            return this.leftN;
        }

        public int getRightN() {
            return this.rightN;
        }

        public Pair getLeft() {
            return this.left;
        }

        public Pair getRight() {
            return this.right;
        }

        public boolean getIsPair() {
            return this.isPair;
        }

        @Override
        public String toString() {

            if (!this.isPair) {
                return "(" + this.getLeftN() + "," + this.getRightN() + ")";
            } else {
                return "(" + this.getLeft().toString() + "," + this.getRight().toString() + ")";
            }

        }

    }

    public String findContestMatch(int n) {

        int count = n / 2;

        List<Pair> last = new ArrayList<>();
        for (int i = 1; i <= n / 2; i++) {
            Pair p = new Pair(i, n - (i - 1));
            last.add(p);
        }

        while (count > 1) {
            List<Pair> current = new ArrayList<>();

            for (int i = 0; i < last.size() / 2; i++) {

                Pair p = new Pair(last.get(i), last.get(last.size() - i - 1));

                current.add(p);
            }

            last = current;
            count /= 2;
        }

        Pair root = last.get(0);

        return root.toString();
    }


    public static void main(String[] args) {

        Match match = new Match();

        System.out.println(match.findContestMatch(8));
    }
}
