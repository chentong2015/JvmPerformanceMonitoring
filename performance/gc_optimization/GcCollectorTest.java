import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

// TODO. 增大Heap空间能明显减少GC次数, 有利于减少总的Pause时间
// 但程序总性能时间可能受限其它因素: 系统IO频繁, JDBC, 数据持久化, 网络通讯
public class GcCollectorTest {

    // 测试VM Options:
    // -Xmx128M -XX:+UseG1GC -XX:ParallelGCThreads=20 -XX:MaxGCPauseMillis=10 -XX:+PrintGCDetails
    // -Xmx128M -XX:+UseShenandoahGC -XX:+PrintGCDetails
    public static void main(String[] args) throws IOException {
        String filepathOutput = "C:\\Users\\chent5\\Downloads\\Batch\\wc-premium-full\\result.txt";
        Files.deleteIfExists(Path.of(filepathOutput));

        String filepathInput = "C:\\Users\\chent5\\Downloads\\Batch\\wc-premium-full\\FMM_RECORD_CHECKSUM_202609230947.csv";
        String fileInput = FileSystems.getDefault().getPath(filepathInput).toString();

        AtomicInteger countLines = new AtomicInteger();
        long startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get(fileInput));
             FileWriter fileWriter = new FileWriter(filepathOutput)) {
             lines.forEach(line -> {
                 try {
                     countLines.getAndIncrement();
                     fileWriter.write("line " + line);
                     fileWriter.append("add new line \n");

                     // if (countLines.get() % 1000000 == 0) {
                     //     System.out.println("Sleep 1 second");
                     //     Thread.sleep(1000);
                     // }
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }
             });
        }
        System.out.println(System.currentTimeMillis() - startTime);
        // G1 2.4s -> 3.4s -> 9178 / 10008
        // Shenandoah 2.5s -> 3.6s -> 14902 17613
        // ZGC 3.2s 3S
    }
}