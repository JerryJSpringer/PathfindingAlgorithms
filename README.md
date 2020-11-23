# Pathfinding Algorithms
A collection of pathfinding algorithms and comparisons between them.

# Algorithms
### Implemented
  - A*
    - Fairly efficient with optimal path
  - Breadth First Search
    - Very efficient but not optimal
  - Dynamic SWSF-FP (Strict Weak Superior Function - Fixed Point)
    - Frontloads runtime at initialization
    - Implementation of Djikstra Map
    - Much slower init time for faster run time

### Planned
   - D* (Dynamic A*) / Focused D* / D*-Lite
     - Similar to A*
     - Can generate new solution to new problem instance
     - Very complex (see simpler implementations)
   - Anytime D*
     - Combines D*-Lite with Anytime Repairing A*
     - Gives a path very quickly that can be improved in given time
     - Runs incredibly well under time constraints
   - LPA* (Lifelong Planning A*) / Incremental A*
     - Similar to A* but subsequent runs are improved using previous data
   - GAA* (Generalized Adaptive A*)
     - A* variant that handles moving targets

# Comparisons

|           Algorithm|       Avg. Init|        Avg. Run|         Fastest|         Slowest|           Total|            Size|        Attempts|       Successes|
|--------------------|----------------|----------------|----------------|----------------|----------------|----------------|----------------|----------------|
|                  A*|               0|              25|              19|              65|            2632|             500|             100|              86|
|Breadth First Search|               0|              13|              12|              47|            1403|             500|             100|              86|
|     Dynamic SWSF-FP|             484|               0|               0|               1|           48431|             500|             100|              86|

**_NOTE:_**  All time is in miliseconds
