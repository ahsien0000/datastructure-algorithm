package org.algorithm.graph;

import java.util.*;

class Graph {
    public Map<Character, List<Character>> adjList = new HashMap<>();
    public Map<Character, Character> parent = new HashMap<>();

    public void addEdge(char u, char v) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }
}
