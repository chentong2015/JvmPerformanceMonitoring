
// 设置修改额外的模块配置
// --add-exports=java.base/jdk.internal.vm.annotation=ALL-UNNAMED
import jdk.internal.vm.annotation.ReservedStackAccess;

// TODO. 注明可能会线程栈溢出的方法
// - 给JVM提示(JVM可能忽略该注解)来分配额外的栈空间 !!
// - 预留部分线程栈，预留区域在实际产生栈溢出时能够被访问
public class DemoStackReserved {

    private static int depth = 0;

    public static void main(String[] args) {
        System.out.println("\n DEPTH USING RESERVED STACK ");
        determineDepthWithReservedStack();
    }

    @ReservedStackAccess
    private static void determineDepthWithReservedStack() {
        depth = 0;
        try {
            recurseToDetermineMaxDepth();
        } catch (StackOverflowError err) {
            System.out.println("Get StackOverflowError !");
        }
        System.out.printf("Depth: %d%n", depth);
    }

    private static void recurseToDetermineMaxDepth() {
        depth++;
        recurseToDetermineMaxDepth();
    }
}
