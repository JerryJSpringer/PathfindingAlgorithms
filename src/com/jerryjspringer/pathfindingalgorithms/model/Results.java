package com.jerryjspringer.pathfindingalgorithms.model;

public class Results {
    private long fastest;
    private long slowest;
    private long initialization;
    private long run;
    private int success;

    public Results() {
        fastest = Long.MAX_VALUE;
        slowest = Long.MIN_VALUE;
    }

    public void setFastest(long fastest) {
        this.fastest = fastest;
    }

    public long getFastest() {
        return fastest;
    }

    public void setSlowest(long slowest) {
        this.slowest = slowest;
    }

    public long getSlowest() {
        return slowest;
    }

    public void addRun(long time) {
        run += time;
    }

    public long getRun() {
        return run;
    }

    public long getTotal() {
        return initialization + run;
    }

    public void addInitialization(long time) {
        initialization += time;
    }

    public long getInitialization() {
        return initialization;
    }

    public void addSuccess() {
        success++;
    }

    public int getSuccess() {
        return success;
    }
}
