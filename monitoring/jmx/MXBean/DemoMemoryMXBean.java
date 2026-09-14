import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;

public class DemoMemoryMXBean {

    public static void main(String[] args) {
        // Returns the managed bean for the memory system of the Java virtual machine
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heap = memoryMXBean.getHeapMemoryUsage();
        System.out.println("init = " + heap.getInit());
        System.out.println("used = " + heap.getUsed());
        System.out.println("committed = " + heap.getCommitted());
        System.out.println("max = " + heap.getMax());

        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean pool : pools) {
            // System.out.println(pool.getName());
            // System.out.println(pool.getType()); // Heap or Non-heap
            // System.out.println(pool.getUsage());
            // System.out.println(pool.getCollectionUsage());
            // System.out.println(pool.getPeakUsage());
            // System.out.println("------------------------------------");
        }
    }
}
