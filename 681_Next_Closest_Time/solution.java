class Solution {
  public String nextClosestTime(String time) {
      char[] numbers = new char[4];
      
      for(int i = 0; i < 2; i++) {
          numbers[i] = time.charAt(i);
      }
      for(int i = 2; i < 4; i++) {
          numbers[i] = time.charAt(i + 1);
      }
      
      /* [2, 3, 5, 9] */
      /* Get All possible times and add them into a Set*/
      Set<String> times = new HashSet<>();
      for(int i = 0; i < numbers.length; i++) {
          for(int j = 0; j < numbers.length; j++) {
              for(int k = 0; k < numbers.length; k++) {
                  for(int l = 0; l < numbers.length; l++) {
                      
                      if(numbers[i] - '2' > 0 || numbers[k] - '6' > 0) continue;
                      if(numbers[i] == '2' && numbers[j] - '4' > 0) continue;
                      
                      String currentTime = "" + numbers[i] + "" + numbers[j] + ":"  + numbers[k] + ""+ numbers[l];
                      // System.out.println(currentTime);
                      if(isValidTime(currentTime)) {
                          times.add(currentTime);
                      }
                  }
              }
          }
      }
      
      /* Sort the Array of Time and find the exist one */
      /* Check if the existed one is the last one, return first in the array *
      /* Or just return the next time */
      
      List<String> list = new ArrayList<>(times);
      
      Collections.sort(list);
      
      int index = list.indexOf(time);
      return index == list.size() - 1 ? list.get(0) : list.get(index + 1);
      
      
  }
  
  public boolean isValidTime(String time) {
      int hour = Integer.parseInt(time.substring(0, 2));
      int min = Integer.parseInt(time.substring(3, 5));
      
      return hour >= 0 && hour <= 24 && min >= 0 && min < 60;
      
  }
}