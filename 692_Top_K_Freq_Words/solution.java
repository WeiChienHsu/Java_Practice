class Solution {
  public List<String> topKFrequent(String[] words, int k) {
      Map<String, Word> map = new HashMap<>();
      
      for(int i = 0; i < words.length; i++) {
          if(!map.containsKey(words[i])) {
              Word word = new Word(words[i]);
              map.put(words[i], word);
          } else {
              map.get(words[i]).addOne();
          }
      }
      
      
      PriorityQueue<Word> maxHeap = new PriorityQueue<>(map.size(),
          new Comparator<Word>() {
              @Override
              public int compare(Word o1, Word o2) {
                  if(o1.freq == o2.freq) {
                      /* Loop through the shorter one */
                      int len1 = o1.name.length();
                      int len2 = o2.name.length();
                      int len = Math.min(len1, len2);
                      
                      for(int i = 0; i < len; i++) {
                          if(o1.name.charAt(i) == o2.name.charAt(i)) {
                              continue;
                          }
                          /* Return the less alph */
                          return o1.name.charAt(i) - o2.name.charAt(i);
                      }
                      /* Return the shorter one if they got the same characters */
                      return o1.name.length() - o2.name.length();
                  } else {
                      /* Return the less freq */
                      return o2.freq - o1.freq;
                  }
                  /* The same implementaion of my solution */
                  // return o1.freq == o2.freq ? o1.name.compareTo(o2.name) : o2.freq - o1.freq;
              
              }
      });
      
      for(String w : map.keySet()) {
          maxHeap.add(map.get(w));
      }
      
      List<String> results = new ArrayList<>();
      
      for(int i = 0; i < k; i++) {
          results.add(maxHeap.poll().name);
      }
      
      return results;
      
  }
}

class Word{
  String name;
  int freq;
  public Word(String name) {
      this.name = name;
      this.freq = 1;
  }
  
  public void addOne(){
      this.freq += 1;
  }
}