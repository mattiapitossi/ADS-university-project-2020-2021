# Documentazione Progetto

# Parsing

I file da caricare e parsare sono di tipo .matrix e sono definiti come segue: 

- I commenti sono delineati da `;;;`
- Nel file sono presenti i valori della matrice (0,1)
- Ogni riga si conclude con un separatore con il carattere `-`

## Parser

---

In Java esistono diversi modi per leggere un file:

[](https://www.baeldung.com/reading-file-in-java)

In particolare, l’obiettivo di questa funzionalità è quello di leggere determinati file di tipo .matrix  contenuti nella cartella di cui verrà indicato il relativo percorso in input all’avvio del programma (fino a quando l’utente lo desidera), analizzarlo e creare un oggetto Java manipolabile.

## Proposta di Design

---

Una volta letto il file .matrix, il compito è quello di individuare la dimensione e il valore delle i-esime righe e j-esime colonne eliminando dati superflui quali i commenti e i separatori delle righe della matrice.

Il formato dei valori della matrice risultante è ancora da discutere.

### Soluzione 1

L’algoritmo verifica se si tratta di un commento oppure di una possibile riga della matrice, in quest’ultimo caso la riga in formato String viene salvata in una lista. Una volta terminato questo processo, ogni riga della lista viene analizzata e ogni suo valore viene spezzettato in modo da ottenere ogni singolo valore della matrice e inserito in un array bidimensionale.

![Untitled Diagram.drawio.png](Documentazione%20Progetto%2027ef24cc1dc944c7a498414a541ff42a/Untitled_Diagram.drawio.png)

Problematiche riscontrare in questa soluzione:

Ogni riga viene analizzata due volte.

In alternativa all’array bidimensionale che verrà restituito dal parser, si potrebbe pensare a creare una Resource che conterrà la matrice bidimensionale con un identificativo univoco (possibilmente randomico o definito dal nome del file stesso) per distinguere le varie matrici. 

```java
@Data
public class MatrixResource {
	private String id;
	private int[][] matrix;
}
```

### Soluzione 2

In Java non è possibile impostare dinamicamente una matrice bidimensionale, infatti visto la natura dei file non ci è data alcuna informazione riguardo la dimensione, sia delle righe che delle colonne, della matrice. Tuttavia, una possibile seconda soluzione potrebbe consistere nell’individuare la dimensione della matrice e parsare ogni singolo valore della matrice; spostandosi poi nella riga successiva qualsiasi volta si incontri il simbolo `-`.

## Validità dei files

---

Le cartelle benchmark 1 e 2 contengono numerosi file che hanno il compito di testare i vari algoritmi implementati. In particolare, l’utente inserisce un percorso di una delle due cartelle e il programma dovrà individuare quali file saranno utili al fine dei test di benchmark. È noto che i file contenuti in queste cartelle non sono tutti della stessa dimensione e sono presenti anche dei cloni. Vi è dunque la possibilità di aggiungere un controllo che verifichi se il file è simile ad un altro già caricato e un controllo sulla dimensione del file, possibilmente con un limite preimpostato o dato in input dall’utente.

**Esempio di File di benchmark** (74L85.000.matrix)

```
;;; Host = zelda6, Version = 26.1, date = 2010-01-13-17-08-09
;;; Source = /tilde/dekleer/projects/GDE/DXC/dxc-09-syn-benchmark-1.1/74l85/74L85.000.scn
;;; Error status nil
;;; Injected fault:32(o2) 
;;; Map 1(z1) 2(z2) 3(z3) 4(z4) 5(z5) 6(z6) 7(z7) 8(z8) 9(z9) 10(z10) 11(z11) 12(z12) 13(z13) 14(z14) 15(z15) 16(z16) 17(z17) 18(z18) 19(z19) 20(z20) 21(z21) 22(z22) 23(z23) 24(z24) 25(z25) 26(z26) 27(z27) 28(z28) 29(z29) 30(z30) 31(o1) 32(o2) 33(o3) 
0 0 0 1 0 0 0 0 1 1 0 0 0 0 0 0 1 0 0 0 0 1 0 1 0 0 0 0 0 0 0 1 0 -
0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 1 0 -
```

Per il nostro algoritmo, i dati rilevanti sono rappresentati dai soli valori che non sono compresi tra i commenti.

# MBase

# Proposta di design

Per evitare controlli superflui abbiamo deciso di escludere a priori il calcolo tra l’unione dei singoletti e gli insiemi vuoti. Inoltre, abbiamo anche escluso il controllo su tutte le unioni tra i vettori rappresentativi e il vettore rappresentativo dell’ultimo elemento del dominio.

# Proof of Concept

```java
public ArrayList<String[]> mbase(String[][] matrix) {
	var column = new String[matrix[0].length];
	var mhs = true;
	var queue = new ArrayList<String[]>();
	var output = new ArrayList<String[]>();

	//arrayOfColumns (la coda) contiene tutti i singoletti iniziali
	for(var j=0;j<matrix[0].length-1;j++) {
		for(var i=0;i<matrix.length();i++) {
			lambda[j] = array[i][j];
			arrayOfColumns.add(lambda[j]);
		}	
		//come output di check considero solo "ok" e "mhs", potremmo usare un true false altrimenti
		if(checkSingleton(lambda)){
			queue.add(lambda);
		}else{
			output.add(lambda);
	}

	for(var i=0; i<arrayOfColumns.size(); i++) {
		for(var j=1; j<arrayOfColumns.size()-1; j++){
			var e = arrayOfColumns.get(j);
			var vector = arrayOfColumn.get(i);
			var sigma = calculateVectorUnion(vector, e);
			if (checkUnion(sigma, vector, e) {
				queue.add(sigma);
			} else if (!checkUnion(sigma, vector, e)) {
				output.add(sigma);
			}
		}
	}

	return output;

}
```

### Utilizzo di ArrayList e passaggio a code (LinkedList)

Inizialmente avevamo usato gli ArrayList con doppi for loop e siamo passati poi alle code (LinkedList) con le rimozioni

MBase utilizza principalmente 3 strutture dati: ArrayList, LinkedList e Array monodimensionale. 

Inizialmente si era pensato ad un approccio che prevedeva l’utilizzo della sola struttura dati quale ArrayList, a seguito di alcuni test eseguiti sui file .matrix, si è deciso di utilizzare come struttura la LinkedList. Da qui il vantaggio di utilizzare il metodo `peek` di questa struttura dati con complessità temporale `O(1)`:

```java
while (queue.peek() != null) {
	var vector = queue.peek();
	var max = getVectorColumn(vector, queueSingoletti);
  for (var j = max + 1; j < queueSingoletti.size(); j++) {
	  var e = queueSingoletti.get(j);
	  var sigma = calculateVectorUnion(vector, e);
	  var maxSigmaIndex = getMaxVectorProjection(sigma);
    var maxSigmaStr = "c" + maxSigmaIndex;
    var maxSigma = getMaxSigmaFromSingoletti(maxSigmaStr, queueSingoletti);
    if (checkUnion(sigma, vector, e).equals("ok") 
				&& maxSigma != matrix[0].length - 1) {
      queue.add(sigma);
    } else if (checkUnion(sigma, vector, e).equals("mhs")) {
      output.add(sigma);
    }
   }
 queue.poll();
}
```

`queue`  è un oggetto di tipo LinkedList e contiene tutti i singoletti, calcolati precedentemente, candidati che potrebbero diventare MHS. 

```java
private String[] calculateVectorUnion(String[] vectorA, String[] vectorB) {

	int sigmaLength = vectorA.length;
	String[] sigma = new String[sigmaLength];

	// considerando i tre vettori A, B e sigma della stessa dimensione sigmaLength (fare 
	// un controllo?)
	for (int i=0; i<sigmaLength; i++){
		if(vectorA[i]=="0" && vectorB[i]=="0"){
			sigma[i] = "0";
		}else if(vectorA[i]=="0" && vectorB[i]!="0"){
			sigma[i]=vectorB[i];
		}else if(vectorA[i]!="0" && vectorB[i]=="0"){
			sigma[i]=vectorA[i];
		}else{
			sigma[i]="x";
		}
	}

	return sigma; 

}
```

```java
private String checkSingleton(String[] sigma){
	var targetSet = new HashSet<String>(Arrays.asList(sigma));
	if (!targetSet.contains("0")) return "mhs";
	else if (targetSet.containsAll("0")) return "ko";
	else return "ok";	
}

private String checkUnion(String[] sigma, String[] vector, String[] e){
	var targetSet = new HashSet<String>(Arrays.asList(sigma));
	var domain1 = getColumnDomain(vector);
	var domain2 = getColumnDomain(e);
	if (!targetSet.contains("0") && targetSet.contains(domain1) //
			&& targetSet.contains(domain2)) {
			return "mhs";
	}
	if (targetSet.contains("0") && (!targetSet.contains(domain1) //
			|| !targetSet.contain(domain2)) {
			return "ko";
	} else {
		return "ok";	
	}
}

private String getColumnDomain(String[] vector) {
	for(var i=0; i < vector.length(); i++ ) {
		if(!vector[i].equals("0")) {
			return vector[i];
		}
	}
}
```

# Pre-Elaborazioni

Le pre-elaborazioni vengono eseguite in 4 fasi differenti:

### Conversione da array multidimensionale a List

Questa prima fase ha il compito di agire come adapter verso le precedenti operazioni di parsing della matrice. Questa prima fase permette di trasformare un array bidimensionale, per sua natura limitato su certi aspetti, in una struttura dati non elementare quale l’ArrayList presente nel linguaggio scelto per questo elaborato quale Java. A questo punto, la conversione risulta più efficiente sia in termini di prestazioni che di lettura di codice; infatti l’utilizzo della programmazione funzionale permette di ridurre notevolmente il numero di cicli e di operazioni innestate.

### Rimozione delle righe

Seguendo l’ordine delle operazioni da svolgere secondo le pre-elaborazioni che non richiedono alcuna post elaborazione, la rimozione delle righe viene eseguita subito dopo la conversione degli array. La soluzione proposta per questa operazione prevede l’utilizzo di una variabile booleana, in particolare viene verificato se una particolare riga della matrice contiene tutte gli elementi della riga o delle righe successive; in caso positivo questa riga verrà aggiunta ad una lista delle righe da rimuovere.

### Rimozione delle colonne

In questo caso ogni riga della matrice viene analizzata, nel caso in cui ogni elemento della riga in analisi corrisponda al valore di zero; l’indice di questa riga viene inserito in un array e successivamente rimosso al termine dell’analisi di ogni riga.

### Conversione da List ad array multidimensionale

Questa ultima fase si occupa di convertire, con il processo inverso rispetto a quello iniziale, la lista nel formato di input, permettendo così di preservare le implementazioni del parser e di Mbase.

# Testing

Per la sperimentazione abbiamo deciso di effettuare dei test incrementali. Siamo partiti da matrici molto piccole, in modo da affrontare il problema passo per passo. Nel nostro caso, con semplici test di unità effettuati con JUnit:

```java
@Test
    void testMBaseAlgorithm() {
        var mbase = new MBase();
        String[][] arr = {
                {"c0", "c1", "c2", "0", "0", "0"}, 
                {"0", "c1", "c2", "c3", "0", "c5"}, 
                {"0", "c1", "c2", "0", "0", "0"}, 
                {"c0", "0", "c2", "0", "0", "c5"}, 
                {"c0", "c1", "c2", "c3", "0", "0"}, 
                {"0", "c1", "c2", "c3", "0", "c5"}, 
                {"0", "0", "c2", "c3", "0", "0"}
        };

        var output = mbase.mbase(arr, "test", 8L);

        var myArrayList = new ArrayList<String[]>();

        var mhs0 = new String[]{"c2"};
        var mhs1 = new String[]{"c1", "c3","c5"};
        var mhs2 = new String[]{"c0", "c1","c3"};
        myArrayList.add(mhs0);
        myArrayList.add(mhs2);
        myArrayList.add(mhs1);

        assertArrayEquals(myArrayList.toArray(), output.toArray());
    }
```

Lo stesso test è stato poi rieseguito con la pre-elaborazione, in modo da essere sicuri che il risultato fosse il medesimo:

## Utilizzo dei file .matrix

Una volta effettuati in test tramite JUnit,
