class Results {
    private long fastest;
    private long slowest;
    private long total;
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

    void addTotal(long time) {
        total += time;
    }

    long getTotal() {
        return total;
    }

    void addSuccess() {
        success++;
    }

    int getSuccess() {
        return success;
    }
}
