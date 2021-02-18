package don.juan.matus.lib.collection.sorted.tree.bintree;

import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.splay.SplayTree;
import don.juan.matus.lib.collection.sorted.tree.heap.avl.splay.AvlSplayHeap;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by karpovvv on 29.01.16.
 */
public class AVLTreeTest extends TestCase {


/*

2, 14, 16, 7, 10, 1, 19, 15, 19, 6, 20, 8, 10, 13, 16, 9, 18, 14, 4, 2

del 18
del 16
del 16
del 4
del 19
del 20
del 14
del 15
 */


    public static final long CNT = 36L;

    public static void main(String[] args) {
        //AVLBinTree<Long> btLng = new AVLBinTree<>();
        //btLng.setThreshold((byte)5);
        //RedBlackTree<Long> btLng = new RedBlackTree<>();
        //BinTreeW<Long> btLng = new BinTreeW<>();
        //RndBinTree<Long> btLng = new RndBinTree<>();
        //ScapegoatTree<Long> btLng = new ScapegoatTree<>();
        //CartesianBinTree<Long> btLng = new CartesianBinTree<>();
        //RandomMergeBinTree<Long> btLng = new RandomMergeBinTree<>();
        //RandomRotateBinTree<Long> btLng = new RandomRotateBinTree<>();
        //SplayTree<Long> btLng = new SplayTree<>();
        //AATree<Long> btLng = new AATree<>();
        AvlSplayHeap<Long> btLng = new AvlSplayHeap<>(50);
        //BinTreeCheckPassEventTest check = new BinTreeCheckPassEventTest();
        //Long[] rnd = new Long[]{407L,3879L,958L,3899L,4262L,3295L,8258L,8214L,875L,4581L,9733L,164L,2942L,8446L,5083L,7494L,9901L,8354L,3326L,8427L,1407L,4104L,3489L,5555L};
        Long[] rnd = new Long[]{9205L,8378L,7395L,6283L,1970L,4425L,5537L,5573L,3508L,5060L,2979L,921L,8387L,5293L,8488L,3405L,4196L,5580L,8660L,1036L,1278L,4456L,4500L,4799L,3347L,3114L, 1L, 2L, 3L, 4L};
        //Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L}; //, 3L, 4L, 7L, 6L, 10L}; //, 15L, 12L, 14L, 18L, 16L, 13L, 20L, 17L, 19L, 11L};
        //Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 18L, 16L, 13L, 20L, 17L, 19L, 11L, 50L};
        //Long[] rnd = new Long[]{50L, 10L, 70L, 100L, 20L, 60L, 50L, 85L, 11L, 1L, 1001L, 101L, 45L, 23L, 31L, 25L, 64L, 67L, 3L, 99L, 41L, 2L, 22L, 79L};

        //Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L};
        //Long[] rnd = new Long[]{};
        //Long[] rnd = new Long[]{3693L, 5312L, 9701L, 8279L, 7627L, 5996L, 8152L, 9863L, 1212L, 2628L, 9159L, 8584L, 3876L, 2173L, 8627L, 6457L, 6034L, 6967L, 9026L, 8530L, 548L, 7564L, 6746L/*, 8752L*/};
        //Long[] rnd = new Long[]{4392624L, 2881184L, 9539741L, 3081582L, 1611473L, 5257245L, 7687060L, 662051L, 9912096L, 9489267L, 143672L, 3062267L, 9688194L, 8924794L, 4746615L, 2115122L, 5390092L, 6001589L, 4045300L, 2239590L, 6082371L, 5207546L, 5335956L, 9123370L, 6573253L, 9771302L, 3030685L, 125894L, 3307948L, 4295786L, 30L, 8925390L};
        btLng.addAll(Arrays.asList(rnd));

//        for (long i = 0; i < CNT; i++) {
//            Long rnd = Math.round(Math.random() * CNT);
//            System.out.print(rnd + "L, ");
//            btLng.add(rnd);
////            btLng.add(i + 1);
//            btLng.checkStructure(check);
//        }

//        btLng.remove(2L);
//        btLng.remove(8L);
//        btLng.remove(19L);
//        btLng.remove(20L);
        //btLng.remove(9L);
        //btLng.remove(10L);
        //btLng.remove(11L);
        BinTreeGraph<Long> graph = new BinTreeGraph<Long>(btLng);
        graph.letsGo();
    }


