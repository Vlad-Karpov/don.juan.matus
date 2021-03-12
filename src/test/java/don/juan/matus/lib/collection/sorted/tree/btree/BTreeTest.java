package don.juan.matus.lib.collection.sorted.tree.btree;

import don.juan.matus.lib.collection.KeyValuePair;
import don.juan.matus.lib.collection.sorted.tree.btree.bptree.BPlusTree;
import don.juan.matus.lib.collection.sorted.tree.btree.btree.BTree;
import junit.framework.TestCase;

public class BTreeTest extends TestCase {

    public void testBTreeCreate() throws IllegalAccessException, InstantiationException {
        BTree<Integer, Integer> tree = new BTree<>((short) 20, Integer.class, Integer.class);
        tree.add(new KeyValuePair<>(1, 1));
    }

    public void testBPlusTreeCreate() throws IllegalAccessException, InstantiationException {
        BPlusTree<Integer, Integer> tree = new BPlusTree<>((short) 20, Integer.class, Integer.class);
        tree.add(new KeyValuePair<>(1, 1));
    }


}
