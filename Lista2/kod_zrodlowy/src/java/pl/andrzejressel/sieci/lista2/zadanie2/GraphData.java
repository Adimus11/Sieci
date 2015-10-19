package pl.andrzejressel.sieci.lista2.zadanie2;

import org.jgrapht.graph.SimpleGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GraphData {

    public SimpleGraph<Integer, MyEdge> g;
    public int[][] przeplywy;
    public double niezawodnosc;
    public double maksymalnyLag;
    public final double wielkoscPakietu = 65535.0;
    public int iloscProb;

    public GraphData(String fileLocation) throws IOException {

        String line;
        File graphFile = new File(fileLocation + File.separator + "graf.txt");
        BufferedReader brGraph = new BufferedReader(new FileReader(graphFile));

        // Czytanie grafu

        // Pierwsza linijka - dane wejściowe
        String[] splitLine = brGraph.readLine().split(" ");

        niezawodnosc = Double.parseDouble(splitLine[0]);             // niezawodność
        maksymalnyLag = Double.parseDouble(splitLine[1]);         // dopuszczalne maksymalne opóźnienie pakietu
        iloscProb = Integer.parseInt(splitLine[2]);           // ilość prób


        g = new SimpleGraph<>(MyEdge.class);

        while ((line = brGraph.readLine()) != null && (splitLine = line.split(" ")).length == 3) {

            int v1 = Integer.parseInt(splitLine[0]);
            int v2 = Integer.parseInt(splitLine[1]);
            int przepustowosc = Integer.parseInt(splitLine[2]);

            g.addVertex(v1);
            g.addVertex(v2);

            g.addEdge(v1, v2, new MyEdge(przepustowosc));

        }

        //Czytanie przepływów
        int graphSize = g.vertexSet().size();
        przeplywy = new int[graphSize][graphSize];

        graphFile = new File(fileLocation + File.separator + "transfery.txt");
        brGraph = new BufferedReader(new FileReader(graphFile));

        int i = 0;

        /*
        while ((line = brGraph.readLine()) != null && (splitLine = line.split(" ")).length != 0) {
            for (int j = 0; j < splitLine.length; j++) {
                if (i == j) continue;
                przeplywy[i][j] = Integer.parseInt(splitLine[j]);
            }
            i++;
        }
*/

        while ((line = brGraph.readLine()) != null && (splitLine = line.split(" ")).length == 3) {

            int src = Integer.parseInt(splitLine[0]) - 1;
            int desc = Integer.parseInt(splitLine[1]) - 1;
            int predkosc = Integer.parseInt(splitLine[2]);

            przeplywy[src][desc] = predkosc;


        }


    }
}