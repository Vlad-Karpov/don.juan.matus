package don.juan.matus.lib.collection.sorted.tree.bintree;

import don.juan.matus.lib.collection.sorted.tree.bintree.tst.TreeMapTst;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CreateRandomFile {

    private static final int maxRandomDataFile = 10000000;
    public static final String SRC_TEST_RESOURCES_RANDOM_DATA_FILE_DAT = "./src/test/resources/RandomDataFile.dat";

    public static void main(String... args) {
        try {
            createRandomDataFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createRandomDataFile() throws IOException {
        Path pth = Paths.get(SRC_TEST_RESOURCES_RANDOM_DATA_FILE_DAT);
        Files.createDirectories(pth);
        Files.deleteIfExists(pth);
        Files.createFile(pth);
        TreeMapTst<Long, Long> tmRndLng = new TreeMapTst<Long, Long>();
        List<Long> rList = new ArrayList<Long>();
        Long rnd;
        while (tmRndLng.size() < maxRandomDataFile) {
            rnd = Math.round(Math.random() * maxRandomDataFile);
            if (tmRndLng.put(rnd, rnd) == null) rList.add(rnd);
        }
        tmRndLng = null;
        System.gc();
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(pth.normalize().toString()));
        os.writeObject(rList);
        os.flush();
        os.close();
        System.gc();
    }


}
