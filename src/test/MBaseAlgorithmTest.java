import algorithms.MBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;


public class MBaseAlgorithmTest {

    @Test
    void testBaseAlgorithm() throws Exception {
        var mbase = new MBase();
        String[][] arr = {{"c1", "0", "c2"}, {"c1", "0", "0"}};

        var res = mbase.mbase(arr);
        res.forEach(c -> System.out.println(c[0]));
    }

    @Test
    void testMBaseAlgorithm() throws Exception {
        var mbase = new MBase();
        String[][] arr = {{"c0", "0", "0", "c0", "c0", "0", "0"},
                {"c1", "c1", "c1", "0", "c1", "c1", "0"},
                {"c2", "c2", "c2", "c2", "c2", "c2", "c2"},
                {"0", "c3", "0", "0", "c3", "c3", "c3"},
                {"0", "0", "0", "0", "0", "0", "0"},
                {"0", "c5", "0", "c5", "0", "c5", "0"}
        };

        var output = mbase.mbase(arr);

        var myArrayList = new ArrayList<String[]>();

        var mhs0 = new String[]{"c2", "c2", "c2", "c2", "c2", "c2", "c2"};
        var mhs1 = new String[]{"c1", "x", "c1", "c5", "x", "x", "c3"};
        var mhs2 = new String[]{"x", "x", "c1", "c0", "x", "x", "c3"};
        myArrayList.add(mhs0);
        myArrayList.add(mhs1);
        myArrayList.add(mhs2);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }

}
