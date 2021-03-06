# mulit-thread 級別 ( 阻塞、無飢餓、無障礙、無鎖、無等待 )

<br>

--------------------------------

<br>

多執行緒之間必須受到控制，根據多執行緒策略，大致上可以分為 5 種：__阻塞__、__無飢餓__、__無障礙__、__無鎖__、__無等待__。

<br>
<br>

## 阻塞 (Blocking)

<br>

一個 Thread-1 阻塞住，那麼在持有資源的那個 Thread 釋放資源前，Thread-1 無法繼續執行，就塞在那裏。當使用 `synchronized` 語法或 `Reentrantlock` 物件時，我們得到的就是阻塞級開發。

<br>
<br>

## 無飢餓 (Starvation-Free)

<br>

飢餓問題的產生就是系統允許對執行緒分級，導致有 "高等人" 與 "下等人" 的區別。高等人遠永可以插隊先走，下等人只能在門口等其他人走完才能走。

無飢餓就是眾生平等，不對 Thread 進行分級，所有人來都乖乖排隊，大家機會都是一樣的，先來後到。

<br>
<br>

## 無障礙 (Obstruction-Free)

<br>

無障礙級是一種弱化非阻塞調度。在這種級別開發下，Thread 們不會因為競爭資源而進行等待掛起，而是任意存取共享資源，但這種情況下大家一起改資料就容易會把資料改壞，為避免這種情況，一旦發現這種情況他就會對自己所做的修改進行 rollback ( 俗稱 : `ctrl+Z` )。如果沒有競爭發生，那就相安無事順利工作，還能節省用鎖造成的效能消耗。

阻塞級是一種悲觀策略，他認為 Thread 之間一定會發生不幸衝突，因此他以保護共享資料為第一要點。非阻塞就是一種樂觀策略，他認為 Thread 之間大概率不會發生衝突 ( 不是說不會喔 )。所以 __不在資源調用上設置障礙，但是一旦發現衝突就進行 rollback__。

當共享資源 ( 資料 ) 被大量 Thread 寫來寫去而發生衝突時，所有 Thread 可能會一直在回滾，然後就沒有人可以完成工作了。

而有一種可行的方法解決這個問題，就是 "__一致性標記__"，共享資料假設是一個物件，這個物件中包還本身代表的 `value` 之外，還有一個 `mark` 參數。Thread 在操作修改 `value` 動作之前會先讀取 `mark` 並記錄，修改操作完成後再次讀取一次 `mark`，比對這個標記前後兩次讀取是否一致，如果一致的話就把 `mark` 修改成其他值，如果不一致就說明已經有其他 Thread 做過修改 `value` 的動作了，並且也已經修改過 `mark` 了，所以立刻進行 rollback。

<br>
<br>

## 無鎖 (Lock-Free)

<br>

無鎖的前提必須要是無障礙的。所有 Thread 都可以自由訪問共享資料，但無鎖保證必定會有一個 Thread 可以完成工作並退出。

無鎖的典型就是 Thread 用無限迴圈不斷嘗試修改共享資料，如果沒有衝突產生就代表修改成功直接退出無限迴圈結束任務，否則就一直試下去，如果運氣不好就會產生類似飢餓狀況，一直無法成功執行任務。

無鎖會大量使用 CAS (Compare And Swap) 機制，這是非常重要的概念，後面會深入研究。

<br>
<br>

## 無等待 (Wait-Free)

<br>

無等待在無鎖的基礎上進行擴展，他要求 Thread 必須在有限步數內完成任務，防止飢餓問題產生。

典型的無等待結構試 RCU ( Read Copy Update )。核心理念是對資料的 read 動作不加控制，所有執行 read 動作的 Thread 都可以無等待直接執行讀操作。但在 write 動作時，要先取得原始資料副本 ( Copy )，然後只修改副本資料，修改好後再找一個合適時機正式 Update 資料。

<br>
<br>