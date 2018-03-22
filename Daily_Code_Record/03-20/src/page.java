import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class page {

    public static void main(String[] args) {
        Set<Integer>[] adj = new Set[5];
        for(int i = 0; i < 5; i++) adj[i] = new HashSet<>(i);
        adj[0].add(4);
        adj[0].add(5);
        adj[0].remove(4);
        int v2 = adj[0].iterator().next();
        System.out.println(adj[0].size());
        List<Set<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < 3; i++ ) ans.add(new HashSet<>());
        System.out.println(ans.get(1).size());
        ans.get(1).add(1);
        int num = ans.get(1).iterator().next();
        System.out.println(num);

        }
    }

