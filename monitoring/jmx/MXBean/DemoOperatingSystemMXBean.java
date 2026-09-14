import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class DemoOperatingSystemMXBean {

    public static void main(String[] args) {
        // The management interface for the operating system on which the Java virtual machine is running.
        OperatingSystemMXBean operatingSystem = ManagementFactory.getOperatingSystemMXBean();

        System.out.println(operatingSystem.getName());
        System.out.println(operatingSystem.getArch());
        System.out.println(operatingSystem.getSystemLoadAverage());
        System.out.println(operatingSystem.getAvailableProcessors());
    }
}
