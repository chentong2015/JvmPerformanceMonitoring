package hotspot;

import model.AbstractStatistic;

import com.sun.management.OperatingSystemMXBean;
import model.StatisticsSink;

public class OsMemoryStatistics extends AbstractStatistic {

    private static final String OS_HEADER = "os";
    private static final String OS_VAR_LOADAVG = "loadavg";

    private static final String OS_VAR_PHYSICAL_FREE = "physicalfree";
    private static final String OS_VAR_PHYSICAL_TOTAL = "physicaltotal";
    private static final String OS_VAR_SWAP_FREE = "swapfree";
    private static final String OS_VAR_SWAP_TOTAL = "swaptotal";
    private static final String OS_VAR_VIRTUAL = "virtual";

    private final OperatingSystemMXBean hsOs;
    private final int processors;

    public OsMemoryStatistics(OperatingSystemMXBean hsOs, int processors) {
        super(OS_HEADER);
        this.hsOs = hsOs;
        this.processors = processors;
    }

    @Override
    public void innerCollect(StatisticsSink sink) {
        if (isLoadAverageAvailable()) {
            sink.addPercentage(OS_VAR_LOADAVG, (int) (100 * hsOs.getSystemLoadAverage() / processors));
        }

        sink.addSize(OS_VAR_PHYSICAL_FREE, hsOs.getFreePhysicalMemorySize());
        sink.addSize(OS_VAR_PHYSICAL_TOTAL, hsOs.getTotalPhysicalMemorySize());
        sink.addSize(OS_VAR_SWAP_FREE, hsOs.getFreeSwapSpaceSize());
        sink.addSize(OS_VAR_SWAP_TOTAL, hsOs.getTotalSwapSpaceSize());
        sink.addSize(OS_VAR_VIRTUAL, hsOs.getCommittedVirtualMemorySize());
    }

    private boolean isLoadAverageAvailable() {
        return hsOs.getSystemLoadAverage() >= 0;
    }
}
