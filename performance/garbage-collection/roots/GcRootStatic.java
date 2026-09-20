package roots;

import base.InnerClassField;

public class GcRootStatic {

    // TODO. 静态属性自动可作为GC Root
    // 只要这个static字段所在的类仍然可达，并且static字段仍然引用这个对象(没有被修改)，那么该对象通常GC可达(不能回收)
    private static InnerClassField innerClassField = new InnerClassField();

    public static void main(String[] args) throws InterruptedException {
        GcRootStatic gcRootStatic = new GcRootStatic();
        System.out.println(getInnerClassField());
    }

    public static InnerClassField getInnerClassField() {
        return innerClassField;
    }
}
