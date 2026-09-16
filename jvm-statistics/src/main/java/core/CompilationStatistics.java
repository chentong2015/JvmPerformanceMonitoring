package core;

import model.AbstractStatistic;
import model.StatisticsSink;

import java.lang.management.CompilationMXBean;

public class CompilationStatistics extends AbstractStatistic {

    private static final String COMPILE_HEADER = "compile";
    private static final String COMPILE_VAR_TIME = "time";

    private final CompilationMXBean compilation;

    public CompilationStatistics(CompilationMXBean compilation) {
        super(COMPILE_HEADER);
        this.compilation = compilation;
    }

    @Override
    public void innerCollect(StatisticsSink sink) {
        sink.addDuration(COMPILE_VAR_TIME, compilation.getTotalCompilationTime());
    }
}

