package core;

import model.AbstractStatistic;
import model.StatisticsSink;

import java.lang.management.ClassLoadingMXBean;

public class ClassStatistics extends AbstractStatistic {

    private static final String CLASS_HEADER = "class";
    private static final String CLASS_VAR_LOADED = "loaded";
    private static final String CLASS_VAR_UNLOADED = "unloaded";

    private final ClassLoadingMXBean classloading;

    public ClassStatistics(ClassLoadingMXBean classloading) {
        super(CLASS_HEADER);
        this.classloading = classloading;
    }

    @Override
    public void innerCollect(StatisticsSink sink) {
        sink.add(CLASS_VAR_LOADED, classloading.getLoadedClassCount());
        sink.add(CLASS_VAR_UNLOADED, classloading.getUnloadedClassCount());
    }
}

