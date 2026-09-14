package jvm;

import model.AbstractStatistic;
import model.StatisticsSink;

import java.lang.management.ThreadMXBean;

public class ThreadStatistics extends AbstractStatistic {

    private static final String THREADS_HEADER = "threads";
    private static final String THREADS_VAR_COUNT = "count";
    private static final String THREADS_VAR_DAEMON = "daemon";
    private static final String THREADS_VAR_TOTAL = "total";

    private final ThreadMXBean thread;

    public ThreadStatistics(ThreadMXBean thread) {
        super(THREADS_HEADER);
        this.thread = thread;
    }

    @Override
    public void innerCollect(StatisticsSink sink) {
        sink.add(THREADS_VAR_COUNT, thread.getThreadCount());
        sink.add(THREADS_VAR_DAEMON, thread.getDaemonThreadCount());
        sink.add(THREADS_VAR_TOTAL, thread.getTotalStartedThreadCount());
    }
}

