class Solution {
  public int calPoints(String[] ops) {
      List<Integer> list = new ArrayList<>();
      int sum = 0;
      int num = 0;
      for(String s : ops) {
          
          switch(s){
              case "C":
                  sum -= list.get(list.size() - 1);
                  list.remove(list.size() - 1);
                  break;
                  
              case "D":
                  num = list.get(list.size() - 1) * 2;
                  sum += (num);
                  list.add(num);
                  break;
                  
              case "+":
                  num = list.get(list.size() - 1) + list.get(list.size() - 2);
                  sum += num;
                  list.add(num);
                  break;
                  
              default:
                  num = Integer.parseInt(s);
                  sum += num;
                  list.add(num);
          }
      }
      return sum;
  }
}