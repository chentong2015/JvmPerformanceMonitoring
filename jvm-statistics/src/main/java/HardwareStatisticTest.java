import hardware.DiskStatistics;
import hardware.MachineCpuStatistics;
import model.StatisticsSinkConsole;

public class HardwareStatisticTest {

    public static void main(String[] args) throws Throwable {
        DiskStatistics diskStatistics = new DiskStatistics();
        diskStatistics.innerCollect(new StatisticsSinkConsole());

        MachineCpuStatistics machineCpuStatistics = new MachineCpuStatistics();
        machineCpuStatistics.innerCollect(new StatisticsSinkConsole());
    }
}