package algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class ModifiedBFS implements PathFinder{

    private int[][] mMap;


    public ModifiedBFS() {
    }

    public void init(int[][] map) {
        this.mMap = map;
    }

    public int[][] findPath(int x1, int y1, int x2, int y2) {

        // Sets up an array to keep track of visited nodes
        boolean[][] visited = new boolean[mMap.length][mMap[0].length];

        // Mark the starting point
        visited[x1][y1] = true;

        // Creating the BFS queue
        Queue<Node> queue = new LinkedList<>();

        // Distance from the start is 0
        queue.add(new Node(x1, y1));

        // Perform BFS from start
        while (!queue.isEmpty()) {
            Node current = queue.peek();

            // If destination finish
            if (current.x == x2 && current.y == y2)
                return traceMap(current);

            // Dequeue front index
            // Enqueue adjacent index
            queue.remove();
            // Search through neighbors of current
            for (int dx = -1; dx < 2; dx++) {
                for (int dy = -1; dy < 2; dy++) {
                    if (dx == 0 && dy == 0)
                        continue;

                    // Gets the location of the neighbor
                    int xn = current.x + dx;
                    int yn = current.y + dy;

                    if (isValid(current.x, current.y, xn, yn) && !visited[xn][yn]) {
                        // Set that it has been visited
                        visited[xn][yn] = true;

                        // Add to queue
                        Node adjacentNode = new Node(xn, yn);
                        adjacentNode.parent = current;
                        queue.add(adjacentNode);
                    }
                }
            }
        }

        // Path could not be found
        return null;
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
        private Node parent;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
