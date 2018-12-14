package future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author modong
 * @date 2018/12/13 下午8:08
 */
public class FutureTest {

    public static void main(String[] args) {
        EventLoop eventLoop = new EventLoop();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        new Thread(new Worker(eventLoop, executorService)).start();
        eventLoop.loop();
    }
}
