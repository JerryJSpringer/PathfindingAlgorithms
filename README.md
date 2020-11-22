# Pathfinding Algorithms
A collection of pathfinding algorithms and comparisons between them.

# Algorithms
### Implemented
  - A*
    - Fairly efficient with optimal path
  - BFS (Breadth First Search)
    - Very efficient but not optimal
  - Dynamic SWSF-FP (Strict Weak Superior Function - Fixed Point)
    - Frontloads runtime at initialization
    - An implementation of what is sometimes known as a Dijkstra Map

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