    @Test
    public void testAVLBinTree1() {
        AVLBinTree<Long> btLng = new AVLBinTree<Long>();
        //btLng.setThreshold((byte)2);
        Long[] rnd = new Long[]{2L, 8L, 5L, 1L, 9L, 3L, 4L, 7L, 6L, 10L, 15L, 12L, 14L, 18L, 16L, 13L, 20L, 17L, 19L, 11L};
        for (int i = 0; i < rnd.length; i++) {
            btLng.add(rnd[i]);
            btLng.checkStructure(new BinTreeCheckPassEvent<Long>() {
                private String errorMessage;

                public void onPass(
                        BinTreeIterator<Long> leftIterator,
                        BinTreeIterator<Long> rightIterator,
                        BinTreeNodeInterface<Long> currentNode,
                        BinTreeNodeInterface<Long> previousNode) {
                    System.out.println(errorMessage
                            + "\r\n Current node: " + currentNode
                            + "\r\n Previous node: " + previousNode
                            + "\r\n Left iterator: " + leftIterator
                            + "\r\n Right iterator: " + leftIterator
                    );
                }

                public void setErrorMessage(String errMsg) {
                    errorMessage = errMsg;
                }

                public String getErrorMessage() {
                    return errorMessage;
                }
            });
        }
        btLng.checkStructure(new BinTreeCheckPassEvent<Long>() {
            private String errorMessage;

            public void onPass(
                    BinTreeIterator<Long> leftIterator,
                    BinTreeIterator<Long> rightIterator,
                    BinTreeNodeInterface<Long> currentNode,
                    BinTreeNodeInterface<Long> previousNode) {
                System.out.println(errorMessage + "\r\n" + currentNode);
            }

            public void setErrorMessage(String errMsg) {
                errorMessage = errMsg;
            }

            public String getErrorMessage() {
                return errorMessage;
            }
        });
        Iterator<Long> it = btLng.iterator();
        int i = 1;
        while (it.hasNext()) {
            Long ll = it.next();
            System.out.println("i = " + i + " btLng = " + ll);
            if (ll.longValue() == 11L) {
                it.remove();
            }
            i++;
        }
        it = btLng.iterator();
        i = 1;
        while (it.hasNext()) {
            Long ll = it.next();
            System.out.println("i = " + i + " btLng = " + ll);
            //if (ll.longValue() == 11L) {
            //    it.remove();
            //}
            i++;
        }
    }

    public void testBFLeftRotationCount() {
        byte threshold = 5;
        byte currentNodeBalanceFactor = (byte) -threshold;
        byte pivotBalanceFactor = (byte) -threshold;
        byte newCurrentNodeBalanceFactor;
        byte newPivotBalanceFactor;
        byte head = (byte) -threshold;
        System.out.println("left rotation");
        System.out.println("newCurrentNodeBalanceFactor");
        System.out.print(" \t");
        while (head <= threshold) {
            System.out.print(head + "\t");
            head++;
        }
        System.out.println("");
        while (currentNodeBalanceFactor <= threshold) {
            pivotBalanceFactor = (byte) -threshold;
            System.out.print(currentNodeBalanceFactor + "\t");
            while (pivotBalanceFactor <= threshold) {
                newCurrentNodeBalanceFactor = (byte) (Math.max(pivotBalanceFactor, 0) + 1 + currentNodeBalanceFactor - pivotBalanceFactor);
                System.out.print(newCurrentNodeBalanceFactor + "\t");
                pivotBalanceFactor++;
            }
            System.out.println("");
            currentNodeBalanceFactor++;
        }
        System.out.println("");
        currentNodeBalanceFactor = (byte) -threshold;
        pivotBalanceFactor = (byte) -threshold;
        System.out.println("left rotation");
        System.out.println("newPivotBalanceFactor");
        head = (byte) -threshold;
        System.out.print(" \t");
        while (head <= threshold) {
            System.out.print(head + "\t");
            head++;
        }
        System.out.println("");
        while (currentNodeBalanceFactor <= threshold) {
            pivotBalanceFactor = (byte) -threshold;
            System.out.print(currentNodeBalanceFactor + "\t");
            while (pivotBalanceFactor <= threshold) {
                newPivotBalanceFactor = (byte) (Math.max(pivotBalanceFactor, Math.max(pivotBalanceFactor, 0) + 1 + currentNodeBalanceFactor) + 1);
                System.out.print(newPivotBalanceFactor + "\t");
                pivotBalanceFactor++;
            }
            System.out.println("");
            currentNodeBalanceFactor++;
        }
        System.out.println("");
    }

    public void testBFRightRotationCount() {
        byte threshold = 5;
        byte currentNodeBalanceFactor = (byte) -threshold;
        byte pivotBalanceFactor = (byte) -threshold;
        byte newCurrentNodeBalanceFactor;
        byte newPivotBalanceFactor;
        byte head = (byte) -threshold;
        System.out.println("right rotation");
        System.out.println("newCurrentNodeBalanceFactor");
        System.out.print(" \t");
        while (head <= threshold) {
            System.out.print(head + "\t");
            head++;
        }
        System.out.println("");
        while (currentNodeBalanceFactor <= threshold) {
            pivotBalanceFactor = (byte) -threshold;
            System.out.print(currentNodeBalanceFactor + "\t");
            while (pivotBalanceFactor <= threshold) {
                newCurrentNodeBalanceFactor = (byte) (Math.min(0, pivotBalanceFactor) - 1 + currentNodeBalanceFactor - pivotBalanceFactor);
                System.out.print(newCurrentNodeBalanceFactor + "\t");
                pivotBalanceFactor++;
            }
            System.out.println("");
            currentNodeBalanceFactor++;
        }
        System.out.println("");
        currentNodeBalanceFactor = (byte) -threshold;
        pivotBalanceFactor = (byte) -threshold;
        System.out.println("right rotation");
        System.out.println("newPivotBalanceFactor");
        head = (byte) -threshold;
        System.out.print(" \t");
        while (head <= threshold) {
            System.out.print(head + "\t");
            head++;
        }
        System.out.println("");
        while (currentNodeBalanceFactor <= threshold) {
            pivotBalanceFactor = (byte) -threshold;
            System.out.print(currentNodeBalanceFactor + "\t");
            while (pivotBalanceFactor <= threshold) {
                newPivotBalanceFactor = (byte) (Math.min(pivotBalanceFactor, Math.min(0, pivotBalanceFactor) - 1 + currentNodeBalanceFactor) - 1);
                System.out.print(newPivotBalanceFactor + "\t");
                pivotBalanceFactor++;
            }
            System.out.println("");
            currentNodeBalanceFactor++;
        }
        System.out.println("");
    }

}
