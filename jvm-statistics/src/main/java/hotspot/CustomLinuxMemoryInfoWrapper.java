package hotspot;

import com.sun.management.OperatingSystemMXBean;

import javax.management.ObjectName;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Wraps original OperatingSystemMXBean to correct Linux getFreePhysicalMemorySize.
 * It does not take into account cache buffers (files cache).
 */
public class CustomLinuxMemoryInfoWrapper implements OperatingSystemMXBean {

    private static final Pattern CACHED_PATTERN = Pattern.compile("Cached:\\s*(\\d+) kB$");

    private final OperatingSystemMXBean os;

    public CustomLinuxMemoryInfoWrapper(OperatingSystemMXBean os) {
        this.os = os;
    }

    @Override
    public String getName() {
        return os.getName();
    }

    @Override
    public String getArch() {
        return os.getArch();
    }

    @Override
    public String getVersion() {
        return os.getVersion();
    }

    @Override
    public int getAvailableProcessors() {
        return os.getAvailableProcessors();
    }

    @Override
    public double getSystemLoadAverage() {
        return os.getSystemLoadAverage();
    }

    @Override
    public long getCommittedVirtualMemorySize() {
        return os.getCommittedVirtualMemorySize();
    }

    @Override
    public long getFreePhysicalMemorySize() {
        return os.getFreePhysicalMemorySize() + getLinuxFSCacheSize();
    }

    @Override
    public long getFreeMemorySize() {
        return 0;
    }

    @Override
    public long getFreeSwapSpaceSize() {
        return os.getFreeSwapSpaceSize();
    }

    @Override
    public long getProcessCpuTime() {
        return os.getProcessCpuTime();
    }

    @Override
    public long getTotalPhysicalMemorySize() {
        return os.getTotalPhysicalMemorySize();
    }

    @Override
    public long getTotalMemorySize() {
        return 0;
    }

    @Override
    public long getTotalSwapSpaceSize() {
        return os.getTotalSwapSpaceSize();
    }

    @Override
    public ObjectName getObjectName() {
        return null;
    }

    @Override
    public double getProcessCpuLoad() {
        return 0;
    }

    @Override
    public double getSystemCpuLoad() {
        return 0;
    }

    @Override
    public double getCpuLoad() {
        return 0;
    }

    /**
     * @return current Linux FS cache buffers size.
     * It reduces the free space so you may add it to free space to get actual free space.
     */
    static long getLinuxFSCacheSize() {
        File meminfo = new File("/proc/meminfo");
        if (!meminfo.exists() || !meminfo.canRead()) return 0;
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(meminfo))) {
                return parseLinuxFSCacheSize(reader);
            }
        } catch (IOException ex) {
            return 0;
        }
    }

    static long parseLinuxFSCacheSize(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            Matcher matcher = CACHED_PATTERN.matcher(line);
            if (matcher.matches()) return Long.parseLong(matcher.group(1)) * 1024;
        }
        return 0;
    }
}
