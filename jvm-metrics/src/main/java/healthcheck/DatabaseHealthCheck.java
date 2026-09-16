package healthcheck;

import io.dropwizard.metrics5.health.HealthCheck;

// TODO. 自定义HealthCheck的检测指标
public class DatabaseHealthCheck implements HealthCheck {

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