package core;

import io.dropwizard.metrics5.MetricRegistry;
import io.dropwizard.metrics5.jvm.*;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;

public class BaseMetricsJvm {

    public static void main(String[] args) {
        MetricRegistry registry = new MetricRegistry();
        registry.registerAll(new JvmAttributeGaugeSet());
        registry.registerAll(new MemoryUsageGaugeSet());
        registry.registerAll(new ClassLoadingGaugeSet());
        registry.registerAll(new ThreadStatesGaugeSet());

        // 创建数组对象: 数据存储到Heap内存的Young年轻代
        byte[] array = new byte[256 * 1024 * 1024];
        registry.registerAll(new GarbageCollectorMetricSet());

        // 分配堆外内存, 测试NIO缓冲区池的大小
        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        registry.registerAll(new BufferPoolMetricSet(mBeanServer));

        registry.getGauges().forEach((name, gauge) -> {
            System.out.println(name + " = " + gauge.getValue());
        });
    }
}