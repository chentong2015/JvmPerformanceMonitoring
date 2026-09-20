import base.InnerClassField;
import base.OuterClass;

public class GcRootObject {

    // TODO. 这个实例字段本身不是GC Root
    // 当InnerClassField对象变成“从任何GC Root都不可达”时，才具有被GC自动回收的资格 !!
    private final InnerClassField innerObject = new InnerClassField();

    public static void main(String[] args) {
        // TODO. 这里创建的线程栈局部变量才是GC Root
        // 只要该局部对象可达(存在)，则它属性所引用的对象就不能被GC !!
        GcRootObject gcRootDemo = new GcRootObject();
        gcRootDemo.testGcCollectObject();

        // 理论上: 当局部变量置空时，InnerClassField对象也不可达
        gcRootDemo = null;

        // 理论上: 重新创建对象，InnerClassField对象也会被重新创建
        gcRootDemo = new GcRootObject();

        // 当方法执行结束返回后，不可达的InnerClassField对象自动被回收
    }

    public void testGcCollectObject() {
        // 在子线程中引用实例字段所引用的对象
        new Thread(() -> {
            // TODO. 在run()方法的实现中创建的局部变量也是GC Root
            OuterClass outerClass = new OuterClass(innerObject, "name thread");
            System.out.println(outerClass);
        }).start();
    }
}