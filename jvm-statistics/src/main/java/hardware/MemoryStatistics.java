package hardware;

import model.AbstractStatistic;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import model.StatisticsSink;

public class MemoryStatistics extends AbstractStatistic {

    private static final String MEMORY_HEADER = "memory";
    private static final String MEMORY_VAR_SWAP = "swap";
    private static final String MEMORY_VAR_PHYSICAL = "physical";

    private GlobalMemory globalMemory = new SystemInfo().getHardware().getMemory();

    public MemoryStatistics() {
        super(MEMORY_HEADER);
    }

    @Override
    public void innerCollect(StatisticsSink sink) throws Throwable {
        // sink.addSize(MEMORY_VAR_SWAP, globalMemory.getSwapTotal() - globalMemory.getSwapUsed());
        sink.addSize(MEMORY_VAR_PHYSICAL, globalMemory.getAvailable());
    }
}

