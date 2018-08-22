package cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author modong
 * @date 2018/8/22 下午5:09
 * the LRU cache
 */
public class LRUCache<K, V> {
    /**
     * 最大缓存大小
     */
    private int maxCacheSize;

    private LinkedHashMap<K, V> cache;

    public LRUCache(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
        this.cache = new LinkedHashMap<K, V>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return cache.size() == maxCacheSize + 1;
            }
        };
    }


    public V get(K k) {
        return cache.get(k);
    }

    public V getOrDefault(K key, V defaultValue) {
        return cache.getOrDefault(key, defaultValue);
    }

    public V put(K key, V value) {
        return cache.put(key, value);
    }

    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    public void clear() {
        cache.clear();
    }

    public V remove(K key) {
        return cache.remove(key);
    }

    public int size() {
        return cache.size();
    }

    public boolean isEmpty() {
        return cache.isEmpty();
    }

    public boolean containsValue(V value) {
        return cache.containsValue(value);
    }

}
