package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author modong
 * @date 2018/12/14 上午11:07
 */
public class EventLoop {
    private  LinkedBlockingDeque<Future> taskQueue = new LinkedBlockingDeque<>();


    public  void addTask(Future<?> future) {
        taskQueue.add(future);
    }

    public  void loop()  {
        for (; ; ) {
            taskQueue.forEach(future -> {
                if (future.isDone() || future.isCancelled()) {
                    try {
                        Object o = future.get();
                        System.out.println("future get " + o);
                        taskQueue.remove();
                        System.out.println(taskQueue.size());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
