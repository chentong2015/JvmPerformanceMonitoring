package metrics_jmx;

import io.dropwizard.metrics5.Counter;
import io.dropwizard.metrics5.MetricRegistry;
import io.dropwizard.metrics5.jmx.JmxReporter;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Set;

public class MetricsJmxDemo {

    public static void main(String[] args) throws Exception {
        MetricRegistry registry = new MetricRegistry();
        Counter counter = registry.counter("test.counter");
        counter.inc(5);

        // TODO. 通过JmxReporter注册MBean到MBeanServer
        JmxReporter reporter = JmxReporter.forRegistry(registry).build();
        reporter.start();

        // 找到Metrics注册的Counter MBean
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> objectNames = mBeanServer.queryNames(null, null);
            ObjectName metricObjectName = objectNames.stream()
                    .filter(name -> name.toString().contains("test.counter"))
                    .findFirst()
                    .orElse(null);

            if (metricObjectName != null) {
                boolean isRegistered = mBeanServer.isRegistered(metricObjectName);
                System.out.println("test.counter registered as JMX MBean: " + isRegistered);

                System.out.println("ObjectName = " + metricObjectName);
                System.out.println("Attributes = " + mBeanServer.getAttribute(metricObjectName, "Count"));
                System.out.println("MBeanInfo = " + mBeanServer.getMBeanInfo(metricObjectName));
            }
        } finally {
            reporter.stop();
        }
    }
}