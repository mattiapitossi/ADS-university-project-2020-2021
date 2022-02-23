import algorithms.MBase;
import utils.MatrixParser;

import java.io.FileNotFoundException;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        var line = "C:\\Users\\mmich\\Desktop\\VS_workspace\\Algoritmi---Strutture-Dati---2020_2021\\src\\main\\resources\\74L85.000.matrix";
        var mbase = new MBase();
        var res = mbase.mbase(MatrixParser.parse(line));
    }
}
