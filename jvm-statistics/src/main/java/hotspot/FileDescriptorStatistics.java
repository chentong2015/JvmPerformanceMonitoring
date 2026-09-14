package hotspot;

import model.AbstractStatistic;
import com.sun.management.UnixOperatingSystemMXBean;
import model.StatisticsSink;

public class FileDescriptorStatistics extends AbstractStatistic {

    private static final String FILE_DESCRIPTORS_HEADER = "descriptors";
    private static final String FILE_DESCRIPTORS_VAR_OPEN = "open";
    private static final String FILE_DESCRIPTORS_VAR_MAX = "max";

    private final UnixOperatingSystemMXBean unixOs;

    public FileDescriptorStatistics(UnixOperatingSystemMXBean unixOs) {
        super(FILE_DESCRIPTORS_HEADER);
        this.unixOs = unixOs;
    }

    @Override
    public void innerCollect(StatisticsSink sink) {
        sink.add(FILE_DESCRIPTORS_VAR_OPEN, unixOs.getOpenFileDescriptorCount());
        sink.add(FILE_DESCRIPTORS_VAR_MAX, unixOs.getMaxFileDescriptorCount());
    }
}
