import base.InnerClassField;
import base.OuterClass;

public class GcRootsObject {

    // TODO. 这个实例字段本身不是GC Root
    // 当InnerClassField对象变成“从任何GC Root都不可达”时，才具有被GC自动回收的资格 !!
    private final InnerClassField innerObject = new InnerClassField();

    private static InnerClassField innerObjectStatic = new InnerClassField();

    public static void main(String[] args) {
        // TODO. 这里创建的线程栈局部变量才是GC Root
        // 只要该局部对象可达(存在)，则它属性所引用的对象就不能被GC !!
        GcRootsObject gcRootDemo = new GcRootsObject();

        gcRootDemo.testGcCollectObject(); // sleep

        // 确保gcRootDemo依然是活跃变量 => 通过Heap Dump查看GC Roots对象
        System.out.println(gcRootDemo);
        System.out.println(innerObjectStatic);

        // 理论上: 当局部变量置空时，InnerClassField对象也不可达
        gcRootDemo = null;

        // 理论上: 重新创建对象，InnerClassField对象也会被重新创建
        gcRootDemo = new GcRootsObject();

        // 当方法执行结束返回后，不可达的InnerClassField对象自动被回收
    }

    public void testGcCollectObject() {
        // 在子线程中引用实例字段所引用的对象
        new Thread(() -> {
            // TODO. 在run()方法的实现中创建的局部变量也是GC Root
            OuterClass outerClass = new OuterClass(innerObject, "name thread");
            doSleep();
            System.out.println(outerClass);
        }).start();
        doSleep();
    }

    // 两个对象各自拥有字节的实例字段(引用不同的对象)
    // ObjectsA对象的删除不影响ObjectsB对象字段的引用对象
    public void testMultiObjects() {
        GcRootsObject gcRootDemo1 = new GcRootsObject();
        GcRootsObject gcRootDemo2 = new GcRootsObject();

        doSleep();
        gcRootDemo1 = null;
    }

    private void doSleep() {
        try {
            Thread.sleep(600000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}