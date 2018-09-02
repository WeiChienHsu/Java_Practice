# Subdomain Visit Count

Example

```
Example 2:
Input: 
["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
Output: 
["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
Explanation: 
We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.

```

## Solution
- Substring: "unhappy".substring(2) returns "happy", "smiles".substring(1, 5) returns "mile".

```java
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
```