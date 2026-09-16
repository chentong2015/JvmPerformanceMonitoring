package healthcheck;

public class Connection {

    public boolean ping(int value) {
        return value > 100;
    }
}
