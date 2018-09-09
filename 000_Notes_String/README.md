# String

- == tests for reference equality (whether they are the same object).

- .equals() tests for value equality (whether they are logically "equal").

- Objects.equals() checks for null before calling .equals() so you don't have to (available as of JDK7, also available in Guava).

- String.contentEquals() compares the content of the String with the content of any CharSequence (available since Java 1.5).


```java
// These two have the same value
new String("test").equals("test") // --> true 

// ... but they are not the same object
new String("test") == "test" // --> false 

// ... neither are these
new String("test") == new String("test") // --> false 

// ... but these are because literals are interned by 
// the compiler and thus refer to the same object
"test" == "test" // --> true 

// ... string literals are concatenated by the compiler
// and the results are interned.
"test" == "te" + "st" // --> true

// ... but you should really just call Objects.equals()
Objects.equals("test", new String("test")) // --> true
Objects.equals(null, "test") // --> false
Objects.equals(null, null) // --> true
```

## CompareTo

The java string compareTo() method compares the given string with current string lexicographically. It returns positive number, negative number or 0.

```java
public class CompareToExample{  
  public static void main(String args[]){  
  String s1="hello";  
  String s2="hello";  
  String s3="meklo";  
  String s4="hemlo";  
  String s5="flag";  
  System.out.println(s1.compareTo(s2));//0 because both are equal  
  System.out.println(s1.compareTo(s3));//-5 because "h" is 5 times lower than "m"  
  System.out.println(s1.compareTo(s4));//-1 because "l" is 1 times lower than "m"  
  System.out.println(s1.compareTo(s5));//2 because "h" is 2 times greater than "f"  
}}  
```

## ReplaceAll

```java
String[] strs = paragraph.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
```


## StringBuilder
- append(String or char)
- insert(int index)
- reverse()
- toString()
- new String(sb.toString());