import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class DemoThreadMXBean {

    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println(threadMXBean.getThreadCount());
    }
}
