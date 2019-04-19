package don.juan.matus.lib.bintree;

import don.juan.matus.lib.bintree.rotatetree.red_black.RedBlackTree;
import junit.framework.TestCase;
import org.apache.commons.collections.list.TreeList;
import don.juan.matus.lib.bintree.rotatetree.avl.AVLBinTree;
import don.juan.matus.lib.bintree.rotatetree.random.RndBinTree;
import don.juan.matus.lib.bintree.rotatetree.waight.BinTreeW;
import don.juan.matus.lib.bintree.tst.TreeMapTst;
import don.juan.matus.lib.bintree.tst.TreeSetTst;

import java.util.*;

/**
 * Просто тест.
 */
public class SimpleTest extends TestCase {

    private static int rng = 100;

    private Map<byte[], byte[]> cache = new HashMap<byte[], byte[]>();

    public void testQQ() {
        byte[] qq = {1, 2, 3};
        byte[] rr = {4, 5, 6};
        byte[] result = cache.get(qq);
        if (result == null) {
            synchronized (cache) {
                result = cache.get(qq);
                if (result == null) {
                    result = rr;
                    cache.put(qq, rr);
                }
            }
        }
        System.out.println(result);
    }

    public void testBinTree() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Long rnd;
        for (int i = 0; i < 100; i++) {
            rnd = Math.round(Math.random() * 10000L);
            btLng.add(rnd);
        }
        //
        btLng.checkStructure(new BinTreeCheckPassEventTest());
        //
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testRndBinTree() {
        RndBinTree<Long> btLng = new RndBinTree<Long>();
        Long rnd;
        for (int i = 0; i < 100; i++) {
            rnd = Math.round(Math.random() * 10000L);
            btLng.add(rnd);
        }
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testTreeMap1() {
        TreeMapTst<Long, Long> tmRndLng = new TreeMapTst<Long, Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 18L, 16L, 13L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            if (i == 15) {
                System.out.println("!");
            }
            tmRndLng.put(rnd[i], rnd[i]);
            /*
            btLng.checkStructure(new AVLBinTree.BinTreePassEvent<AVLBinTree<Long>.BinTreeNodeBF<Long>>() {
                @Override
                public void onPass(AVLBinTree<Long>.BinTreeNodeBF<Long> theObject) {
                    System.out.println("shit = " + theObject);
                }
            });
            */
        }
        Iterator<Long> it = tmRndLng.descendingKeySet().iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testAVLBinTree1() {
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 13L, 18L, 16L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            if (i == 12) {
                System.out.println("!");
            }
            btLng.add(rnd[i]);
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest());
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testBinTreeRemove() {
        ArrayList<Long> ar = new ArrayList<Long>();
        BinTreeBase<Long> btLng = new BinTreeBase<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 13L, 18L, 16L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            if (i == 12) {
                System.out.println("!");
            }
            btLng.add(rnd[i]);
            ar.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator();
        Iterator<Long> arit = ar.iterator();
        Long next;
        while (arit.hasNext()) {
            next = arit.next();
            if (next == 11L) {
                arit.remove();
            }
            System.out.println(" btLng = " + next);
        }
        int i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next == 2) {
                it.remove();
            }
            if (next == 8) {
                it.remove();
            }
            if (next == 19) {
                it.remove();
            }
            if (next == 20) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(9L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(9L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next >= 4L) it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        if (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("remove " + next);
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(1L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(5L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        //btLng.remove(3L);
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        //
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        it.remove();
    }

    public void testAvlBinTreeRemove() {
        ArrayList<Long> ar = new ArrayList<Long>();
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 13L, 18L, 16L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            if (i == 15) {
                System.out.println("!");
            }
            btLng.add(rnd[i]);
            ar.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator();
        Iterator<Long> arit = ar.iterator();
        Long next;
        while (arit.hasNext()) {
            next = arit.next();
            if (next == 11L) {
                arit.remove();
            }
            System.out.println(" btLng = " + next);
        }
        int i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next == 2) {
                it.remove();
                it.remove();
                it.remove();
            }
            if (next == 8) {
                it.remove();
            }
            if (next == 19) {
                it.remove();
            }
            if (next == 20) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(9L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(9L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next == 13L) {
                System.out.println("ququ");
            }
            it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next >= 4L) it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        if (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("remove " + next);
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(1L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator(5L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        //btLng.remove(3L);
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        //
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.iterator();
        it.remove();
    }

    public void testBinTreeRemoveDesc() {
        ArrayList<Long> ar = new ArrayList<Long>();
        BinTreeBase<Long> btLng = new BinTreeBase<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 13L, 18L, 16L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            if (i == 12) {
                System.out.println("!");
            }
            btLng.add(rnd[i]);
            ar.add(rnd[i]);
        }
        Iterator<Long> it = btLng.descendingIterator();
        Iterator<Long> arit = ar.iterator();
        Long next;
        while (arit.hasNext()) {
            next = arit.next();
            if (next == 11L) {
                arit.remove();
            }
            System.out.println(" btLng = " + next);
        }
        int i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next == 2) {
                it.remove();
            }
            if (next == 8) {
                it.remove();
            }
            if (next == 19) {
                it.remove();
            }
            if (next == 20) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator(9L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator(9L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            if (next <= 13L) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator();
        if (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("remove " + next);
        }
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator(18L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator(14L);
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        //btLng.remove(3L);
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            it.remove();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        //
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            next = it.next();
            System.out.println("i = " + i + " btLng = " + next);
            i++;
        }
        it = btLng.descendingIterator();
        it.remove();
    }

    public void testBaseBinTree1() {
        BinTreeBase<Long> btLng = new BinTreeBase<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 13L, 18L, 16L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            if (i == 12) {
                System.out.println("!");
            }
            btLng.add(rnd[i]);
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest());
        BinTreeIterator<Long> it = (BinTreeIterator<Long>) btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("size: " + it.getSize());
        System.out.println("max level: " + it.getMaxLevel());
        System.out.println("tree size: " + btLng.getSize());
        System.out.println("tree max level: " + btLng.getMaxLevel());


    }

    public void testBinTree1() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 18L, 16L, 13L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testTreeList1() {
        TreeList btLng = new TreeList();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 18L, 16L, 13L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testTreeSet1() {
        TreeSet<Long> btLng = new TreeSet<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 18L, 16L, 13L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testAVLBinTree6() {
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest());
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testBinTree6() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testAVLBinTree4() {
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L};
        for (int i = 0; i < 10; i++) {
            btLng.add(rnd[i]);
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest());
        for (int j = 0; j < 10; j++) {
            Iterator<Long> it = btLng.iterator(rnd[j]);
            int i = 1;
            while (it.hasNext()) {
                System.out.println("i = " + i + " btLng = " + it.next());
                i++;
            }
            System.out.println("----------------------");
        }
        Iterator<Long> it = btLng.iterator(11L);
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
        it = btLng.iterator(-11L);
        i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
    }

    public void testBinTree4() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L};
        for (int i = 0; i < 10; i++) {
            btLng.add(rnd[i]);
        }
        for (int j = 0; j < 10; j++) {
            Iterator<Long> it = btLng.iterator(rnd[j]);
            int i = 1;
            while (it.hasNext()) {
                System.out.println("i = " + i + " btLng = " + it.next());
                i++;
            }
            System.out.println("----------------------");
        }
        Iterator<Long> it = btLng.iterator(11L);
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
        it = btLng.iterator(-11L);
        i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
    }

    public void testAVLBinTree5() {
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        Long[] rnd = new Long[]{2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 8L, 8L, 8L, 8L, 5L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 3L, 3L, 10L, 20L, 20L, 30L, 30L, 40L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest());
        Iterator<Long> it = btLng.iterator(15L);
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
    }

    public void testBinTree5() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Long[] rnd = new Long[]{2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 8L, 8L, 8L, 8L, 5L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 3L, 3L, 10L, 20L, 20L, 30L, 30L, 40L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
        }
        Iterator<Long> it = btLng.iterator(15L);
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
    }

    public void testAVLBinTree2() {
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        btLng.setThreshold((byte)4);
        Long rnd;
        for (int i = 0; i < 1000; i++) {
            rnd = Math.round(Math.random() * 10000L);
            btLng.add(rnd);
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest());
        btLng.passStraight(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                System.out.println("i = " + i + " btLng = " + theObject);
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }
        });
        System.out.println("----------------------");
        btLng.passBack(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                System.out.println("i = " + i + " btLng = " + theObject);
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }
        });
        System.out.println("----------------------");
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            if (i % 2 == 0) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            if (i % 2 == 0) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        System.out.println("----------------------");
        it = btLng.descendingIterator();
        i = 1;
        while (it.hasNext()) {
            if (i % 2 == 0) {
                it.remove();
            }
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
        btLng.checkStructure(new BinTreeCheckPassEventTest());
    }

