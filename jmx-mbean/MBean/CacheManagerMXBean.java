public interface CacheManagerMXBean {

    int getCacheSize();

    long getAccessCount();

    void clearCache();
}
