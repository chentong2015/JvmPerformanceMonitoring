import jvm.ClassStatistics;
import jvm.ThreadStatistics;
import model.StatisticsSink;
import model.StatisticsSinkConsole;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class JvmStatisticTest {

    public static void main(String[] args) {
        // The management interface for the operating system on which the Java virtual machine is running.
        OperatingSystemMXBean operatingSystem = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(operatingSystem.getName());
        System.out.println(operatingSystem.getArch());
        System.out.println(operatingSystem.getSystemLoadAverage());
        System.out.println(operatingSystem.getAvailableProcessors());

        StatisticsSink<Object> statisticsSink = new StatisticsSinkConsole();

        ClassStatistics classStatistics = new ClassStatistics(ManagementFactory.getClassLoadingMXBean());
        classStatistics.innerCollect(statisticsSink);

        ThreadStatistics threadStatistics = new ThreadStatistics(ManagementFactory.getThreadMXBean());
        threadStatistics.innerCollect(statisticsSink);
    }
}
