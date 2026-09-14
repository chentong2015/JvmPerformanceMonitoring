import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Attribute;
import java.lang.management.ManagementFactory;

public class CustomMBean {

    // ObjectName 对象名称约束
    // domain = demo
    // key property:
    //    type = Demo
    public static void main(String[] args) throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        Demo demo = new Demo(100, "Hello JMX");
        ObjectName objectName = new ObjectName("demo:type=Demo");

        mBeanServer.registerMBean(demo, objectName);
        System.out.println("isRegistered = " + mBeanServer.isRegistered(objectName));


        // 获取MBean的Attribute属性值
        Object id = mBeanServer.getAttribute(objectName, "Id");
        Object name = mBeanServer.getAttribute(objectName, "Name");
        System.out.println("Id   = " + id);
        System.out.println("Name = " + name);

        // 获取MBean的MetaInfo元信息
        System.out.println(mBeanServer.getMBeanInfo(objectName));


        mBeanServer.setAttribute(objectName, new Attribute("Name", "Hello MBean"));
        System.out.println("Name after = " + mBeanServer.getAttribute(objectName, "Name"));

        // MBean Operation 调用方法/做管理操作
        Object result = mBeanServer.invoke(objectName,
                "hello",
                new Object[]{ "Tom" },
                new String[]{ String.class.getName() }
        );
        System.out.println("invoke result = " + result);

        mBeanServer.unregisterMBean(objectName);
        System.out.println("isRegistered = " + mBeanServer.isRegistered(objectName));
    }

    // Standard MBean 的管理接口
    public interface DemoMBean {

        int getId();

        void setId(int id);

        String getName();

        void setName(String name);

        String hello(String username);
    }

    public static class Demo implements DemoMBean {

        private int id;
        private String name;

        public Demo(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String hello(String username) {
            return "Hello " + username + ", name=" + name;
        }
    }
}