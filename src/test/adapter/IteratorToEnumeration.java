package test.adapter;

import java.util.Enumeration;
import java.util.Iterator;

public class IteratorToEnumeration implements Enumeration<String> {
    private Iterator<String> iter;

    public IteratorToEnumeration(Iterator<String> iter) {
        this.iter = iter;
    }

    @Override
    public boolean hasMoreElements() {
        return iter.hasNext();
    }

    @Override
    public String nextElement() {
        return iter.next();
    }
}
