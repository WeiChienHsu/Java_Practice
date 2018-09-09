/* Use the lenth of list as the index we query for stored String in the List */

public class Codec {
    
  List<String> list;
  
  public Codec() {
      list = new ArrayList<>();
  }
  
  // Encodes a URL to a shortened URL.
  public String encode(String longUrl) {
      list.add(longUrl);
      int index = list.size();
      return String.valueOf(index - 1);
  }

  // Decodes a shortened URL to its original URL.
  public String decode(String shortUrl) {
      int index = Integer.parseInt(shortUrl);
      return index < list.size() ? list.get(index) : "";
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));