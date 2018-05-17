package queue.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * @Author: zhengye.zhang
 * @Description:
 * @Date: 2018/5/17 下午4:52
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch){
        System.out.println("Event: " + event);
    }
}
