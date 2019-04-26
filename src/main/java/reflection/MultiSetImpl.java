package reflection;

import java.util.AbstractCollection;
import java.util.Iterator;

public class MultiSetImpl<E> extends AbstractCollection<E> implements MultiSet<E> {

    static boolean f;
    public static boolean t;


    private Object[] store;

    final int i = 0;

    int j;

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }
}
