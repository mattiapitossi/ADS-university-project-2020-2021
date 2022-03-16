import java.util.ArrayList;
import java.util.List;

public class Application_inherit_method {
    public static void main(String[] args) {

        var lambda = new String[]{"c0", "c2"};
        var gamma = new String[]{"c0", "c2", "c11"};

        var listaTabuLambda = new ArrayList<ArrayList<String>>();

        listaTabuLambda.add(new ArrayList<>(List.of("c3", "c5")));
        listaTabuLambda.add(new ArrayList<>(List.of("c6")));
        listaTabuLambda.add(new ArrayList<>(List.of("c11", "c12", "c13")));
        listaTabuLambda.add(new ArrayList<>(List.of("c11", "c14")));
        listaTabuLambda.add(new ArrayList<>(List.of("c16")));

        var maxGamma = gamma.length - 1;

        var copyListaTabuLambda = new ArrayList<>(listaTabuLambda);

        List<ArrayList<String>> listaTabuGamma = copyListaTabuLambda.stream() //
                .filter(s -> compareStrings(s.get(0), gamma[maxGamma])).toList();

        listaTabuGamma.stream().forEach(s -> {
            if (s.get(0).equals(gamma[maxGamma])) {
                s.remove(0);
            }
        });

        for (var elementoListaTabuGamma : listaTabuGamma) {
            for (var s : elementoListaTabuGamma) {
                System.out.printf("%s ", s);
            }
            System.out.println("\n");
        }
    }

    public static boolean compareStrings(String s, String gamma) {
        s = s.replaceAll("[^\\d.]", "");
        gamma = gamma.replaceAll("[^\\d.]", "");
        return Integer.parseInt(s) >= Integer.parseInt(gamma);
    }

}
