import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Application_inherit_method {
    public static void main(String[] args) {

        var lambda = new String[] { "c0", "c2" };
        var gamma = new String[] { "c0", "c2", "c11" };

        var listaTabuLambda = new ArrayList<ArrayList<String>>();

        listaTabuLambda.add(new ArrayList<>(List.of("c3", "c5")));
        listaTabuLambda.add(new ArrayList<>(List.of("c6")));
        listaTabuLambda.add(new ArrayList<>(List.of("c11", "c12", "c13")));
        listaTabuLambda.add(new ArrayList<>(List.of("c11", "c14")));
        listaTabuLambda.add(new ArrayList<>(List.of("c16")));

        var maxGamma = gamma.length - 1;

        var copyListaTabuLambda = new ArrayList<ArrayList<String>>();
        copyListaTabuLambda.addAll(listaTabuLambda);

        List<ArrayList<String>> listaTabuGamma = copyListaTabuLambda.stream() //
                .filter(s -> compareStrings(s.get(0), gamma[maxGamma])) //
                .collect(Collectors.toList());

        listaTabuGamma.stream().forEach(s -> {
            if (s.get(0).equals(gamma[maxGamma])) {
                s.remove(0);
            }
        });

        for (var i = 0; i < listaTabuGamma.size(); i++) {
            var elementoListaTabuGamma = listaTabuGamma.get(i);
            for (var j = 0; j < elementoListaTabuGamma.size(); j++) {
                System.out.printf("%s ", elementoListaTabuGamma.get(j));
            }

            System.out.println("\n");
        }
    }

    public static boolean compareStrings(String s, String gamma) {
        s = s.replaceAll("[^\\d.]", "");
        gamma = gamma.replaceAll("[^\\d.]", "");
        if (Integer.parseInt(s) >= Integer.parseInt(gamma)) {
            return true;
        } else
            return false;

    }

}