    public void testBinTree2() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Long rnd;
        for (int i = 0; i < 100; i++) {
            rnd = Math.round(Math.random() * 10000L);
            btLng.add(rnd);
        }
        btLng.passStraight(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                System.out.println("i = " + i + " btLng = " + theObject.getObjectNode());
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }
            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }
        });
        System.out.println("----------------------");
        btLng.passBack(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                System.out.println("i = " + i + " btLng = " + theObject.getObjectNode());
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }
            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }
        });

        System.out.println("----------------------");
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it.next());
            i++;
        }
    }

    public void testAVLBinTree3() {
        Calendar cBegin = Calendar.getInstance();
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        btLng.setThreshold((byte)4);
        Long rnd;
        for (int i = 0; i < 10000000; i++) {
            rnd = Math.round(Math.random() * 10000L);
//            System.out.print(", " + rnd);
            btLng.add(rnd);
            if (i % 100000 == 0) {
                System.out.println("i = " + i);
            }
        }
        System.out.println("---------------------------------------------------");
        btLng.checkStructure(new BinTreeCheckPassEventTest());
        System.out.println("---------------------------------------------------");
        System.out.println("MaxLevel = " + btLng.getMaxLevel());
        System.out.println("RotateCount = " + btLng.getRotateCount());
        System.out.println("---------------------------------------------------");
        final Iterator<Long> it = btLng.iterator();
        btLng.passStraight(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                if (it.hasNext()) {
                    assertEquals(theObject.getObjectNode(), it.next());
                }
                if (i % 100000 == 0) {
                    System.out.println("i = " + i);
                }
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }
        });
        Calendar cEnd = Calendar.getInstance();
        System.out.println("---------------------------------------------------");
        System.out.println("time = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
    }

    public void testBinTree3() {
        Calendar cBegin = Calendar.getInstance();
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Long rnd;
        for (int i = 0; i < 10000000; i++) {
            rnd = Math.round(Math.random() * 10000L);
            btLng.add(rnd);
            if (i % 100000 == 0) {
                System.out.println("i = " + i);
            }
        }
        System.out.println("---------------------------------------------------");
        System.out.println("maxLevel = " + btLng.getMaxLevel());
        System.out.println("RotateCount = " + btLng.getRotateCount());
        System.out.println("---------------------------------------------------");
        final Iterator<Long> it = btLng.iterator();
        btLng.passStraight(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                if (it.hasNext()) {
                    assertEquals(theObject.getObjectNode(), it.next());
                }
                if (i % 100000 == 0) {
                    System.out.println("i = " + i);
                }
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }
            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }
        });
        Calendar cEnd = Calendar.getInstance();
        System.out.println("---------------------------------------------------");
        System.out.println("time = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
    }

    public void testBinTree7() {
        Calendar cBegin = Calendar.getInstance();
        TreeMapTst<Long, Long> tmRndLng = new TreeMapTst<Long, Long>();
        List<Long> rList = new ArrayList<Long>();
        Long rnd;
        while (tmRndLng.size() < 10000000) {
            rnd = Math.round(Math.random() * 10000000L);
            if (tmRndLng.put(rnd, rnd) == null) rList.add(rnd);
        }
        tmRndLng = null;
        System.gc();
        //
        int j;
        Iterator<Long> it;
        //
        Calendar cEnd = Calendar.getInstance();
        System.out.println("time 1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        System.out.println("BinTreeW");
        cBegin = Calendar.getInstance();
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        for (int i = 0; i < 10000000; i++) {
            rnd = rList.get(i);
            btLng.add(rnd);
        }
        System.out.println("maxLevel = " + btLng.getMaxLevel());
        System.out.println("RotateCount = " + btLng.getRotateCount());
        cEnd = Calendar.getInstance();
        System.out.println("time 2.1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        cBegin = Calendar.getInstance();
        it = btLng.iterator();
        j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + btLng.getSize());
        //
        cBegin = Calendar.getInstance();
        btLng.checkStructure(new BinTreeCheckPassEventTest());
        cEnd = Calendar.getInstance();
        System.out.println("time 4.1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + btLng.getSize());
        //
        btLng = null;
        System.gc();
        //
        System.out.println("TreeMap");
        cBegin = Calendar.getInstance();
        TreeMapTst<Long, Long> tmLng = new TreeMapTst<Long, Long>();
        for (int i = 0; i < 10000000; i++) {
            rnd = rList.get(i);
            tmLng.put(rnd, rnd);
        }
        System.out.println("maxLevel = " + tmLng.maxLevel);
        System.out.println("RotateCount = " + tmLng.rotateCount);
        cEnd = Calendar.getInstance();
        System.out.println("time 2.2 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        cBegin = Calendar.getInstance();
        it = tmLng.descendingKeySet().iterator();
        j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.2 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + tmLng.size());
        tmLng = null;
        System.gc();
        //
        System.out.println("AVLBinTree");
        cBegin = Calendar.getInstance();
        //AVLBinTree<Long> avlLng = new AVLBinTree<Long>();
        RedBlackTree<Long> avlLng = new RedBlackTree<Long>();
        //avlLng.setThreshold((byte)4);
        for (int i = 0; i < 10000000; i++) {
            rnd = rList.get(i);
            avlLng.add(rnd);
        }
        System.out.println("maxLevel = " + avlLng.getMaxLevel());
        System.out.println("RotateCount = " + avlLng.getRotateCount());
        cEnd = Calendar.getInstance();
        System.out.println("time 2.3 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        cBegin = Calendar.getInstance();
        it = avlLng.iterator();
        j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.3 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + avlLng.getSize());
        //
        cBegin = Calendar.getInstance();
        avlLng.checkStructure(new BinTreeCheckPassEventTest());
        cEnd = Calendar.getInstance();
        System.out.println("time 4.3 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + avlLng.getSize());
        //
        avlLng = null;
        System.gc();
        //
        System.out.println("RndBinTree");
        cBegin = Calendar.getInstance();
        RndBinTree<Long> rndBLng = new RndBinTree<Long>();
        for (int i = 0; i < 10000000; i++) {
            rnd = rList.get(i);
            rndBLng.add(rnd);
        }
        System.out.println("maxLevel = " + rndBLng.getMaxLevel());
        System.out.println("RotateCount = " + rndBLng.getRotateCount());
        cEnd = Calendar.getInstance();
        System.out.println("time 2.4 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        cBegin = Calendar.getInstance();
        it = rndBLng.iterator();
        j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.4 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + rndBLng.getSize());
        //
        cBegin = Calendar.getInstance();
        rndBLng.checkStructure(new BinTreeCheckPassEventTest());
        cEnd = Calendar.getInstance();
        System.out.println("time 4.4 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + rndBLng.getSize());
        //
        rndBLng = null;
        System.gc();

        //
        System.out.println("BinTreeBase");
        cBegin = Calendar.getInstance();
        BinTreeBase<Long> baseBLng = new BinTreeBase<Long>();
        for (int i = 0; i < 10000000; i++) {
            rnd = rList.get(i);
            baseBLng.add(rnd);
        }
        System.out.println("maxLevel = " + baseBLng.getMaxLevel());
        cEnd = Calendar.getInstance();
        System.out.println("time 2.5 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        cBegin = Calendar.getInstance();
        it = baseBLng.iterator();
        j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.5 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + baseBLng.getSize());
        //
        cBegin = Calendar.getInstance();
        baseBLng.checkStructure(new BinTreeCheckPassEventTest());
        cEnd = Calendar.getInstance();
        System.out.println("time 4.5 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + baseBLng.getSize());
        //
        baseBLng = null;
        System.gc();
    }

    public void testBinTreeBase() {
        int j;
        Iterator<Long> it;
        TreeMapTst<Long, Long> tmRndLng = new TreeMapTst<Long, Long>();
        List<Long> rList = new ArrayList<Long>();
        Long rnd;
        while (tmRndLng.size() < 10000000) {
            rnd = Math.round(Math.random() * 10000000L);
            if (tmRndLng.put(rnd, rnd) == null) rList.add(rnd);
        }
        tmRndLng = null;
        System.gc();
        //
        System.out.println("BinTreeBase");
        Calendar cBegin = Calendar.getInstance();
        BinTreeBase<Long> baseBLng = new BinTreeBase<Long>();
        for (int i = 0; i < 10000000; i++) {
            rnd = rList.get(i);
            baseBLng.add(rnd);
        }
        System.out.println("maxLevel = " + baseBLng.getMaxLevel());
        Calendar cEnd = Calendar.getInstance();
        System.out.println("time 2.4 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        cBegin = Calendar.getInstance();
        it = baseBLng.iterator();
        j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.4 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + baseBLng.getSize());
        System.gc();
        //
        cBegin = Calendar.getInstance();
        it = baseBLng.iterator();
        j = 0;
        while (it.hasNext()) {
            it.next();
            if (j % 3 == 0) {
                System.out.println("j="+ j);
                it.remove();
            }
            j++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.5 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + baseBLng.getSize());
        System.gc();
        //
        cBegin = Calendar.getInstance();
        it = baseBLng.iterator();
        j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.6 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + j + ") " + baseBLng.getSize());
        baseBLng = null;
        System.gc();
    }

    public void testBinTree77() {
        Calendar cBegin = Calendar.getInstance();
        TreeMapTst<Long, Long> tmRndLng = new TreeMapTst<Long, Long>();
        List<Long> rList = new ArrayList<Long>();
        Long rnd;
        while (tmRndLng.size() < 10000000) {
            rnd = Math.round(Math.random() * 10000000L);
            if (tmRndLng.put(rnd, rnd) == null) rList.add(rnd);
        }
        Calendar cEnd = Calendar.getInstance();
        System.out.println("time 1.1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        System.out.println("TreeMap");
        cBegin = Calendar.getInstance();
        TreeMapTst<Long, Long> tmLng = new TreeMapTst<Long, Long>();
        for (int i = 0; i < 10000000; i++) {
            rnd = rList.get(i);
            tmLng.put(rnd, rnd);
        }
        System.out.println("maxLevel = " + tmLng.maxLevel);
        System.out.println("RotateCount = " + tmLng.rotateCount);
        cEnd = Calendar.getInstance();
        System.out.println("time 2.1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        System.out.println("RndBinTree");
        cBegin = Calendar.getInstance();
        RndBinTree<Long> avlLng = new RndBinTree<Long>();
        for (int i = 0; i < 10000000; i++) {
            rnd = rList.get(i);
            avlLng.add(rnd);
        }
        System.out.println("maxLevel = " + avlLng.getMaxLevel());
        System.out.println("RotateCount = " + avlLng.getRotateCount());
        cEnd = Calendar.getInstance();
        System.out.println("time 3.1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        int i;
        Iterator<Long> it;
        //
        /*
        cBegin = Calendar.getInstance();
        Iterator<Long> it = btLng.iterator();
        i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + i + ") " + btLng.size());
        */
        //
        cBegin = Calendar.getInstance();
        it = tmLng.descendingKeySet().iterator();
        i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.2 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + i + ") " + tmLng.size());
        //
        cBegin = Calendar.getInstance();
        it = avlLng.iterator();
        i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.3 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + i + ") " + avlLng.getSize());
    }

    public void testBinTree8() {
        Calendar cBegin = Calendar.getInstance();
        Long rnd;
        List<Long> rList = new ArrayList<Long>();
        for (int i = 0; i < 10000000; i++) {
            rnd = Math.round(Math.random() * 10000000L);
            rList.add(rnd);
        }
        Calendar cEnd = Calendar.getInstance();
        System.out.println("time 1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        cBegin = Calendar.getInstance();
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        for (int i = 0; i < 10000000; i++) {
            rnd = rList.get(i);
            btLng.add(rnd);
        }
        System.out.println("maxLevel = " + btLng.getMaxLevel());
        cEnd = Calendar.getInstance();
        System.out.println("time 2.1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        cBegin = Calendar.getInstance();
        TreeSetTst<Long> tmLng = new TreeSetTst<Long>();
        for (int i = 0; i < 10000000; i++) {
            rnd = rList.get(i);
            tmLng.add(rnd);
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 2.2 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        int i;
        //
        cBegin = Calendar.getInstance();
        Iterator<Long> it = btLng.iterator();
        i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + i + ") " + btLng.getSize());
        //
        cBegin = Calendar.getInstance();
        it = tmLng.iterator();
        i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.2 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + i + ") " + tmLng.size());
    }

    public void testBinTree9() {
        Calendar cBegin = Calendar.getInstance();
        Long rnd;
        TreeSetTst<Long> rList1 = new TreeSetTst<Long>();
        TreeSetTst<Long> rList2 = new TreeSetTst<Long>();
        for (int i = 0; i < 15000000; i++) {
            rnd = Math.round(Math.random() * 15000000L);
            rList1.add(rnd);
            rList2.add(rnd);
        }
        Calendar cEnd = Calendar.getInstance();
        System.out.println("time 1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        cBegin = Calendar.getInstance();
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Iterator<Long> itl = rList1.iterator();
        while (itl.hasNext()) {
            btLng.add(itl.next());
        }
        System.out.println("maxLevel = " + btLng.getMaxLevel() + "; rotateCount = " + btLng.getRotateCount());
        cEnd = Calendar.getInstance();
        System.out.println("time 2.1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        cBegin = Calendar.getInstance();
        TreeSetTst tmLng = new TreeSetTst();
        Iterator<Long> it2 = rList2.iterator();
        while (it2.hasNext()) {
            tmLng.add(it2.next());
        }
        System.out.println("maxLevel = " + tmLng.getMaxLevel() + "; rotateCount = " + tmLng.getRotateCount());
        cEnd = Calendar.getInstance();
        System.out.println("time 2.2 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()));
        //
        int i;
        //
        cBegin = Calendar.getInstance();
        Iterator<Long> it = btLng.iterator();
        i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.1 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + i + ") " + btLng.getSize());
        //
        cBegin = Calendar.getInstance();
        it = tmLng.iterator();
        i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        cEnd = Calendar.getInstance();
        System.out.println("time 3.2 = " + (cEnd.getTimeInMillis() - cBegin.getTimeInMillis()) + " (" + i + ") " + tmLng.size());
    }

    public void testBinTreeEmpty() {
        BinTreeW<Long> btLng = new BinTreeW<Long>();
        Iterator<Long> it1 = btLng.iterator();
        int i = 1;
        while (it1.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it1.next());
            i++;
        }
        Iterator<Long> it2 = btLng.iterator(10L);
        i = 1;
        while (it2.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it2.next());
            i++;
        }
        final Iterator<Long> it = btLng.iterator();
        btLng.passStraight(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                assertEquals(theObject.getObjectNode(), it.next());
                if (i % 100000 == 0) {
                    System.out.println("i = " + i);
                }
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }
            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }
        });
        btLng.add(10L);
        Iterator<Long> it3 = btLng.iterator();
        i = 1;
        while (it3.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it3.next());
            i++;
        }
        Iterator<Long> it4 = btLng.iterator(10L);
        i = 1;
        while (it4.hasNext()) {
            System.out.println("i = " + i + " btLng = " + it4.next());
            i++;
        }
        final Iterator<Long> it5 = btLng.iterator();
        btLng.passStraight(new BinTreePassEvent<BinTreeNodeInterface<Long>>() {
            int i = 1;

            public void onPass(BinTreeNodeInterface<Long> theObject) {
                assertEquals(theObject.getObjectNode(), it5.next());
                if (i % 100000 == 0) {
                    System.out.println("i = " + i);
                }
                i++;
            }

            public Long incLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }
            public Long decLevel(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decLeft(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            public Long decRight(BinTreeNodeInterface<Long> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<Long> theObject) {

            }
        });
    }

    public static class BinTreeCheckPassEventTest implements BinTreeCheckPassEvent<Long> {

        private String errorMessage;

        @Override
        public void onPass(
                BinTreeIterator<Long> leftIterator,
                BinTreeIterator<Long> rightIterator,
                BinTreeNodeInterface<Long> currentNode,
                BinTreeNodeInterface<Long> previousNode) {
            System.out.println(errorMessage
                    + "\r\n Current node: " + currentNode
                    + "\r\n Previous node: " + previousNode
                    + "\r\n Left iterator: " + leftIterator
                    + "\r\n Right iterator: " + rightIterator);
        }

        @Override
        public void setErrorMessage(String errMsg) {
            errorMessage = errMsg;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }

}
