import java.util.HashMap;
import java.util.Map;

public class CacheManager  implements CacheManagerMXBean {

    private final Map<String, String> cache = new HashMap<>();
    private long accessCount = 0;

    public CacheManager() {
        for (int i = 0; i < 10; i++) {
            cache.put("key-" + i, "value-" + i);
        }
    }

    @Override
    public int getCacheSize() {
        return cache.size();
    }

    @Override
    public long getAccessCount() {
        return accessCount;
    }

    @Override
    public void clearCache() {
        cache.clear();

        System.out.println("Cache cleared by JMX!");
    }

    public void access() {
        accessCount++;

        String key = "key-" + (accessCount % 10);
        cache.put(key, "value-" + accessCount);
    }
}
