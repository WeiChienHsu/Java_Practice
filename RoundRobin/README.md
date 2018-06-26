# Round Robin 

[Round Robin CPU Scheduling](https://www.youtube.com/watch?v=aWlQYllBZDs)

| Process | Arrival Time | BT (Execution Time) |
|:-------:|:------------:|:-------------------:|
| P1 | 0 | 5 -> 2 -> 0 |
| P2 | 1 | 3 -> 0 |
| P3 | 3 | 6 -> 3 -> 0 |
| P4 | 5 | 1 -> 0|
| P5 | 6 | 4 -> 1 -> 0|


| Request Queue | Status |
|:-------------:|:------:|
| P1 | Execute P1, rest 2, currentTime = 3, put P2 and P3 and rest of P1 (3,2) into Queue |
| P2 | Execute P2, rest 0, currentTime = 6, put P4 into Queue |
| P3 | Execute P3, rest 3, currentTime = 9, put P5 into Queue and rest of P3(9,3) into Queue |
| P1(2) | Execute P1(2), rest 0, currentTime = 11 |
| P4 | Execute P4, rest 0, currentTime = 12 |
| P5 | Execute P5, rest 1, currentTime = 15, put rest of P5(15, 1) into Queue |
| P3(3) | Execute P3(3), rest 0, currentTime = 18 |
| P5(1) | Execute P5(1), rest 0, currentTime = 19 |


先執行Queue peek的process，新增等待時間 + 3，並且更新currentTime + 3 或 小於 3的 exeTime(Math.min(q, currentProcess.exeTime))， 檢查小於currentTime的 arrival Time，把需要加入Queue的Process加入，最後，檢查current process 是否有執行完畢（ exeTime 是否小於 q)，如果沒有執行完畢，要把剩餘需要執行的時間，以新的exeTime更新。

如果過程中，發現process queue已經空了，但還有沒處理的process(index不等於array長度)，要更新currentTime，並且把後面的process加入Queue中，繼續處理，此時不需要更新waiting Time，應為前面的Process都已經處理完畢。


```
|-- P1 --|-- P2 --|-- P3 --|- P1 -|- P4 -|-- P5 --|- P3 -|- P5 -|
0        3        6        9      11     12       15     18     19
```

## Solution

一个处理器要处理一堆request，一次只能处理一条，每次执行一个任务最多执行时间q，接着执行等待着的下一个任务。若前一个任务没执行完则放到队尾，等待下一次执行。

假设只要有任务开始以后cpu是不会空闲的，也就是说cpu开始后如果空闲了就说明没有任务了，另外Robin Round最后返回值是float。


```
输入：arrival time array, execution time array, quantum time
输出：每个process的平均等待时间
Keep a queue to store processes (a process is characterized by its arrival time and execution time)
curTime: 记录当前时间
waitTime: 累计所有等待时间
cur: 当前进行的process
index：指向下一个还没有处理到的process
```

```
create a new queue
curTime, waitTime, index set to 0 (指向下個process)

while(queue 裡面還有 process，或是初始化時，還沒有處理任何process |index < length|) {
    如果（queue里面还有process）{ // 必須處理當前這個process
        1. poll queue得到当前process currProcess
        
        2. 更新wait time（开始处理的时间-到达时间就是waitTime）

        3. 更新curTime到下一节点或者过了q时间，或者当前process run完。（通过比较q和cur.exetime取小的那个）
           
        4. 利用index指針，把这个过程中有可能arrive的新的process加入
          （判断条件，arrive time小于新的时间点并且index在范围内，同时更新index）
        
        5. 如果 cur.exeTime > q 代表會有剩下沒執行完的，要更新一個在Queue的裡面，用(currentTime)當起始時間, (exeTime - q) 當結束時間

    }

    //出现queue裡面沒有process情况有两种可能：
    //1. 初始情况 
    //2. 之前的process都已经run完，新的process的arrival time还没有到。所以只需要更新arrival time，不需要更新waitTime。

    如果（queue里面没有process）{
        1. 把index process放入queue
        2. curTime设为index arrival time
        3. index++ (因为index指向下一个还没有处理的process)
    }
return waitTime/length   
}
```

```java



```