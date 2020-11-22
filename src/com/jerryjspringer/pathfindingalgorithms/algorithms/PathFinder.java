package com.jerryjspringer.pathfindingalgorithms.algorithms;

public interface PathFinder {
    void init(int[][] map, int x1, int y1, int x2, int y2);
    int[][] findPath(int x1, int y1, int x2, int y2);
}
