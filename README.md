# Multi-thread mysticism (by Java)

<br>

---

<br>
<br>

多執行緒奧義，深入研究 Java 多執行緒相關問題的紀錄歸總。

<br>
<br>
<br>

## 目錄

<br>

### 一、關於開發多執行緒必須知道的幾件事 (基礎概念)

 <br>

* [甚麼時候用並行 (Parallel) ?](note/basic/whenToUseParallel.md)

* [同步 (Synchronous)、非同步 (Asynchronous)](note/basic/synchronousAndAsynchronous.md)

* [並行 (Concurrency)、平行 (Parallelism)](note/basic/concurrencyAndParallelism.md)

* [阻塞 (Blocking)、非阻塞 (Non-Blocking)](note/basic/blockingAndNonBlocking.md)

* [死鎖 (Deadlock)、飢餓 (Starvation)、活鎖 (Livelock)](note/basic/deadlockStarvationLivelock.md)

* [mulit-thread 級別 ( 阻塞、無飢餓、無障礙、無鎖、無等待 )](note/basic/multiThreadGrading.md)

* [Java内存模型（Java Memory Model，__JMM__）
  -> 原子性( Atomicity )、可見性( Visibility )、有序性( Ordering )](note/basic/JMM.md)

* [Java Thread 的 6 個生命週期狀態 （NEW、RUNNABLE、BLOCKED、WAITING、TIME_WAITING、TERMINATED）](note/basic/threadsLifeCycle.md)

  <br>
  
### 二、Java 多執行緒基礎操作中可能被你忽略的點

  <br>

  * [多實作 __Runnable__ 少繼承 __Thread__](note/likelyToBeIgnore/runnableVsThread.md)

  * [如何合理的終止 Thread 運行（不要用 `stop()`方法，`interrupt()` 才是上上策）](note/likelyToBeIgnore/howToWStopThread.md)

  * [`wait()` 與 `notify()` 怎麼用 ?](note/likelyToBeIgnore/waitAndNotify.md)

  * [不要用 `suspend()` 與 `resume()`](note/likelyToBeIgnore/suspendAndResume.md)

  * [`join()` 與 `yield()`](note/likelyToBeIgnore/joinAndYield.md)

  * [volatile 可以保證可見性與有序性，但不能保證原子性](note/likelyToBeIgnore/volatile.md)

  * [Thread Safe 與 synchronized 概念](note/likelyToBeIgnore/threadSafeAndSynchronized.md)

  * [使用 __ThreadGroup__ 管理 Thread 們](note/likelyToBeIgnore/threadGroup.md)

  * [Daemon 守護執行緒（後臺運行）](note/likelyToBeIgnore/daemon.md)

  * [Thread 的優先級，權貴與庶民](note/likelyToBeIgnore/threadPrioity.md)

  * [並發情況下不要用 __ArrayList__，改用 __Vector__](note/likelyToBeIgnore/arrayList.md)

  * [__HashMap__ 也不安全，改用 __ConcurrentHashMap__](note/likelyToBeIgnore/hashMap.md)

  * [千萬不要對 __Integer__ 上鎖 ！！！](note/likelyToBeIgnore/dontLockInt.md)

  <br>

