import java.util.ArrayList;
import java.util.List;

public class DemoMemoryOOM {

    // 配饰VM Options: -Xmx1G -XX:+HeapDumpOnOutOfMemoryError
    public static void main(String[] args) {
         ExcelFile excelFile = new ExcelFile(1, "name1");
         for (int index = 0; index < 10000000; index++) {
             excelFile.addCell(new Cell("field1 " + index, "field2 " + index));
         }
    }

    static class ExcelFile {
        private int id;
        private String name;
        List<Cell> cellList;

        public ExcelFile(int id, String name) {
            this.id = id;
            this.name = name;
            this.cellList = new ArrayList<>();
        }

        public void addCell(Cell cell) {
            this.cellList.add(cell);
        }
    }

    static class Cell {
        private String field1;
        private String field2;

        public Cell(String field1, String field2) {
            this.field1 = field1;
            this.field2 = field2;
        }
    }
}