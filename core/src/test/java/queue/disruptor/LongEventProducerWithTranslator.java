package queue.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @Author: zhengye.zhang
 * @Description:
 * @Date: 2018/5/17 下午4:57
 */
public class LongEventProducerWithTranslator {

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer>
            TRANSLATOR = (event, sequence, bb) -> event.set(bb.getLong(0));

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}
