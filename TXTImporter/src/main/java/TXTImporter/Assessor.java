package TXTImporter;

import java.util.ArrayList;
import java.util.Objects;

public class Assessor {
    protected int id = 0;
    protected String name = "";

    public Assessor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Assessor() {
    }

//    @Override public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        Assessor assessor = (Assessor) obj;
//        return Objects.equals(name, assessor.name);
//    }
//
//    @Override public int hashCode() {
//        return Objects.hash(name);
//    }
}

