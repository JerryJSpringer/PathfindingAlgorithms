class Results {
    private long fastest;
    private long slowest;
    private long initialization;
    private long run;
    private int success;

    Results() {
        fastest = Long.MAX_VALUE;
        slowest = Long.MIN_VALUE;
    }

    void setFastest(long fastest) {
        this.fastest = fastest;
    }

    long getFastest() {
        return fastest;
    }

    void setSlowest(long slowest) {
        this.slowest = slowest;
    }

    long getSlowest() {
        return slowest;
    }

    void addRun(long time) {
        run += time;
    }

    long getRun() {
        return run;
    }

    long getTotal() {
        return initialization + run;
    }

    void addInitialization(long time) {
        initialization += time;
    }

    long getInitialization() {
        return initialization;
    }

    void addSuccess() {
        success++;
    }

    int getSuccess() {
        return success;
    }
}
