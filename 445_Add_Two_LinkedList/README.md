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

## Solution - 直接 Reverse Result

不用單獨考慮其中一個為null的情況，因為就算長度不同，我們還是會把後面的一起加入，最後才反轉。

使用了摩運算反而增加了運算速度，但增加了可讀性。

```java
int sum = (num1 + num2 + carry) % 10;
carry = (num1 + num2 + carry) / 10;
```

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode _reverse_l1 = reverseLinkedList(l1);
        ListNode _reverse_l2 = reverseLinkedList(l2);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        boolean carry = false;
        
        while(_reverse_l1 != null || _reverse_l2 != null || carry) {
            int num1 = _reverse_l1 != null? _reverse_l1.val : 0;
            int num2 = _reverse_l2 != null? _reverse_l2.val : 0;
            int sum = carry? num1 + num2 + 1 : num1 + num2;
            carry = false;
            if(sum > 9) {
                sum -= 10;
                carry = true;
            }
            cur.next = new ListNode(sum);
            cur = cur.next;
            if(_reverse_l1 != null) _reverse_l1 = _reverse_l1.next;
            if(_reverse_l2 != null) _reverse_l2 = _reverse_l2.next;
        }
        
        return reverseLinkedList(dummy.next);
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

```java
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        
        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }
        
        return list.val == 0 ? list.next : list;
    }
}
```