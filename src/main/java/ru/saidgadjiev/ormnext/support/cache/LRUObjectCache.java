package ru.saidgadjiev.ormnext.support.cache;

import ru.saidgadjiev.ormnext.core.cache.ObjectCache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache with LRU caching algorithm.
 *
 * @param <ID> cache key type
 * @param <T>  cache value type
 * @author Said Gadjiev
 */
public class LRUObjectCache<ID, T> implements ObjectCache<ID, T> {

    /**
     * Default cache size.
     */
    private static final int DEFAULT_CACHE_SIZE = 16;

    /**
     * Cached classes map.
     */
    private Map<Class<T>, Map<ID, T>> cache = new ConcurrentHashMap<>();

    /**
     * Max cache size.
     */
    private int maxSize;

    /**
     * Create a new instance.
     *
     * @param maxSize target max size
     */
    public LRUObjectCache(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Create a new instance with default max size.
     */
    public LRUObjectCache() {
        this.maxSize = DEFAULT_CACHE_SIZE;
    }

    @Override
    public void registerClass(Class<T> tClass) {
        cache.computeIfAbsent(tClass, k -> createLRUMap(maxSize));
    }

    @Override
    public void put(Class<T> tClass, ID id, T data) {
        Map<ID, T> objectCache = cache.get(tClass);

        if (objectCache != null) {
            objectCache.put(id, data);
        }
    }

    @Override
    public T get(Class<T> tClass, ID id) {
        Map<ID, T> objectCache = cache.get(tClass);

        if (objectCache == null) {
            return null;
        }

        return objectCache.get(id);
    }

    @Override
    public boolean contains(Class<T> tClass, ID id) {
        Map<ID, T> objectCache = cache.get(tClass);

        return objectCache != null && objectCache.containsKey(id);
    }

    @Override
    public void invalidate(Class<T> tClass, ID id) {
        Map<ID, T> objectCache = cache.get(tClass);

        if (objectCache == null) {
            return;
        }
        objectCache.remove(id);
    }

    @Override
    public void invalidateAll(Class<T> tClass) {
        cache.remove(tClass);
    }

    @Override
    public void invalidateAll() {
        cache.forEach((key, value) -> value.clear());
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public long size(Class<T> tClass) {
        Map<ID, T> objectCache = cache.get(tClass);

        if (objectCache == null) {
            return 0;
        }

        return objectCache.size();
    }

    /**
     * Create a new LRU map.
     *
     * @param maxSize target max size
     * @return created map with LRU remove algorithm
     */
    private Map<ID, T> createLRUMap(int maxSize) {
        return new LinkedHashMap<ID, T>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<ID, T> eldest) {
                return size() > maxSize;
            }
        };
    }
}
