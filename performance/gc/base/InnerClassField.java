package base;

import java.util.ArrayList;
import java.util.List;

public class InnerClassField {

    private List<String> list;

    public InnerClassField() {
        list = new ArrayList<>();
        for (int  i = 1; i < 1000; i++) {
            list.add("Item:" + i);
        }
    }
}
