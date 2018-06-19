# Group Anagrams


## Java String (注意!)

```java
char c1 = {'a', 'b', 'c'};
char c2 = {'b', 'a', 'c'};
Arrays.sort(c2);

String s1 = String.valueOf(c1);
String s2 = String.valueOf(c2);

System.out.println(s1);
System.out.println(s2);
System.out.println(s1 == s2);
System.out.println(s1.equals(s2));
```

```
abc
abc
false
false
```

## Solution