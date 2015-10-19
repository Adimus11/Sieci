package pl.andrzejressel.sieci.lista2.zadanie2;

import org.jgrapht.Graph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.SimpleGraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Zadanie2 {

    private final Random generator = new Random();

    public void start(String fileLocation) {

        GraphData graphData = null;
        SimpleGraph<Integer, MyEdge> graf;

        try {
            graphData = new GraphData(fileLocation);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        int[][] przeplywy = graphData.przeplywy;

        int all = 0;
        int passed = 0;

        List<Integer> opoznienia = new ArrayList<>();

        System.out.print("A: 1/1");

        for (int p = 1; p <= graphData.iloscProb; p++) {

            //noinspection unchecked
            graf = (SimpleGraph<Integer, MyEdge>) graphData.g.clone();

            //Czyścimy przepustowości (one się nie klonują)
            graf.edgeSet().parallelStream().forEach(myEdge -> myEdge.setPrzepustowosc(0));

            //Rzuty na niszczenie się
            ArrayList<MyEdge> krawedzie = new ArrayList<>();
            krawedzie.addAll(graf.edgeSet());

            for (MyEdge krawedz : krawedzie) {
                double waga = graphData.niezawodnosc;

                double number = generator.nextDouble();

                if (number > waga) {
                    //Usuwamy krawędź
                    graf.removeEdge(krawedz);
                }

            }

            int graphSize = graf.vertexSet().size();

            ConnectivityInspector<Integer, MyEdge> ci = new ConnectivityInspector<>(graf);

            if (ci.isGraphConnected()) {

                for (int i = 0; i < graphSize; i++) {
                    for (int j = 0; j < graphSize; j++) {
                        if (i == j) continue;

                        int vertex1 = i + 1;
                        int vertex2 = j + 1;
                        int weight = przeplywy[i][j];

                        List<MyEdge> list = new DijkstraShortestPath<>(graf, vertex1, vertex2).getPathEdgeList();

                        list.parallelStream().forEach(edge -> edge.setPrzepustowosc(edge.getPrzepustowosc() + weight));

                    }
                }

                if (testPrzepustowosci(graf)) {

                    //Liczymy opóźnienie
                    //http://stackoverflow.com/questions/22601036/stream-from-two-dimensional-array-in-jav
                    int sumaPakietow = Arrays.stream(przeplywy).flatMapToInt(Arrays::stream).sum();

                    final GraphData finalGraphData = graphData;
                    double sumaZKrawedzi = graf.edgeSet().stream().mapToDouble(myEdge -> ((myEdge.getPrzepustowosc() * finalGraphData.wielkoscPakietu) / myEdge.getPrzepustowoscMax() - myEdge.getPrzepustowosc())).sum();

                    int opoznienie = (int) (sumaZKrawedzi / sumaPakietow);

                    opoznienia.add(opoznienie);

                    if (opoznienie < graphData.maksymalnyLag) {
                        passed++;
                    }
                }
            }

            all++;

            System.out.print("\r");
            System.out.print(passed * 100 / all + "%  " + passed + "/" + all);


            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

        int srednieOpoznienie = (opoznienia.parallelStream().mapToInt(Integer::intValue).sum()/opoznienia.size());

        System.out.print("\n");
        System.out.print("Średnie opóźnienie: " + srednieOpoznienie);

    }


    private boolean testPrzepustowosci(Graph<Integer, MyEdge> graf) {
//       return graf.edgeSet().parallelStream().filter(edge -> edge.getPrzepustowosc() > edge.getPrzepustowoscMax()).count() == 0;


        for (MyEdge edge : graf.edgeSet()) {
            if (edge.getPrzepustowosc() > edge.getPrzepustowoscMax()) {
                return false;
            }
        }

        return true;

    }

}
