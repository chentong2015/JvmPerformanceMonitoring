package healthcheck;

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
}