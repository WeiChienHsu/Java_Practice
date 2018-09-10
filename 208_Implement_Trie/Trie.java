/* Trie: Stored the Characters of several Strings in to the tree.
   TrieNode: has a children array to record child and a boolean value of isWord marked the last character in the String.
*/

class Trie {
    
  TrieNode root;

  /** Initialize your data structure here. */
  public Trie() {
      root = new TrieNode();
  }
  
  /** Inserts a word into the trie. */
  public void insert(String word) {
      TrieNode prev = root;
      for(char c : word.toCharArray()) {
          /* Check if the current character is existed */
          if(prev.children[c - 'a'] == null) {
              /* Create a new node in the tree */
              prev.children[c - 'a'] = new TrieNode();
          }
          /* Move forward to the next character */
          prev = prev.children[c - 'a'];
      }
      /* Mark the last character as Word */
      prev.isWord = true;
  }
  
  /** Returns if the word is in the trie. */
  public boolean search(String word) {
      TrieNode current = root;
      for(char c : word.toCharArray()) {
          if(current.children[c - 'a'] == null) {
              return false;
          }
          current = current.children[c - 'a'];
      }
      return current.isWord;
  }
  
  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
      TrieNode current = root;
      for(char c : prefix.toCharArray()) {
          if(current.children[c - 'a'] == null) {
              return false;
          }
          current = current.children[c - 'a'];
      }
      return true;
  }
}

class TrieNode {
  TrieNode[] children;
  boolean isWord;
  public TrieNode() {
      children = new TrieNode[26];
      isWord = false;
  }
}

/**
* Your Trie object will be instantiated and called as such:
* Trie obj = new Trie();
* obj.insert(word);
* boolean param_2 = obj.search(word);
* boolean param_3 = obj.startsWith(prefix);
*/