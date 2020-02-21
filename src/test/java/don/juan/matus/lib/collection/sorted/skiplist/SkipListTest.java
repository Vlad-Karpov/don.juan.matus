package don.juan.matus.lib.collection.sorted.skiplist;

import junit.framework.TestCase;

import java.util.Iterator;

public class SkipListTest extends TestCase {

    public void testSkipList01() {

        SkipList<Long> sl = new SkipList<>();

        sl.add(10L);
        sl.add(20L);
        sl.add(30L);
        sl.add(40L);
        sl.add(50L);

        sl.add(15L);
        sl.add(25L);
        sl.add(35L);
        sl.add(45L);
        sl.add(55L);

        Iterator<Long> iter = sl.iterator();
        iter.forEachRemaining(System.out::println);

        sl.showMustGoOn();

    }

}
