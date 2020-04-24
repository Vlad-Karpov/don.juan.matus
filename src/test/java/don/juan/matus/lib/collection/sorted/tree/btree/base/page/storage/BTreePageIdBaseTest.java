package don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage;

import junit.framework.TestCase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BTreePageIdBaseTest extends TestCase {

    public void testBTreePageIdBase() throws IOException, ClassNotFoundException {
        BTreePageIdBase id = new BTreePageIdBase(12345);
        System.out.println(id);


        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Talda.out"));
        out.writeObject(id);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Talda.out"));
        id = (BTreePageIdBase) in.readObject();

        System.out.println(id);

        id = new BTreePageIdBase(0);
        System.out.println(id);
        id = new BTreePageIdBase(1);
        System.out.println(id);
        id = new BTreePageIdBase(2147483647);
        System.out.println(id);
    }

}
