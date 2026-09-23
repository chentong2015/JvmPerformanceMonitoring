
// 获取到栈溢出时的最大调用深度, 可通过-Xss增大线程栈
public class DemoStackOverflow {

    private static int depth = 0;

    public static void main(String[] args) {
        determineDepth();
    }

    private static void determineDepth() {
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
