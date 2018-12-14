package future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author modong
 * @date 2018/12/14 上午11:07
 */
public class Worker implements Runnable {

    private EventLoop eventLoop;

    private ExecutorService executorService;

    public Worker(EventLoop eventLoop, ExecutorService executorService) {
        this.eventLoop = eventLoop;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        for (; ; ) {
            Future<String> future = executorService.submit(new WorkThread());
            eventLoop.addTask(future);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
