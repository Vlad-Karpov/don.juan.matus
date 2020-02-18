package don.juan.matus.lib.collection.sorted;

import don.juan.matus.lib.collection.CollectionBase;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;

public class SortedCollectionBase<T extends Comparable<T>> extends CollectionBase<T> implements NavigableSet<T> {

    @Override
    public int size() {
        return 0;
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

    @Override
    public T lower(T t) {
        return null;
    }

    @Override
    public T floor(T t) {
        return null;
    }

    @Override
    public T ceiling(T t) {
        return null;
    }

    @Override
    public T higher(T t) {
        return null;
    }

    @Override
    public T pollFirst() {
        return null;
    }

    @Override
    public T pollLast() {
        return null;
    }

    @Override
    public NavigableSet<T> descendingSet() {
        return null;
    }

    @Override
    public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableSet<T> headSet(T toElement, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableSet<T> tailSet(T fromElement, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return null;
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        return null;
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return null;
    }

    @Override
    public T first() {
        return null;
    }

    @Override
    public T last() {
        return null;
    }

}
