# Top K Frequent Words

```
Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
    
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.

```

## Solution - Comparator is the key

Used an additional Class to record Frequency and Name of the String.
Used a Map to pair the specific String and Word class.
Used a PQ implemented a MAX Heap and sorted by the frequecy (if its the same, sorted by Alph - using compareTo())

```java
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
                  return o1.freq == o2.freq ? o1.name.compareTo(o2.name) : o2.freq - o1.freq;
              }
      });
      
      for(String w : map.keySet()) {
          maxHeap.add(map.get(w));
      }
      
      List<String> results = new ArrayList<>();
      
      while(results.size() < k) {
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
```