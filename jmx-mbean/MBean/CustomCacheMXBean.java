import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class CustomCacheMXBean {

    public static void main(String[] args) throws Exception {
        CacheManager cacheManager = new CacheManager();
        ObjectName objectName = new ObjectName("demo:type=CacheManager");

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(cacheManager, objectName);
        System.out.println("JMX Demo started.");
        System.out.println("PID: " + ProcessHandle.current().pid());
        System.out.println("JMX ObjectName: " + objectName);

        while (true) {
            cacheManager.access();
            System.out.println("AccessCount = " + cacheManager.getAccessCount()
                            + ", CacheSize = " + cacheManager.getCacheSize());
            Thread.sleep(3000);
        }
    }
}
