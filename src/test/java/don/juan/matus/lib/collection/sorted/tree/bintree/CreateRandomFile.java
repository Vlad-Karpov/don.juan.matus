package don.juan.matus.lib.collection.sorted.tree.bintree;

import don.juan.matus.lib.collection.sorted.tree.bintree.tst.TreeMapTst;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CreateRandomFile {

    private static final int maxRandomDataFile = 10000000;

    public static void main(String... args) {
        try {
            createRandomDataFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createRandomDataFile() throws IOException {
        TreeMapTst<Long, Long> tmRndLng = new TreeMapTst<Long, Long>();
        List<Long> rList = new ArrayList<Long>();
        Long rnd;
        while (tmRndLng.size() < maxRandomDataFile) {
            rnd = Math.round(Math.random() * maxRandomDataFile);
            if (tmRndLng.put(rnd, rnd) == null) rList.add(rnd);
        }
        tmRndLng = null;
        System.gc();
        Files.createFile(Paths.get("./src/test/resources/RandomDataFile.dat"));
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src/test/resources/RandomDataFile.dat"));
        os.writeObject(rList);
        os.flush();
        os.close();
        System.gc();
    }


}
