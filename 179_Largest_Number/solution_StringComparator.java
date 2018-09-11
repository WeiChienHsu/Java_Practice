class Solution {
  public String largestNumber(int[] nums) {
      
      List<String> list = new ArrayList<>();
      
      for(int num : nums) {
          list.add(Integer.toString(num));
      }
      
      Collections.sort(list, new Comparator<String>(){
          public int compare(String i1, String i2) {
              String str1 = i1 + i2;
              String str2 = i2 + i1;
              /* Return the order which get a larger value */
              return str2.compareTo(str1);
          }
      });
      
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < list.size(); i++) {
          sb.append(list.get(i));
      }
      /* Deal with the corner case merging of two 0 */
      return sb.toString().charAt(0) == '0' ? "0" : sb.toString() ;
  }
}