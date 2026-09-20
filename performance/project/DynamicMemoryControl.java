public class DynamicMemoryControl {

    // 安全因子: 限制直接使用全部的内存
    private static final double SAFETY_FACTOR = 0.6;
    private static final int MAX_TASK_SIZE = 500;

    // 根据最大内存限制: 控制最大允许的Task任务数量, 避免加载过多对象造成OOM
    public int getNumAllowedTasks(double objectSizeInMB, int numObjects) {
        if (objectSizeInMB <= 0 || numObjects <= 0) {
            throw new RuntimeException("objectSize and numObjects should be greater than 0");
        }

        long maxMemoryUsed = Runtime.getRuntime().maxMemory() / (1024 * 1024); // in MB
        double objectSizePerTask = objectSizeInMB * numObjects;
        if (objectSizePerTask > maxMemoryUsed * SAFETY_FACTOR) {
            throw new RuntimeException("Too large object size per task");
        }

        int numAllowedTask = (int) (maxMemoryUsed * SAFETY_FACTOR / objectSizePerTask);
        if (numAllowedTask > MAX_TASK_SIZE) {
            return MAX_TASK_SIZE;
        } else {
            return Math.max(numAllowedTask, 1);
        }
    }
}