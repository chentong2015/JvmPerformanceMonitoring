import io.dropwizard.metrics5.health.HealthCheck;
import io.dropwizard.metrics5.health.HealthCheckRegistry;

public class HealthCheckDemo {

    public static void main(String[] args) {
        Connection connection = new Connection();
        HealthCheckRegistry registry = new HealthCheckRegistry();
        registry.register("connection", new DatabaseHealthCheck(connection));

        // 运行HealthCheck指标的检测
        registry.runHealthChecks().forEach((name, result) -> {
            if (result.isHealthy()) {
                System.out.println(name + ": OK");
            } else {
                System.out.println(name + ": FAIL");
                System.out.println(result.getMessage());
            }
        });
    }

    // TODO. 自定义HealthCheck的检测指标
    static class DatabaseHealthCheck implements HealthCheck {
        private final Connection connection;

        public DatabaseHealthCheck(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Result check() throws Exception {
            if (connection.ping(200)) {
                return Result.healthy();
            }
            return Result.unhealthy("Can't ping database");
        }
    }

    static class Connection {
        public boolean ping(int value) {
            return value > 100;
        }
    }
}
