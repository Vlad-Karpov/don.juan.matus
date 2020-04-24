package don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage;

import java.io.Serializable;

public interface BTreePageIdInterface<T extends Comparable<T> & Serializable> extends Comparable<BTreePageIdInterface<T>>, Serializable {
    T getId();
    char[] getIdAsCharArray();
    int getIdAsInt();
    int size();
}
