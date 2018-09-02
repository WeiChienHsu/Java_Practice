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