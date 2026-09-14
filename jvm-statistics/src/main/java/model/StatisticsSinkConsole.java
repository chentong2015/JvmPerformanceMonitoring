package model;

// 测试输出收集到的信息到控制台
public class StatisticsSinkConsole implements StatisticsSink<Object> {

    @Override
    public StatisticsSink<Object> beginSection(String name) {
        return null;
    }

    @Override
    public StatisticsSink<Object> endSection() {
        return null;
    }

    @Override
    public StatisticsSink<Object> addDuration(String property, long timeInMilli) {
        return null;
    }

    @Override
    public StatisticsSink<Object> addSize(String property, long sizeInBytes) {
        return null;
    }

    @Override
    public StatisticsSink<Object> addPercentage(String property, float percent) {
        System.out.println("Property = " + property + ", percent = " + percent);
        return null;
    }

    @Override
    public StatisticsSink<Object> add(String property, String value) {
        System.out.println("Property = " + property + ", value = " + value);
        return null;
    }

    @Override
    public StatisticsSink<Object> add(String property, int value) {
        System.out.println("Property = " + property + ", value = " + value);
        return null;
    }

    @Override
    public StatisticsSink<Object> add(String property, long value) {
        System.out.println("Property = " + property + ", value = " + value);
        return null;
    }

    @Override
    public Object flush() {
        return null;
    }
}
