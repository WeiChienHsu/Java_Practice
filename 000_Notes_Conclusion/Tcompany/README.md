# Solution

## Primes In Subtree

```java
private  static List<Integer> primeQ(int n, List<Integer> first, List<Integer> second, List<Integer> values, List<Integer> queries) {
        int[] primCount = new int[n+1];
        int[] parentIdx = new int[n+1];
        int[] childNum = new int[n+1];
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            if(isPrime(values.get(i-1))) primCount[i] = 1;
        }
        for(int i = 0; i < first.size(); i++) {
            int u = first.get(i);
            int v = second.get(i);
            if(!map.containsKey(u)) map.put(u,new ArrayList<Integer>());
            map.get(u).add(v);
            if(!map.containsKey(v)) map.put(v,new ArrayList<Integer>());
            map.get(v).add(u);
        }
        Queue<Integer> leaves = new LinkedList<>();
        parentIdx[1] = 1;
        leaves.offer(1);
        while (!leaves.isEmpty()) {
            int u = leaves.poll();
            for(int v : map.get(u)) {
                if(parentIdx[v] != 0) continue;
                parentIdx[v] = u;
                childNum[u]++;
                leaves.offer(v);
            }
        }
        leaves.clear();
        for(int i = 1; i <= n; i++) {
            if(childNum[i] == 0) leaves.offer(i);
        }
        while (!leaves.isEmpty()) {
            int v = leaves.poll();
            int u = parentIdx[v];
            if(u == v) continue;
            primCount[u] += primCount[v];
            childNum[u]--;
            if(childNum[u] == 0) leaves.offer(u);
        }
        List<Integer> res = new ArrayList<>();
        for(int query : queries) res.add(primCount[query]);
        return  res;
    }

    private static boolean isPrime(int n) {
        if(n == 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
```