### 三、Java 多執行緒高級 API

  <br>


  * [`synchronized()` 的替代品： __ReentrantLock__ （中斷等待、鎖申請等待時間、公平鎖）](note/highLevelAPI/ReentrantLock.md)

  * [`wait()`、`notify()` 的替代品：__Condition__](note/highLevelAPI/Condition.md)

  * [__Semaphore__ 信號量，允許多個 Thread 同時訪問](note/highLevelAPI/Semaphore.md)

  * [__ReadWriteLock__ 讀寫分離鎖](note/highLevelAPI/ReadWriteLock.md)

  * [Thread 完成數量達標才放行：__CountDownLatch__](note/highLevelAPI/CountDownLatch.md)

  * [循環柵欄：__CyclicBarrier__（士兵集結完畢後再一起作戰）](note/highLevelAPI/CyclicBarrier.md)

  * [取代 `suspend()` 與 `resume()` 的最佳工具：__LockSupport__](note/highLevelAPI/LockSupport.md)

  <br>

  ### 四、Thread Pool 系列

  <br>

  * [__Executor__ 框架介紹](note/threadPool/Executor.md)

  * [固定大小 ThreadPool 與計畫任務](note/threadPool/fixThreadPoolNScheduledThreadPool.md)

  * [探究底層：ThreadPool 的內部實作（自定義 __ThreadPoolExecutor__ 必看）](note/threadPool/howToImpl.md)

  * [任務太多隊列塞爆了：拒絕策略](note/threadPool/rejectHandler.md)

  * [自定義 Thread 創建：__ThreadFactory__](note/threadPool/threadFactory.md)

  * [擴展 ThreadPool，繼承 __ThreadPoolExecutor__](note/threadPool/extendsExecutorPoolExecutor.md)

  * [合理計算並決定 ThreadPool 的 Thread 數量](note/threadPool/computeSuitableThreadCount.md)

  * [在 ThreadPool 中尋找被藏起來的 __Exception__](note/threadPool/findException.md)

  * [Fork/Join 框架（MapReduce 思想）](note/threadPool/forkNJoin.md)

  <br>

  ### 四、多執行緒資料結構

  <br>

  * [好用的工具：多執行緒集合類別簡介](note/dataStructures/intro.md)

  * [（超難）解析高效的讀寫隊列：__ConcurrentLinkedQueue__](note/dataStructures/concurrentLinkedQueue.md)

  * [高效讀取陣列：不變模式下的　__CopyOnWriteArrayList__](note/dataStructures/copyOnWriteArrayList.md)

  * [資料共享通道：__BlockingQueue__](note/dataStructures/blockingQueue.md)

  * [隨機資料結構：跳表（SkipList）實現部分鎖（多執行緒 Map，有序性，快速查找。）](note/dataStructures/skipList.md)


<br>

### 五、開發時要注意的細節和鎖的優化

<br>

* [注意！減小鎖持有時間，能不上鎖就不上鎖](note/advancedConception/dontLockIfPossible.md)

* [注意！鎖粗化（大量分散上鎖會造成浪費），能一起上鎖就一起上鎖](note/advancedConception/LockCoarsening.md)

* [減小鎖粒度](note/advancedConception/Lockgranularity.md)

* [鎖分離](note/advancedConception/LockSeparate.md)

* [JVM 把細節做到了極致（偏向鎖、輕量級鎖、自旋鎖、鎖消除）](note/advancedConception/JvmKeepImproving.md)

* [ThreadLocal，為每個 Thread 分配專屬於自己的資源（不用共享）](note/advancedConception/ThreadLocal.md)

<br>

### 六、無鎖

<br>

* [不一樣的併發思維策略：比較交換（CAS）](note/noLock/CAS.md)

* [Java Pointer：__Unsafe__](note/noLock/Unsafe.md)

* [CAS 原子性操作的不足（被修改後實際值不變）](note/noLock/CASProblem.md)

* [無鎖的 Thread Safe 整數：__AtomicInteger__](note/noLock/AtomicInteger.md)

* [無鎖的物件引用：__AtomicReference__ 與帶有時間戳記雙重驗證的無鎖的物件引用：__AtomicStampedReference__](note/noLock/AtomicReference.md)

* [無鎖的 Array：__AtomicIntegerArray__](note/noLock/AtomicIntegerArray.md)

* [把普通變數進化成原子化變數：__AtomicFieldUpdater__](note/noLock/AtomicFieldUpdater.md)

* [實作練習：無鎖 __Vector__ 實現挑戰](note/noLock/nonLockVector.md)