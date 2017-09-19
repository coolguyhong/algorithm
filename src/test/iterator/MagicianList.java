package test.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MagicianList implements Iterable<String> {
    private List<String> list = new ArrayList<>();

    public void add(String name) {
        list.add(name);
    }
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            int seq = 0;
            @Override
            public boolean hasNext() {
                return list.size() > seq;
            }

            @Override
            public String next() {
                return list.get(seq++);
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
//        return list.iterator();
    }
}
