package don.juan.matus.lib.collection;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;

public interface CollectionBaseInterface<T> extends Collection<T> {

    Iterator<T> iterator();

    @NotNull
    Iterator<T> iterator(T theObject);

    Iterator<T> descendingIterator();

    @NotNull
    Iterator<T> descendingIterator(T theObject);

    boolean add(T theObject);

    boolean remove(Object theObject);

}
