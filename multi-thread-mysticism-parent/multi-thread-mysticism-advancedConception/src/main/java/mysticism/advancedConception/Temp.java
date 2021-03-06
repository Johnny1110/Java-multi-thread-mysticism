package mysticism.advancedConception;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class Temp<E> {

    private final ReentrantLock takeLock = new ReentrantLock();
    private final Condition notEmpty = takeLock.newCondition(); // take() 方法使用的 takeLock

    private final ReentrantLock putLock = new ReentrantLock();
    private final Condition notFull = putLock.newCondition();   // put() 方法使用的 putLock

    private AtomicInteger count = new AtomicInteger(0);
    private int capacity = 36;


    public E take() throws InterruptedException {
        E x;
        int c = -1;
        final AtomicInteger count = this.count;
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lockInterruptibly();
        try {
            try {
                while (count.get() == 0) {
                    notEmpty.await();
                }
            } catch (InterruptedException e) {
                notEmpty.signal();
                throw e;
            }
            x = extract(); // 取出頭部第一筆資料，不放回。
            c = count.getAndDecrement(); // 數量 -1，原子操作，c 為 -1 前的值。

            if (c > 1) {
                notEmpty.signal(); // 數量還有，可以繼續取值
            }
        }finally {
            takeLock.unlock();
        }
        if (c == capacity) {
            signalNotFull(); // c == capacity 代表 take 之前空間被塞滿了，put() 都在等，所以取出一個後要通知 put 操作可以繼續了。
        }
        return x;
    }

    private void signalNotFull() {
    }

    private E extract() {
        return null;
    }

    public void put(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        int c = -1;
        final ReentrantLock putLock = this.putLock;
        final AtomicInteger count = this.count;
        putLock.lockInterruptibly();
        try{
            try{
                while (count.get() == capacity){
                    notFull.await();
                }
            }catch (InterruptedException ex) {
                notFull.signal();
                throw ex;
            }
            insert(e);
            c = count.getAndIncrement(); // 更新總數量，c 是 count+1 前的值。
            if (c + 1 < capacity) { // 如果新增後的數量 < 總容量。
                notFull.signal(); // 通知等待的 put() 還有空間。
            }
        }finally {
            putLock.unlock();
        }
        if (c == 0) {
            signalNotEmpty();  // c == 0 代表 put() 前隊列已空，take() 們都在等，所以要通知 take() 們可以繼續。
        }
    }

    private void signalNotEmpty() {
    }

    private void insert(E e) {

    }

}
