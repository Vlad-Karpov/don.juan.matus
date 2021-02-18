package don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class CharacterArray implements Comparable<CharacterArray>, Serializable {

    static transient char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static transient int size = 10;
    transient int id;
    char[] cid = new char[size];

    public CharacterArray() {
        this.id = 0;
        for (int i = 0; i < size; i++) {
            this.cid[i] = digits[0];
        }
    }

    public CharacterArray(char[] cid) {
        if (cid.length != size) throw new RuntimeException("id size must by " + size + "!");
        this.cid = Arrays.copyOf(cid, size);
        this.id = Integer.parseUnsignedInt(new String(this.cid));
    }

    public CharacterArray(int id) {
        if (id < 0) throw new RuntimeException("id must by positive!");
        this.id = id;
        long n = id;
        long g = size;
        long d = 1;
        for (int i = 0; i < size; i++) {
            this.cid[size - i - 1] = digits[(int) ((n % g) / d)];
            n = n - n % g;
            d = g;
            g = g * size;
        }
    }

    public char[] getIdAsCharArray() {
        return this.cid;
    }
    public int getIdAsInt() {
        return this.id;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterArray that = (CharacterArray) o;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String toString() {
        return new String(this.cid);
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        for (int i = 0; i < size; i++) {
            stream.writeChar(this.cid[i]);
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        this.cid = new char[size];
        for (int i = 0; i < size; i++) {
            this.cid[i] = stream.readChar();
        }
        this.id = Integer.parseUnsignedInt(new String(this.cid));
    }

    @Override
    public int compareTo(CharacterArray o) {
        return this.id - o.id;
    }

}
