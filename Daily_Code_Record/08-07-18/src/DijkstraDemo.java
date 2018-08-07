import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

class toCity {
    String  name;
    int     dist;
    public toCity(String name, int dist) {
        this.name = name;
        this.dist = dist;
    }
}

public class DijkstraDemo {

    public static void main(String[] args) {
        HashMap<String, HashMap<String, Integer>> source = initSource();
        showAllDistances(source, "Pensacola");
    }

    public static void showAllDistances(HashMap<String, HashMap<String, Integer>> source, String startCity) {

        /* Set a reachable HashMap to record the shortest distance with City */
        LinkedHashMap<String, Integer> reachable = new LinkedHashMap<>();

        /* Set a Priority Queue to get the currently shortest path */
        /* Write a Comparator for a min Heap return values base on dist in class*/
        PriorityQueue<toCity> pq = new PriorityQueue<>(new Comparator<toCity>() {
            @Override
            public int compare(toCity o1, toCity o2) {
                return o1.dist - o2.dist;
            }
        });

        /* Add the Start City into Priority Queue */
        pq.add(new toCity(startCity, 0));

        while(!pq.isEmpty()) {
            /* Get the shortest city form the top of PQ */
            toCity currentCity = pq.poll();

            /* If current city is in the reachable, discard */
            if(reachable.containsKey(currentCity.name)) {
                continue;
            } else {
                /* add v with given cost to reachable dictionary */
                reachable.put(currentCity.name, currentCity.dist);
            }

            System.out.println(" ======= ");
            System.out.println("Current City: " + currentCity.name + "(" + reachable.get(currentCity.name) + ")");

            /* Get the HashMap which has recorded all neighbors of current City */
            HashMap<String, Integer> neighbors = source.get(currentCity.name);

            for(String neighbor : neighbors.keySet()) {
                /* Check if the neighbor of current city has been put into reachable map */
                if(!reachable.containsKey(neighbor)) {
                    /* Updated the new Distance by adding up: */
                        /* Current Distance : currentCity.dist */
                        /* Distance of current city to its neighbor: neighbors.get(neighbor) */
                    pq.add(new toCity(neighbor, currentCity.dist + neighbors.get(neighbor)));
                }
            }
            showPQ(pq);
        }

        showReachableResults(reachable, startCity);
    }

    public static void showReachableResults(LinkedHashMap<String, Integer> reachable, String startCity) {
        System.out.println("The shortest Distance from: " + startCity );
        for(String city : reachable.keySet()) {
            System.out.println("City: " + city + " Distance: " + reachable.get(city));
        }
    }

    public static void showPQ(PriorityQueue<toCity> pq) {
        PriorityQueue<toCity> copiedPQ = new PriorityQueue<>(pq);
        System.out.println(" ------ ");
        System.out.println("Elements in the Priority Queue: ");
        if(copiedPQ.isEmpty()) System.out.println("None");
        while(!copiedPQ.isEmpty()) {
            toCity currentCity = copiedPQ.poll();
            System.out.println("  " + currentCity.name + ". Distance: " + currentCity.dist);
        }
    }

    public static HashMap<String, HashMap<String, Integer>> initSource() {
        HashMap<String, HashMap<String, Integer>> source = new HashMap<String, HashMap<String, Integer>>();
        source.put("Pendleton", new HashMap<>());
        source.get("Pendleton").put("Pueblo", 8);
        source.get("Pendleton").put("Phoenix", 4);

        source.put("Pensacola", new HashMap<>());
        source.get("Pensacola").put("Phoenix", 5);

        source.put("Peoria", new HashMap<>());
        source.get("Peoria").put("Pueblo", 3);
        source.get("Peoria").put("Pittsburgh", 5);

        source.put("Phoenix", new HashMap<>());
        source.get("Phoenix").put("Pueblo", 3);
        source.get("Phoenix").put("Peoria", 4);
        source.get("Phoenix").put("Pittsburgh", 10);

        source.put("Pierre", new HashMap<>());
        source.get("Pierre").put("Pendleton", 2);

        source.put("Pittsburgh", new HashMap<>());
        source.get("Pittsburgh").put("Pensacola", 4);

        source.put("Princeton", new HashMap<>());
        source.get("Princeton").put("Pittsburgh", 2);

        source.put("Pueblo", new HashMap<>());
        source.get("Pueblo").put("Pierre", 3);

        return source;
    }


}


