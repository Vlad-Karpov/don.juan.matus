package don.juan.matus.lib.collection.sorted.tree.heap.avl.splay;

import junit.framework.TestCase;

public class AvlSplayHeapTest extends TestCase {

    public void testAvlSplayHeapTst01() {
        AvlSplayHeap<Long> heap = new AvlSplayHeap<>(10);
        heap.add(1L);
        heap.add(2L);
        heap.add(3L);
        heap.add(4L);
        heap.add(5L);
        heap.add(6L);
        heap.add(7L);
        heap.add(8L);
        heap.add(9L);
        heap.add(10L);
        if (!heap.seek(5L, null)) {
            System.out.println(String.format("%s not found!", 5L));
        }
        heap.add(11L);
        if (!heap.seek(1L, null)) {
            System.out.println(String.format("%s not found!", 1L));
        }
        heap.add(12L);
        if (!heap.seek(2L, null)) {
            System.out.println(String.format("%s not found!", 2L));
        }
    }

}
