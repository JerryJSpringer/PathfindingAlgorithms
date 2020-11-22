package com.jerryjspringer.pathfindingalgorithms;

import com.jerryjspringer.pathfindingalgorithms.algorithms.AStar;
import com.jerryjspringer.pathfindingalgorithms.algorithms.DynamicSWSFFP;
import com.jerryjspringer.pathfindingalgorithms.algorithms.BreadthFirstSearch;
import com.jerryjspringer.pathfindingalgorithms.algorithms.PathFinder;
import com.jerryjspringer.pathfindingalgorithms.model.Maze;
import com.jerryjspringer.pathfindingalgorithms.model.Results;

public class Runner {

    private static final int SIZE = 500;
    private static final int SEED = 6505539;
    private static final int TRIALS = 100;

    public static void main(String[] args) {
        System.out.print(getResults());
    }

    private static String getResults() {
        String result = "";
        result += String.format("|%20s| %15s| %15s| %15s| %15s| %15s| %15s| %15s| %15s|\n",
                "Algorithm", "Init Average", "Average Run", "Fastest Run",
                "Slowest Run", "Total", "Size", "Attempts", "Successes");
        result += "|--------------------|----------------|----------------|----------------|------";
        result += "----------|----------------|----------------|----------------|----------------|\n";
        result += formatResults("A*", runTrials(new AStar()));
        result += formatResults("Breadth First Search", runTrials(new BreadthFirstSearch()));
        result += formatResults("Dynamic SWSF-FP", runTrials(new DynamicSWSFFP()));
        return result;
    }

    private static String formatResults(String name, Results results) {
        return String.format("|%20s| %15d| %15d| %15d| %15d| %15d| %15d| %15d| %15d|\n",
                name,
                results.getInitialization() / TRIALS,
                results.getRun() / TRIALS,
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

            long time = System.currentTimeMillis();
            pathFinder.init(map, 0, 0, SIZE - 1, SIZE - 1);
            time = System.currentTimeMillis() - time;

            results.addInitialization(time);

            time = System.currentTimeMillis();
            map = pathFinder.findPath(0, 0, SIZE - 1, SIZE - 1);
            time = System.currentTimeMillis() - time;

            if (time > results.getSlowest())
                results.setSlowest(time);

            if (map != null) {
                results.addSuccess();

                if (time < results.getFastest())
                    results.setFastest(time);
            }

            results.addRun(time);
        }

        return results;
    }
}
