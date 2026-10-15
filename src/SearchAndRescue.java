public class SearchAndRescue {
    int n;
    int m;
    double[][] probabilities;

    public SearchAndRescue(int n, int m) {
        this.n = n;
        this.m = m;
        this.probabilities = new double[n][m];
        for (int i = 0; i < n; i += 1) {
            for (int j = 0; j < m; j += 1) {
                this.probabilities[i][j] = (double) 1 / (this.n * this.m);
            }
        }
    }

    public void setGridProbability(int row, int col, double probability) {
        if (row > n) {
            System.out.printf("Invalid Row: %d\n", row);
            return;
        }
        if (col > m) {
            System.out.printf("Invalid Column: %d\n", col);
            return;
        }
        if (probability < 0.0 || probability > 1.0) {
            System.out.printf("Invalid Probability: %d\n", row);
            return;
        }

        this.probabilities[row - 1][col - 1] = probability;
    }

    public void bestLocation() {
        double best = 0.0;
        int best_row = 0;
        int best_col = 0;
        for (int i = 0; i < n; i += 1) {
            for (int j = 0; j < m; j += 1) {
                double current = this.probabilities[i][j];
                if (current > best) {
                    best = current;
                    best_row = i;
                    best_col = j;
                }
            }
        }
        System.out.printf("Best Location: (%d, %d)\n", best_row + 1, best_col + 1);
    }

    public static void main(String[] args) {
        SearchAndRescue search = new SearchAndRescue(2,4);
        System.out.println("Initial grid");
        System.out.println(search); //calls toString

        System.out.println("Most likely location");
        search.bestLocation();

        System.out.println("Update grid locations");
        search.setGridProbability(1, 5, 0.3); //error, invalid location
        search.setGridProbability(1, 1, 0.3);
        search.setGridProbability(-1,-1,-1.0); //error, invalid location
        search.setGridProbability(1,2, 100.0); //error, invalid probability

        System.out.println("After updates");
        System.out.println(search); //calls toString
        System.out.println("New most likely");
        search.bestLocation();
    }
}