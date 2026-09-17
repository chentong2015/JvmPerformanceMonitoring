import com.sun.management.HotSpotDiagnosticMXBean;

import javax.management.MBeanServer;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class HotSpotDiagnosticMXBeanTest {

    // TODO. 测试在代码层面输出Heap Dump
    public static void dumpHeap(String filePath, boolean live) throws IOException {
        String mxBeanName = "com.sun.management:type=HotSpotDiagnostic";
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        HotSpotDiagnosticMXBean mxBean = ManagementFactory.newPlatformMXBeanProxy(server, mxBeanName, HotSpotDiagnosticMXBean.class);

        mxBean.dumpHeap(filePath, live);
    }
}
