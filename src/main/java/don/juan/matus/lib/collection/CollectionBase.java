package don.juan.matus.lib.collection;

import don.juan.matus.lib.collection.sorted.NullIterator;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Iterator;

public abstract class CollectionBase<T> extends AbstractSet<T> implements CollectionBaseInterface<T>, Cloneable, java.io.Serializable {

    protected Long size = 0L;

    @Override
    public int size() {
        return size.intValue();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new NullIterator<>();
    }

    @NotNull
    @Override
    public Iterator<T> iterator(T theObject) {
        return new NullIterator<>();
    }

    @NotNull
    @Override
    public Iterator<T> descendingIterator() {
        return new NullIterator<>();
    }

    @NotNull
    @Override
    public Iterator<T> descendingIterator(T theObject) {
        return new NullIterator<>();
    }

}
