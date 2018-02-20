import java.util.HashMap;

import com.sun.tools.sjavac.pubapi.PubVar;

public ArrayList<DirectdGraphNode> topSort(ArrayList<DirectdGraphNode> graph) {
  ArrayList<DirectdGraphNode> order = new ArrayList<>();

  if(graph == null) {
    return null;
  }

  // 1. count indegree
  Map<DirectdGraphNode, Integer> indegree = getIndegree(graph);

  // 2. Top Sorting - Put all start Nodes into startNodes List
  ArrayList<DirectdGraphNode> startNodes = getStartNodes(indegree, graph);
  
  // 3. BFS -> get order
  order = bfs(indegree, startNodes);

  if(order.size() == graph.size()) {
    return order;
  }

  return null;
}

// 計算每個點有多少點造訪
public Map<DirectdGraphNode, Integer> indegree(ArrayList<DirectdGraphNode> graph) {
  Map<DirectdGraphNode, Integer> indegree = new HashMap<>();
  for(DirectdGraphNode node : graph) {
    for(DirectdGraphNode neighbor : node.neighbors) {
          indegree.put(neighbor, indegree.getOrDefault(neighbor,0) + 1);
      }
    }
    return indegree;
  }


// 取得indegree == 0 的點
public ArrayList<DirectdGraphNode> getStartNodes(Map<DirectdGraphNode,Integer> indegree,ArrayList<DirectdGraphNode> graph ) {
    ArrayList<DirectdGraphNode> nodes = new ArrayList<>();
    for(DirectdGraphNode node : graph) {
      if(indegree.get(node) == 0){
        nodes.add(node);
      }
    }
    return nodes;
}

// BFS，把indegree == 0的點丟入Queue中，造訪鄰居
public ArrayList<DirectdGraphNode> bfs(Map<DirectdGraphNode,Integer> indegree, ArrayList<DirectdGraphNode> startNodes){
  ArrayList<DirectdGraphNode> order = new ArrayList<>();
  Deque<DirectdGraphNode> queue = new ArrayDeque<>();
  for(DirectdGraphNode node : nodes) {
    order.add(node);
    queue.offerLast(node);
  }

  while(!queue.isEmpty()) {
    DirectdGraphNode cur = queue.pollFirst();
    for(DirectdGraphNode neighbor : cur.neighbors) {
      indegree.put(neighbor, indegree.get(neighbor) - 1);
      if(indegree == 0) {
        order.add(neighbor);
        queue.offerLast(neighbor);
      }
    }
  }
  return order;
}