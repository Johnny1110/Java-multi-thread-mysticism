package mysticism.highLevelAPI;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo implements Runnable{

    private final Semaphore semap = new Semaphore(5);

    @Override
    public void run() {
        try{
            semap.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " done!");
            semap.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 0; i < 20; ++i){
            exec.submit(demo);
        }
        exec.shutdown();
    }
}
