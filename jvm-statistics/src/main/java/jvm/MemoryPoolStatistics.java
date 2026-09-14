package jvm;

import model.AbstractStatistic;
import model.StatisticsSink;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;

public class MemoryPoolStatistics  extends AbstractStatistic {

    private List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();

    public MemoryPoolStatistics(String name) {
        super(name);
    }

    @Override
    public void innerCollect(StatisticsSink sink) throws Throwable {
        for (MemoryPoolMXBean pool : pools) {
            System.out.println(pool.getName());
            System.out.println(pool.getType()); // Heap or Non-heap
            System.out.println(pool.getUsage());
            System.out.println(pool.getCollectionUsage());
            System.out.println(pool.getPeakUsage());
            System.out.println("------------------------------------");
        }
    }
}
