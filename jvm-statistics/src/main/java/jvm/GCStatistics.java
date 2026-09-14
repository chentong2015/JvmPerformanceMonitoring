package jvm;

import model.AbstractStatistic;
import model.StatisticsSink;

import java.lang.management.GarbageCollectorMXBean;

public class GCStatistics extends AbstractStatistic {

    private static final String GC_HEADER = "gc";
    private static final String GC_VAR_COUNT = "count";
    private static final String GC_VAR_TIME = "time";

    private final GarbageCollectorMXBean gc;

    public GCStatistics(GarbageCollectorMXBean gc) {
        super(GC_HEADER + "(" + gc.getName() + ")");
        this.gc = gc;
    }

    @Override
    public void innerCollect(StatisticsSink sink) {
        sink.add(GC_VAR_COUNT, gc.getCollectionCount());
        sink.addDuration(GC_VAR_TIME, gc.getCollectionTime());
    }
}

