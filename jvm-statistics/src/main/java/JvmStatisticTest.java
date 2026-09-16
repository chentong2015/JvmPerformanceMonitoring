import core.ClassStatistics;
import core.OsCpuStatistics;
import core.ThreadStatistics;
import model.StatisticsSink;
import model.StatisticsSinkConsole;

import java.lang.management.ManagementFactory;

public class JvmStatisticTest {

    public static void main(String[] args) {
        OsCpuStatistics osCpuStatistics = new OsCpuStatistics("OS CPU");
        osCpuStatistics.innerCollect(new StatisticsSinkConsole());

        StatisticsSink<Object> statisticsSink = new StatisticsSinkConsole();

        ClassStatistics classStatistics = new ClassStatistics(ManagementFactory.getClassLoadingMXBean());
        classStatistics.innerCollect(statisticsSink);

        ThreadStatistics threadStatistics = new ThreadStatistics(ManagementFactory.getThreadMXBean());
        threadStatistics.innerCollect(statisticsSink);
    }
}
