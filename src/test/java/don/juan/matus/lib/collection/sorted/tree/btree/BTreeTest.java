package don.juan.matus.lib.collection.sorted.tree.btree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeKeyValuePair;
import don.juan.matus.lib.collection.sorted.tree.btree.btree.BTree;
import junit.framework.TestCase;

public class BTreeTest extends TestCase {

    public void testBTreeCreate() {
        BTree<Integer, Integer> tree = new BTree<>(20);
        tree.add(new BTreeKeyValuePair<>(1, 1));
    }

}
