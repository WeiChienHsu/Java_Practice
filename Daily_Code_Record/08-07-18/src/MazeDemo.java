/* This is a demo for both DFS and BFS solving MAZE problem */

import java.util.*;

public class MazeDemo {
    public static void main(String[] args) {
        /* Init the maze by using Edges List */
        HashMap<Integer,  List<Integer>> edgesList = new HashMap<>();
        edgesList = MazeDemo.initList();
        System.out.println("DFS Demo: ");
        showThePathByDFS(edgesList);
        System.out.println("==========================");
        System.out.println("BFS Demo: ");
        showThePathByBFS(edgesList);
    }

    public static void showThePathByBFS(HashMap<Integer, List<Integer>> edgesList) {
        /* Use a Queue as a container */
        /* Use an Array for recording reachable nodes */
        /* Need to show the nodes in the Queue currently and nodes in the reachable array */
        /* Show the node we're dealing with as well */
        Deque<Integer> queue = new ArrayDeque<>();
        List<Integer> reachable = new ArrayList<>();
        int count = 0;

        queue.offerFirst(1);

        while(!queue.isEmpty()) {
            int currentNode = queue.pollLast();
            count++;
            System.out.print("Steps " + count);
            System.out.println(". Current Node: " + currentNode);
            reachable.add(currentNode);

            if(currentNode == 25) {
                System.out.println("Meet the end point 25! ");
                System.out.println("Print the searching path: ");
                showReachableNodeLists(reachable);
                break;
            }

            /* Add all neighbors of current node into the Queue */
            List<Integer> neighbors = edgesList.get(currentNode);
            for(int neighbor : neighbors) {
                if(!reachable.contains(neighbor) && !queue.contains(neighbor)) {
                    queue.offerFirst(neighbor);
                }
            }
            System.out.println("Elements in the Queue: ");
            showElementInQueue(queue);
            System.out.print("Reachable Node Lists: ");
            showReachableNodeLists(reachable);
        }
    }




    public static void showThePathByDFS(HashMap<Integer, List<Integer>> edgesList) {
        /* Use a Stack as a container */
        /* Use an Array for recording reachable nodes */
        /* Need to show the nodes in the Stack currently and nodes in the reachable array */
        /* Also show the node we just pop out of the Stack */

        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> reachable = new ArrayList<>();
        int count = 0;

        /* Add the first Node into the stack */
        stack.offerFirst(1);
        count ++;

        while(!stack.isEmpty()) {
            int currentNode = stack.pollFirst();
            count ++;
            System.out.print("Steps " + count);
            System.out.println(". Current Node: " + currentNode);
            reachable.add(currentNode);

            if(currentNode == 25) {
                System.out.println("Meet the end point 25! ");
                System.out.println("Print the searching path: ");
                showReachableNodeLists(reachable);
                break;
            }


            /* Add all neighbors of current node into the Stack */
            List<Integer> neighbors = edgesList.get(currentNode);

            for(int i = 0; i < neighbors.size(); i++) {
                int neighbor = neighbors.get(i);
                /* Check the neighbor is not in the reachable array */
                if(!reachable.contains(neighbor) && !stack.contains(neighbor)) {
                    /* Push it into the Stack */
                    stack.offerFirst(neighbor);
                }
            }
            System.out.println("Elements in the Stack: ");
            showElementsInStack(stack);
            System.out.print("Reachable Node Lists: ");
            showReachableNodeLists(reachable);
        }
    }

    public static void showReachableNodeLists(List<Integer> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println(" ");
        System.out.println("-----------------");
    }

    public static void showElementInQueue(Deque<Integer> queue) {
        Deque<Integer> copiedQueue = new ArrayDeque<>(queue);
        System.out.print(" || Front ");
        while(!copiedQueue.isEmpty()) {
            System.out.print(copiedQueue.pollLast() + " ");
        }
        System.out.println("Back ||");
    }


    public static void showElementsInStack(Deque<Integer> stack) {
        Deque<Integer> copiedStack = new ArrayDeque<>(stack);
        System.out.println(" Top");
        while(!copiedStack.isEmpty()) {
            int top = copiedStack.pop();
            System.out.println("|" + top + (top < 10 ? " |" : "|" ));
        }
        System.out.println("----");

    }

    public static HashMap<Integer, List<Integer>> initList() {
        HashMap<Integer,  List<Integer>> edgesList = new HashMap<>();
        /* Put each node into the map as keys */
        for(int i = 1; i < 26; i++) {
            edgesList.put(i, new ArrayList<Integer>());
        }
        edgesList.get(1).add(2);
        edgesList.get(1).add(6);
        edgesList.get(2).add(1);
        edgesList.get(2).add(3);
        edgesList.get(2).add(7);
        edgesList.get(3).add(2);
        edgesList.get(3).add(4);
        edgesList.get(3).add(8);
        edgesList.get(4).add(3);
        edgesList.get(4).add(5);
        edgesList.get(4).add(9);
        edgesList.get(5).add(4);
        edgesList.get(5).add(10);
        edgesList.get(6).add(1);
        edgesList.get(6).add(11);
        edgesList.get(7).add(2);
        edgesList.get(8).add(3);
        edgesList.get(8).add(13);
        edgesList.get(9).add(4);
        edgesList.get(9).add(14);
        edgesList.get(10).add(5);
        edgesList.get(10).add(15);
        edgesList.get(11).add(6);
        edgesList.get(11).add(12);
        edgesList.get(11).add(16);
        edgesList.get(12).add(11);
        edgesList.get(12).add(13);
        edgesList.get(12).add(17);
        edgesList.get(13).add(8);
        edgesList.get(13).add(12);
        edgesList.get(13).add(18);
        edgesList.get(14).add(9);
        edgesList.get(14).add(15);
        edgesList.get(15).add(10);
        edgesList.get(15).add(14);
        edgesList.get(15).add(20);
        edgesList.get(16).add(11);
        edgesList.get(16).add(21);
        edgesList.get(17).add(12);
        edgesList.get(17).add(22);
        edgesList.get(18).add(13);
        edgesList.get(19).add(20);
        edgesList.get(19).add(24);
        edgesList.get(20).add(15);
        edgesList.get(20).add(19);
        edgesList.get(21).add(16);
        edgesList.get(21).add(22);
        edgesList.get(22).add(17);
        edgesList.get(22).add(21);
        edgesList.get(22).add(23);
        edgesList.get(23).add(22);
        edgesList.get(24).add(19);
        edgesList.get(24).add(25);
        edgesList.get(25).add(24);
        return edgesList;
    }
}
