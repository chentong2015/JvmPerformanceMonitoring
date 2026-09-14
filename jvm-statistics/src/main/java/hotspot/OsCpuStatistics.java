package hotspot;

import model.AbstractStatistic;
import com.sun.management.OperatingSystemMXBean;
import model.StatisticsSink;

public class OsCpuStatistics extends AbstractStatistic {

    private static final String CPU_HEADER = "cpu";
    private static final String CPU_VAR_LOAD = "load";
    private static final String CPU_VAR_CORES = "cores";

    private final OperatingSystemMXBean hosx;
    private final int processors;
    private long time = System.currentTimeMillis();
    private long cpu;

    OsCpuStatistics(OperatingSystemMXBean hosx, int processors) {
        super(CPU_HEADER);
        this.hosx = hosx;
        this.processors = processors;
        cpu = hosx.getProcessCpuTime() / 1000000;
    }

    @Override
    protected void innerCollect(StatisticsSink sink) {
        sink.add(CPU_VAR_CORES, processors);
        long currentTime = System.currentTimeMillis();
        long currentCpu = hosx.getProcessCpuTime() / 1000000;
        if (currentTime != time) {
            float cpuPercent = computeCpuPercentage(cpu, currentCpu, processors, time, currentTime);
            cpu = currentCpu;
            time = currentTime;
            sink.addPercentage(CPU_VAR_LOAD, cpuPercent);
        }
    }
}

