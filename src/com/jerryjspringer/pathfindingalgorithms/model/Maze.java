package com.jerryjspringer.pathfindingalgorithms.model;

import java.util.Random;

public class Maze {

    private static final float OBJECT_CHANCE = .2f;
    private int[][] mMap;
    private int size;

    public Maze(int size) {
        this.size = size;
    }

    public void makeMap(int seed) {
        mMap = new int[size][size];
        Random random = new Random(seed);

        for (int i = 0 ; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (random.nextFloat() < OBJECT_CHANCE)
                    mMap[i][j] = -1;
            }
        }

        mMap[0][0] = 0;
        mMap[size - 1][size - 1] = 0;
    }

    public int[][] getMap() {
        int [][] newMap = new int[mMap.length][];
        for(int i = 0; i < mMap.length; i++)
            newMap[i] = mMap[i].clone();
        return newMap;
    }

    public static void displayMap(int[][] map) {
        if (map == null) {
            System.out.println("Map is null");
            return;
        }

        for (int[] ints : map) {
            for (int j = 0; j < map.length; j++) {
                System.out.format("%3d", ints[j]);
            }
            System.out.println();
        }
    }
}
