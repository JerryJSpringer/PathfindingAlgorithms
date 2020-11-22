package com.jerryjspringer.pathfindingalgorithms.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class DynamicSWSFFP implements PathFinder {

    private final int NONDIAGONAL_COST = 10;
    private final int DIAGONAL_COST = 14;

    private int[][] mMap;
    private Node[][] mNodes;
    private boolean[][] mVisited;
    private Queue<Node> mQueue;

    public DynamicSWSFFP() {
    }

    @Override
    public void init(int[][] map, int x1, int y1, int x2, int y2) {
        mMap = map;
        mNodes = new Node[map.length][map[0].length];
        mVisited = new boolean[map.length][map[0].length];
        mQueue = new LinkedList<>();
        makeMap(x1, y1, x2, y2);
    }

    private void makeMap(int x1, int y1, int x2, int y2) {
        mVisited[x2][y2] = true;
        mNodes[x2][y2] = new Node(x2, y2);
        mQueue.add(mNodes[x2][y2]);

        while (!mQueue.isEmpty()) {
            Node current = mQueue.remove();

            for (int dx = -1; dx < 2; dx++) {
                for (int dy = -1; dy < 2; dy++) {
                    if (dx == 0 && dy == 0)
                        continue;

                    int xn = current.x + dx;
                    int yn = current.y + dy;

                    // Checks if the location can be moved to
                    if (isValid(current.x, current.y, xn, yn)) {
                        // Get the cost to move to the new node
                        int cost = current.cost + getCost(dx, dy);

                        // Make a new node
                        Node neighbor = mNodes[xn][yn];
                        if (neighbor == null) {
                            neighbor = new Node(xn, yn);
                            mNodes[xn][yn] = neighbor;
                        }

                        if (cost < neighbor.cost) {
                            mQueue.remove(neighbor);
                            mVisited[xn][yn] = false;
                        }

                        if (!mVisited[xn][yn]) {
                            mVisited[xn][yn] = true;
                            neighbor.cost = cost;
                            neighbor.parent = current;
                            mQueue.add(neighbor);
                        }
                    }
                }
            }
        }
    }

    @Override
    public int[][] findPath(int x1, int y1, int x2, int y2) {
        if (mNodes[x1][y1] == null)
            return null;
        else
            return traceMap(mNodes[x1][y1]);
    }

    private int[][] traceMap(Node end) {
        Node node = end;
        int x = end.x;
        int y = end.y;

        while(node.parent != null) {
            mMap[x][y] = 1;
            node = node.parent;
            x = node.x;
            y = node.y;
        }

        mMap[x][y] = 1;

        return mMap;
    }

    private int getCost(int dx, int dy) {
        int value = Math.abs(dx) + Math.abs(dy);

        if (value == 1)
            return NONDIAGONAL_COST;
        if (value == 2)
            return DIAGONAL_COST;
        return 0;
    }

    private boolean isValid(int x, int y, int xn, int yn) {
        // Check if the new indices are in map
        if (xn < 0 || xn >= mMap.length || yn < 0 || yn >= mMap.length)
            return false;

        // Check if the diagonal movement would scrape corner
        if (mMap[xn][y] == -1 || mMap[x][yn] == -1)
            return false;

        // Check if the new coordinates are valid
        return mMap[xn][yn] == 0;
    }

    private class Node {
        private int x;
        private int y;
        private int cost;
        private Node parent;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
