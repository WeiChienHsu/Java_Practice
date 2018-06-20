# Add Two LinkedList


## Solution - 轉換成數字相加（無法處理太長的List)
將兩串LinkedList轉換成數字，相加之後，不段的在Dummy Node後面新增一個位數(sum % 10)，然後處理原本的總和(sum / 10)。

但如果Input過大的話，是無法處理的。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long number1 = 0;
        long number2 = 0;
        long sum = 0;
        
        while(l1 != null) {
            number1 = number1 * 10 + l1.val;
            l1 = l1.next;
        }
        
        while(l2 != null) {
            number2 = number2 * 10 + l2.val;
            l2 = l2.next;
        }
        
        sum = number1 + number2;
        
        ListNode dummy = new ListNode(0);
        dummy.next = null;
        ListNode current = dummy;
        
        while(sum > 9) {
            ListNode next = current.next;
            current.next = new ListNode((int)(sum % 10));
            current.next.next = next;
            sum /= 10;
        }
        
        ListNode next = current.next;
        current.next = new ListNode((int)sum);
        current.next.next = next;
        
        return dummy.next;
    }
}
```

## Solution - Reverse LinkedList

不好的方法，因為當我把LinkedList翻轉過來後，我是直接相加然後反著插回去Dummy Node之後，但其實可以直接相加得到一個新的LinkedList，然後 return  reverse 後的結果。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode _reverse_l1 = reverseLinkedList(l1);
        ListNode _reverse_l2 = reverseLinkedList(l2);
        ListNode dummy = new ListNode(0);
        
        boolean addOne = false;
        
        while(_reverse_l1 != null &&  _reverse_l2 != null) {
            int sum = addOne? _reverse_l1.val +  _reverse_l2.val + 1 : _reverse_l1.val +  _reverse_l2.val;
            if(sum > 9) {
                sum -= 10;
                ListNode next = dummy.next;
                dummy.next = new ListNode(sum);
                dummy.next.next = next;
                addOne = true;
            } else {
                ListNode next = dummy.next;
                dummy.next = new ListNode(sum);
                dummy.next.next = next;
                addOne = false;
            }

            _reverse_l1 = _reverse_l1.next;
            _reverse_l2 = _reverse_l2.next;
        }
        
        
        while(_reverse_l1 != null) {
            int sum = addOne? _reverse_l1.val + 1 : _reverse_l1.val;
            if(sum > 9) {
                sum -= 10;
                ListNode next = dummy.next;
                dummy.next = new ListNode(sum);
                dummy.next.next = next;
                addOne = true;
            } else {
                ListNode next = dummy.next;
                dummy.next = new ListNode(sum);
                dummy.next.next = next;
                addOne = false;
            }

            _reverse_l1 = _reverse_l1.next;
        }
        
        while(_reverse_l2 != null) {
            int sum = addOne? _reverse_l2.val + 1 : _reverse_l2.val;
            if(sum > 9) {
                sum -= 10;
                ListNode next = dummy.next;
                dummy.next = new ListNode(sum);
                dummy.next.next = next;
                addOne = true;
            } else {
                ListNode next = dummy.next;
                dummy.next = new ListNode(sum);
                dummy.next.next = next;
                addOne = false;
            }
            _reverse_l2 = _reverse_l2.next;
        }
        
        if(addOne) {
            ListNode next = dummy.next;
            dummy.next = new ListNode(1);
            dummy.next.next = next;
        }
        
        return dummy.next;
    }
    

    
    public static ListNode reverseLinkedList(ListNode root){
        ListNode pre = null;
        ListNode cur = root;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        
        return pre;     
    }
}
```



## Solution - Using Stack