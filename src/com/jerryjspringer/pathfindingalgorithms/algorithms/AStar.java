package com.jerryjspringer.pathfindingalgorithms.algorithms;

import java.util.PriorityQueue;

/**
 * Implements A* path-finding.
 */
public class AStar implements PathFinder {

    private final int NONDIAGONAL_COST = 10;
    private final int DIAGONAL_COST = 14;
    
    private int[][] mMap;
    private Node[][] mNodes;
    private boolean[][] mClosed;
    private boolean[][] mOpen;
    private PriorityQueue<Node> mQueue;

    /**
     * Constructor for A* path finding.
     */
    public AStar() {
    }

    /**
     * Initializes the class to solve an instance of a shortest path problem.
     * @param map The map that could contain a shortest path problem.
     */
    public void init(int[][] map, int x1, int y1, int x2, int y2) {
        mMap = map;
        mNodes = new Node[map.length][map[0].length];
        mClosed = new boolean[map.length][map[0].length];
        mOpen = new boolean[map.length][map[0].length];
        mQueue = new PriorityQueue<>();
    }

    @Override
    public int[][] findPath(int x1, int y1, int x2, int y2) {

        // Add the start node to the open list
        mNodes[x1][y1] = new Node(x1, y1);
        mQueue.add(mNodes[x1][y1]);
        mOpen[x1][y1] = true;

        // The current node
        Node current;

        while (mQueue.size() != 0) {
            // Remove least ranked node from open
            current = mQueue.remove();

            // If we are at our goal then break
            if (current.x == x2 && current.y == y2)
                return traceMap(current);

            // Adds current to closed
            mClosed[current.x][current.y] = true;

            // Search through neighbors of current
            for (int dx = -1; dx < 2; dx++) {
                for (int dy = -1; dy < 2; dy++) {
                    if (dx == 0 && dy == 0)
                        continue;

                    // Gets the location of the neighbor
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

                        // New path is better than the old path
                        if (cost < neighbor.cost) {
                            mQueue.remove(neighbor);
                            mOpen[xn][yn] = false;
                            mClosed[xn][yn] = false;
                        }

                        // Node has not been added to open or closed
                        // Add it as potential node in path
                        if (!mOpen[xn][yn] && !mClosed[xn][yn]) {
                            neighbor.cost = cost;
                            neighbor.heuristic = getHeuristic(xn, yn, x2, y2);
                            mQueue.add(neighbor);
                            mOpen[xn][yn] = true;
                            neighbor.parent = current;
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Recovers the solution to the shortest path problem.
     *
     * @param end The last node in the path.
     * @return An array containing the shortest path marked by 1's.
     */
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


    /**
     * A heuristic estimating the distance from the given point to goal.
     *
     * @param x1 The x coordinate of the given point.
     * @param y1 The y coordinate of the given point.
     * @param x2 The x coordinate of the goal.
     * @param y2 The y coordinate of the goal.
     * @return The estimated cost to move from (x1, y1) to (x2, y2).
     */
    private int getHeuristic(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);

        if (dx > dy)
            return (NONDIAGONAL_COST * (dx - dy) + DIAGONAL_COST * dy);
        else
            return (NONDIAGONAL_COST * (dy - dx) + DIAGONAL_COST * dx);
    }

    /**
     * Returns the cost for movement in a direction.
     *
     * @param dx The x movement.
     * @param dy The y movement.
     * @return The cost to move in the given direction.
     */
    private int getCost(int dx, int dy) {
        int value = Math.abs(dx) + Math.abs(dy);

        if (value == 1)
            return NONDIAGONAL_COST;
        if (value == 2)
            return DIAGONAL_COST;
        return 0;
    }

    /**
     * Checks if the movement between the current and new coordinates
     * are valid. This ensures that indices are within bounds and the
     * movement would not scrape corners.
     *
     * @param x The x coordinate of the current node.
     * @param y The y coordinate of the current node.
     * @param xn The x coordinate of a potential node.
     * @param yn The y coordinate of a potential node.
     * @return If the movement is valid.
     */
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

    /**
     * A node used in the search graph.
     */
    private class Node implements Comparable {
        private int x;
        private int y;
        private int cost;
        private int heuristic;
        private Node parent;

        /**
         * Creates a node with the given coordinates.
         *
         * @param x The x coordinate.
         * @param y The y coordinate.
         */
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object other) {
            Node o = (Node) other;

            // F = G + H
            float f = cost + heuristic;
            float of = o.cost + o.heuristic;

            return Float.compare(f, of);
        }
    }
}
