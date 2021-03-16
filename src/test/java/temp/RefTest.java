package temp;

import junit.framework.TestCase;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class RefTest extends TestCase {

    public void test1() throws InterruptedException {
        ReferenceQueue<A> q = new ReferenceQueue<>();
        A v = new A("key");
        WeakReference<A> r = new WeakReference<>(v, q);
        System.out.println(r);
        System.out.println(r.get());
        v = null;
        System.out.println(v);
        System.gc();
        Thread.sleep(2000);
        System.out.println(r);
        System.out.println(r.get());
        WeakReference<A> r1 = (WeakReference<A>) q.remove();
        System.out.println(r1);
        if (r1 != null) {
            System.out.println(r1.get());
        }
    }

    public void test3() throws InterruptedException {
        ReferenceQueue<String> q = new ReferenceQueue<>();
        String v = new String("key");
        WeakReference<String> r = new WeakReference<>(v, q);
        System.out.println(r);
        System.out.println(r.get());
        v = null;
        System.out.println(v);
        System.gc();
        Thread.sleep(2000);
        System.out.println(r);
        System.out.println(r.get());
        WeakReference<String> r1 = (WeakReference<String>) q.remove();
        System.out.println(r1);
        if (r1 != null) {
            System.out.println(r1.get());
        }
    }

    public void test4() throws InterruptedException {
        ReferenceQueue<String> q = new ReferenceQueue<>();
        String v = "key";
        WeakReference<String> r = new WeakReference<>(v, q);
        System.out.println(r);
        System.out.println(r.get());
        v = null;
        System.out.println(v);
        System.gc();
        Thread.sleep(2000);
        System.out.println(r);
        System.out.println(r.get());
        WeakReference<String> r1 = (WeakReference<String>) q.poll();
        System.out.println(r1);
        if (r1 != null) {
            System.out.println(r1.get());
        }
    }



    public void test2() throws InterruptedException {
        WeakHashMap<A, B> whm = new WeakHashMap<>();
        A k = new A("key");
        B v = new B(new ArrayList<>());
        whm.put(k, v);
        System.out.println(whm.get(k));
        System.out.println(whm.size());
        k = null;
        System.gc();
        Thread.sleep(2000L);
        System.out.println(whm.get(new A("key")));
        System.out.println(whm.size());
    }

    public void test5() throws InterruptedException {
        WeakHashMap<String, String> whm = new WeakHashMap<>();
        String k = new String("key");
        String s = new String("key");
        String v = new String("!!");
        whm.put(k, v);
        System.out.println(whm.get(s));
        System.out.println(whm.size());
        k = null;
        System.gc();
        Thread.sleep(2000L);
        System.out.println(whm.get(s));
        System.out.println(whm.size());
    }

    public void test6() throws InterruptedException {
        WeakHashMap<String, String> whm = new WeakHashMap<>();
        String k = "key";
        String s = "key";
        String v = "!!";
        whm.put(k, v);
        System.out.println(whm.get(s));
        System.out.println(whm.size());
        k = null;
        System.gc();
        Thread.sleep(2000L);
        System.out.println(whm.get(s));
        System.out.println(whm.size());
    }

    static class A {
        public A(String s) {
            this.s = s;
        }
        String s;

        @Override
        public String toString() {
            return "A{" +
                    "s='" + s + '\'' +
                    '}';
        }
    }

    static class B {
        List<Object> largeObject;

        public B(List<Object> largeObject) {
            this.largeObject = largeObject;
        }
    }

}
