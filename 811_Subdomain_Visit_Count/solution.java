class Solution {
  public List<String> subdomainVisits(String[] cpdomains) {
      Map<String, Integer> map = new HashMap<>();
      List<String> result = new ArrayList<>();
      
      for(int i = 0; i < cpdomains.length; i++) {
          String currentDomain = cpdomains[i];
          int spaceIndex = currentDomain.indexOf(' '); /* Find the first break point */
          int count = Integer.valueOf(currentDomain.substring(0, spaceIndex));
          /* Remove the number in the current Domain */
          currentDomain = currentDomain.substring(spaceIndex + 1);
          
          /* Split the domain name by the '.' */
          for(int j = 0; j < currentDomain.length(); j++) {
              if(currentDomain.charAt(j) == '.') {
                  String subDomain = currentDomain.substring(j + 1);
                  map.put(subDomain, map.getOrDefault(subDomain, 0) + count);
              }
          }
          
          map.put(currentDomain, map.getOrDefault(currentDomain, 0) + count);
      }
      
      for(String s : map.keySet()) {
          result.add(map.get(s) + " " + s);
      }
      return result;
      
  }
}