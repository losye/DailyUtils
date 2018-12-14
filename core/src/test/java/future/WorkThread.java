package future;

import java.util.concurrent.Callable;

/**
 * @author modong
 * @date 2018/12/14 上午11:08
 */
public class WorkThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(100);
        return "Callback";
    }
}
