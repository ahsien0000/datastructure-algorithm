package org.algorithm.bfs;

import org.datastructure.graph.Graph;

import java.util.*;

class BreadthFirstSearch {

    public static void bfs(Graph g, char start) {
        Queue<Character> queue = new LinkedList<>();
        Map<Character, Integer> level = new HashMap<>();
        Map<Character, Character> parent = new HashMap<>();

        int i = 0;
        queue.add(start);
        level.put(start, i);

        while (!queue.isEmpty()) {
            char node = queue.poll();
            System.out.print(node + " ");

            for (char neighbor : g.adjList.getOrDefault(node, Collections.emptyList())) {
                if (!level.containsKey(neighbor)) {
                    queue.add(neighbor);
                    level.put(neighbor, i + 1);
                    parent.put(neighbor, node);
                }
            }
            i++;
        }
        g.parent = parent;
    }

    public static void shortest_path(Graph g, char s, char target, List<Character> path) {
        path.addFirst(target);
        if (target == s) return;
        shortest_path(g, s, g.parent.getOrDefault(target, 's'), path);
    }


    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge('r', 's');
        g.addEdge('s', 'r');
        g.addEdge('r', 'v');
        g.addEdge('v', 'r');
        g.addEdge('s', 'w');
        g.addEdge('w', 's');
        g.addEdge('w', 't');
        g.addEdge('t', 'w');
        g.addEdge('w', 'x');
        g.addEdge('x', 'w');
        g.addEdge('t', 'x');
        g.addEdge('x', 't');
        g.addEdge('t', 'u');
        g.addEdge('u', 't');
        g.addEdge('x', 't');
        g.addEdge('x', 'u');
        g.addEdge('u', 'x');
        g.addEdge('x', 'y');
        g.addEdge('y', 'x');
        g.addEdge('u', 'y');
        g.addEdge('y', 'u');

        // Run BFS
        System.out.println("BFS Traversal starting from node s:");
        bfs(g, 's');

        //Shortest path from source to target
        List<Character> path = new ArrayList<>();
        shortest_path(g, 's', 'u', path);
        System.out.println( "\n" + path);
    }
}
