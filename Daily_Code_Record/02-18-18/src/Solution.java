import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
        int n = 5;
        System.out.println(validTree(n, edges));

    }

    private static boolean validTree(int n, int[][] edges) {
        if(n == 0) return false;
        if(edges.length != n - 1) return false;

        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
        Deque<Integer> queue = new ArrayDeque<>();
        HashSet<Integer> hashSet = new HashSet<>();

        int startNum = 0;

        queue.offerLast(startNum);
        hashSet.add(startNum);

        while (!queue.isEmpty()) {
            int node = queue.pollFirst();
            for(Integer neighbor: graph.get(node)) {
                if(hashSet.contains(neighbor)){
                    continue;
                }
                queue.offerLast(neighbor);
                hashSet.add(neighbor);
            }
        }
        return (hashSet.size() == n);

    }

    private static Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int i = 0; i < n ; i++) {
            graph.put(i, new HashSet<>());
        }

        for(int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }
}
