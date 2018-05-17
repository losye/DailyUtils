package queue.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @Author: zhengye.zhang
 * @Description:
 * @Date: 2018/5/17 下午4:51
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
