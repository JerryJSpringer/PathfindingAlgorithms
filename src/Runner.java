import algorithms.AStar;
import algorithms.ModifiedBFS;
import algorithms.PathFinder;

public class Runner {

    private static final int SIZE = 500;
    private static final int SEED = 126005;
    private static final int TRIALS = 1000;

    public static void main(String[] args) {
        System.out.print(getResults());
    }

    private static String getResults() {
        String result = "";
        result += String.format("%15s %15s %15s %15s %15s %15s %15s %15s\n",
                "Algorithm", "Average", "Fastest", "Slowest", "Total", "Size", "Attempts", "Successes");
        result += formatResults("A*", runTrials(new AStar()));
        result += formatResults("Modified BFS", runTrials(new ModifiedBFS()));
        return result;
    }

    private static String formatResults(String name, Results results) {
        return String.format("%15s %15d %15d %15d %15d %15d %15d %15d\n",
                name,
                results.getTotal() / TRIALS,
                results.getFastest(),
                results.getSlowest(),
                results.getTotal(),
                SIZE,
                TRIALS,
                results.getSuccess());
    }

    private static Results runTrials(PathFinder pathFinder) {
        Results results = new Results();
        Maze maze = new Maze(SIZE);
        int[][] map;

        for (int i = 0; i < TRIALS; i++) {
            maze.makeMap(SEED + i);
            map = maze.getMap();

            pathFinder.init(map);
            long time = System.currentTimeMillis();
            map = pathFinder.findPath(0, 0, SIZE - 1, SIZE - 1);
            time = System.currentTimeMillis() - time;

            if (time > results.getSlowest())
                results.setSlowest(time);

            if (map != null) {
                results.addSuccess();

                if (time < results.getFastest())
                    results.setFastest(time);
            }

            results.addTotal(time);
        }

        return results;
    }
}
