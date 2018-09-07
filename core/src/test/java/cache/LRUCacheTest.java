package cache;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author modong
 * @date 2018/8/22 下午7:47
 */
public class LRUCacheTest {


    @Test
    public void test() throws Exception {
        LRUCache<String, Integer> lruCache = new LRUCache<>(5);

        lruCache.put("a", 1);
        lruCache.put("b", 2);
        lruCache.put("c", 3);
        lruCache.put("d", 4);
        lruCache.put("e", 5);
        Assert.assertTrue(1 == lruCache.get("a"));
        Assert.assertEquals(5, lruCache.size());

        //触发lru
        lruCache.put("f", 6);
        Assert.assertEquals(5, lruCache.size());

        Assert.assertNull(lruCache.get("a"));
    }


}
