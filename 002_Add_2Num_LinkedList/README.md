# Add Two Number in Linked List
```
2 -> 3 -> 3
3 -> 7 -> 4

R: 5 -> 0 -> 8


5
5

R: 0 -> 1

1 -> 8
2

R: 3 -> 8
```
## Solution 

- Used a int Sum to count those node one by one
- /10 -> to get the carray in next round
- %10 -> to get the digital of number
- Used a dummy node to point to the first ListNode from answer
- Used three pointers stand for ListNode l1, ListNode l2 and ListNode d
- Each time :
* 1. Get the value from l1.val and l2.val to sum (if two of them aren't null)
* 2. Point the d.next to the new ListNode(snum)
* 3. Move forward l1 and l2
* 4. Final Check is sum / 10 = 1, which means we need to give an additional node to the end!
