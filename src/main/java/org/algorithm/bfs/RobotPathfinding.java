package org.algorithm.bfs;

import java.util.*;

class RobotPathfinding {
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static List<int[]> bfs(int[][] grid, int[] start, int[] goal) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Map<String, int[]> parent = new HashMap<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        parent.put(Arrays.toString(start), null);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int i = current[0], j = current[1];

            // If it reaches the goal, reconstruct the path
            if (i == goal[0] && j == goal[1]) {
                return reconstructPath(parent, goal);
            }

            for (int[] dir : DIRECTIONS) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 0 && !visited[x][y])  {
                    queue.add(new int[]{x, y});
                    visited[x][y] = true;
                    parent.put(Arrays.toString(new int[]{x, y}), current);
                }
            }
        }
        return new ArrayList<>(); // No path found
    }

    // Reconstruct the path from goal to start using the parent map
    private static List<int[]> reconstructPath(Map<String, int[]> parent, int[] goal) {
        List<int[]> path = new ArrayList<>();
        int[] current = goal;

        while (current != null) {
            path.add(current);
            current = parent.get(Arrays.toString(current));
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 1},
                {0, 0, 1},
                {1, 0, 0}
        };

        int[] start = {0, 0};
        int[] goal = {2, 2};

        List<int[]> path = bfs(grid, start, goal);

        if (path.isEmpty()) {
            System.out.println("No path found!");
        } else {
            System.out.println("Shortest Path:");
            for (int[] step : path) {
                System.out.println(Arrays.toString(step));
            }
        }
    }
}

