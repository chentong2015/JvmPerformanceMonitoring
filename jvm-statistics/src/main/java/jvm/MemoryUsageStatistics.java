package jvm;

import model.AbstractStatistic;
import model.StatisticsSink;

import java.lang.management.MemoryUsage;
import java.util.function.Supplier;

public class MemoryUsageStatistics extends AbstractStatistic {

    private static final String MEMORY_POOL_VAR_USED = "used";
    private static final String MEMORY_POOL_VAR_COMMITTED = "committed";
    private static final String MEMORY_POOL_VAR_INIT = "init";
    private static final String MEMORY_POOL_VAR_MAX = "max";

    private final Supplier<MemoryUsage> memUsageSupplier;

    public MemoryUsageStatistics(String name, Supplier<MemoryUsage> memUsageSupplier) {
        super(name);
        this.memUsageSupplier = memUsageSupplier;
    }

    @Override
    public void innerCollect(StatisticsSink sink) {
        MemoryUsage usage = memUsageSupplier.get();
        sink.addSize(MEMORY_POOL_VAR_USED, usage.getUsed());
        sink.addSize(MEMORY_POOL_VAR_COMMITTED, usage.getCommitted());
        sink.addSize(MEMORY_POOL_VAR_INIT, usage.getInit());
        sink.addSize(MEMORY_POOL_VAR_MAX, usage.getMax());
    }
}
