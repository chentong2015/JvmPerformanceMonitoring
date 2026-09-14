package hotspot;

import model.StatisticsSink;
import sun.management.HotspotThreadMBean;

public class HotspotThreadStatistics {

    private static final String THREADS_VAR_INTERNAL = "internal";

    private final HotspotThreadMBean hsThread;

    public HotspotThreadStatistics(HotspotThreadMBean hsThread) {
        this.hsThread = hsThread;
    }

    public void innerCollect(StatisticsSink sink) {
        sink.add(THREADS_VAR_INTERNAL, hsThread.getInternalThreadCount());
    }
}

