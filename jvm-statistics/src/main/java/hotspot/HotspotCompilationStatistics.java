package hotspot;

import model.StatisticsSink;
import sun.management.HotspotCompilationMBean;

public class HotspotCompilationStatistics {

    private static final String COMPILE_VAR_COUNT = "count";
    private static final String COMPILE_VAR_INVALIDATED = "invalidated";
    private static final String COMPILE_VAR_FAILED = "failed";
    private static final String COMPILE_VAR_THREADS = "threads";

    private final HotspotCompilationMBean hsCompilation;

    public HotspotCompilationStatistics(HotspotCompilationMBean hsCompilation) {
        this.hsCompilation = hsCompilation;
    }

    public void innerCollect(StatisticsSink sink) {
        sink.addDuration(COMPILE_VAR_COUNT, hsCompilation.getTotalCompileCount());
        sink.addDuration(COMPILE_VAR_INVALIDATED, hsCompilation.getInvalidatedCompileCount());
        sink.addDuration(COMPILE_VAR_FAILED, hsCompilation.getBailoutCompileCount());
        sink.addDuration(COMPILE_VAR_THREADS, hsCompilation.getCompilerThreadCount());
    }
}
