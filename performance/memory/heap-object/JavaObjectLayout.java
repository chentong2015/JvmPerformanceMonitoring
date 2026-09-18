// import org.openjdk.jol.info.ClassLayout;
// import org.openjdk.jol.vm.VM;

//  Java Object Layout Core 输出对象在内存的存储
//  <dependency>
//      <groupId>org.openjdk.jol</groupId>
//      <artifactId>jol-core</artifactId>
//      <version>0.17</version>
//      <scope>compile</scope>
//  </dependency>
public class JavaObjectLayout {

    public static void main(String[] args) {
        // System.out.println(VM.current().details());

        // runtime_memory.object_layout.JavaObjectLayout$StorageClass object internals:
        // OFF  SZ                                            TYPE DESCRIPTION              VALUE
        //  0   8                                                 (object header: mark)     N/A   对象头
        //  8   4                                                 (object header: class)    N/A   类型指针
        // 12   4                                             int StorageClass.id           N/A   属性
        // 16   4                                java.lang.String StorageClass.name         N/A   属性
        // 20   4   runtime_memory.object_layout.JavaObjectLayout StorageClass.this$0       N/A
        // Instance size: 24 bytes
        // Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        // System.out.println(ClassLayout.parseClass(StorageClass.class).toPrintable());

        // runtime_memory.object_layout.JavaObjectLayout$StorageClass object internals:
        // OFF  SZ               TYPE DESCRIPTION              VALUE
        //  0   8                    (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
        //  8   4                    (object header: class)    0x0101bac0
        // 12   4                int StorageClass.id           1
        // 16   4   java.lang.String StorageClass.name         (object)
        // 20   4                    (object alignment gap)
        // Instance size: 24 bytes
        // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        StorageClass storageClass = new StorageClass(1, "execution_engine/garbage_collector/test");
        // System.out.println(ClassLayout.parseInstance(storageClass).toPrintable());

        // OFF  SZ   TYPE DESCRIPTION              VALUE
        //  0   8        (object header: mark)     0x0000000000000001 (non-biasable; age: 0) 对象头
        //  8   4        (object header: class)    0x00006b18 类型指针
        // 12   4        (array length)            3          数组的长度
        // 16  12    int [I.<elements>             N/A        数组包含元素
        // 28   4        (object alignment gap)               对齐填充
        // Instance size: 32 bytes                            实际总大小
        // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        int[] arr = {1, 2, 3};
        // System.out.println(ClassLayout.parseInstance(arr).toPrintable());
    }

    static class StorageClass {
        private int id;
        private String name;

        public StorageClass(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void test() {
            System.out.println("test method");
        }
    }
}
