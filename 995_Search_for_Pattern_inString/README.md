# Searching for Patterns

## Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[]) that prints all occurrences of pat[] in txt[]. You may assume that n > m.

```
Input:  txt[] = "THIS IS A TEST TEXT"
        pat[] = "TEST"
Output: Pattern found at index 10

Input:  txt[] =  "AABAACAADAABAABA"
        pat[] =  "AABA"
Output: Pattern found at index 0
        Pattern found at index 9
        Pattern found at index 12
```

## Solution: Naive
- Search each Charactor in the String 
- Search the length for the pat.length()
- if the word didn't match
- Check if the length of checker is the same as target number
```
 abcababa
i*
j*
 ab
j*

 abcababa
i*
j *
 ab
j *

j = 1 

- Cehck if a == a / b == b 
- if Yes Check if j = length of Target - 1
```

# Rabin-Karp Algorithm
```
The Naive String Matching algorithm slides the pattern one by one. After each slide, it one by one checks characters at the current shift and if all characters match then prints the match.
Like the Naive Algorithm, Rabin-Karp algorithm also slides the pattern one by one. But unlike the Naive algorithm, Rabin Karp algorithm matches the hash value of the pattern with the hash value of current substring of text, and if the hash values match then only it starts matching individual characters. So Rabin Karp algorithm needs to calculate hash values for following strings.
```