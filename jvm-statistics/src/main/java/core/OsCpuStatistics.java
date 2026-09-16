package core;

import model.AbstractStatistic;
import model.StatisticsSink;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

// 获取JVM运行的OS操作系统信息
// The management interface for the operating system on which the Java virtual machine is running.
public class OsCpuStatistics extends AbstractStatistic {

    public OsCpuStatistics(String name) {
        super(name);
    }

    @Override
    public void innerCollect(StatisticsSink sink) {
        OperatingSystemMXBean operatingSystem = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(operatingSystem.getName());
        System.out.println(operatingSystem.getArch());
        System.out.println(operatingSystem.getSystemLoadAverage());
        System.out.println(operatingSystem.getAvailableProcessors());
    }
}
