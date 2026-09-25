package gc_roots;

public class GcRootObject2 {

    // 两个对象各自拥有字节的实例字段(引用不同的对象)
    // ObjectsA对象的删除不影响ObjectsB对象字段的引用对象
    public static void main(String[] args) throws InterruptedException {
        GcRootObject gcRootDemo1 = new GcRootObject();
        GcRootObject gcRootDemo2 = new GcRootObject();

        Thread.sleep(10000);
        gcRootDemo1 = null;
    }
}