class LogSystem {
    
  List<String[]> timestamps; /* ["id", "timestamp"], ["id", "timestamp"] */
  final List<String> units; /* For gra to get the index in indeices array */
  final int[] indices;

  public LogSystem() {
      timestamps = new ArrayList<>();
      units = Arrays.asList("Year", "Month", "Day", "Hour", "Minute", "Second");
      indices = new int[]{4, 7, 10, 13, 16, 19};
  }
  
  public void put(int id, String timestamp) {
      timestamps.add(new String[]{Integer.toString(id), timestamp});
  }
  
  public List<Integer> retrieve(String s, String e, String gra) {
      List<Integer> result = new ArrayList<>();
      int index = indices[units.indexOf(gra)]; /* get the substring end point */
      for(String[] timestamp : timestamps) {
          /* Check if the timestamp is between the start and end */
          /* Only need to compare the substring of the index */
          if(timestamp[1].substring(0, index).compareTo(s.substring(0, index)) >= 0 &&
             timestamp[1].substring(0, index).compareTo(e.substring(0, index)) <= 0)
              result.add(Integer.parseInt(timestamp[0])); /* Convert String to integer */
      }
      return result;
  }
}

/**
* Your LogSystem object will be instantiated and called as such:
* LogSystem obj = new LogSystem();
* obj.put(id,timestamp);
* List<Integer> param_2 = obj.retrieve(s,e,gra);
*/