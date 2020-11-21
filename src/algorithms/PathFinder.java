package algorithms;

public interface PathFinder {
    void init(int[][] map);
    int[][] findPath(int x1, int y1, int x2, int y2);
}
