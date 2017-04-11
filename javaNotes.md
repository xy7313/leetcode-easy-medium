1. `String str2rvs = new StringBuilder(str2).reverse().toString();`
2. `for(Integer value : map.values())`
3. `for(Integer key : map.keySet())`
4. 
5. `for(int n: nums) map.put(n,map.getOrDefault(n,0)+1);`
6. `PriorityQueue<Map.Entry<Integer,Integer>> maxHeap = new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));`
7. `maxHeap.addAll(map.entrySet());`





### 关于queue！
1. The java.util.Queue is a subtype of java.util.Collection interface. It is an ordered list of objects with its use limited to inserting elements at the end of list and deleting elements from the start of list i.e. it follows FIFO principle.
2. 可以用priority queue和linked list实现
1. 用priority queue实现时, add(E) might throw an exception while offer(E) will simply return false.(offer，add区别：一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，多出的项就会被拒绝。这时新的 offer 方法就可以起作用了。它不是对调用 add() 方法抛出一个 unchecked 异常，而只是得到由 offer() 返回的 false。)
2. poll will return null if there are no elements. remove() will throw a NoSuchElementException
3. peek，element区别：element() 和 peek() 用于在队列的头部查询元素。与 remove() 方法类似，在队列为空时， element() 抛出一个异常，而 peek() 返回 null
4. *异常类型（不太重要）：add：如果队列已满，则抛出一个IIIegaISlabEepeplian异常；remove：如果队列为空，则抛出一个NoSuchElementException异常；element：如果队列为空，则抛出一个NoSuchElementException异常
