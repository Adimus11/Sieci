package pl.andrzejressel.sieci.lista2.zadanie1;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Zadanie1 {


    private final Random generator = new Random();

    public void start() {

        List<GraphFactory> grafy = new ArrayList<>();

        grafy.add(new A());
        grafy.add(new B());
        grafy.add(new C());
        grafy.add(new D());

        for (GraphFactory graphFactory : grafy) {

            System.out.println();

            int all = 0;
            int passed = 0;

            System.out.print(graphFactory.toString() + ": 1/1");

            while (all!=1000) {

                @SuppressWarnings("unchecked")
                SimpleWeightedGraph<Integer, DefaultWeightedEdge> graf = graphFactory.stworzGraf();

                ArrayList<DefaultWeightedEdge> krawedzie = new ArrayList<>();
                krawedzie.addAll(graf.edgeSet());

                for (DefaultWeightedEdge krawedz : krawedzie) {
                    double waga = graf.getEdgeWeight(krawedz);

                    double number = generator.nextDouble();

                    if (number > waga) {
                        //Usuwamy krawędź
                        graf.removeEdge(krawedz);
                    }

                }

                if (czySpojny(graf)) {
                    passed++;
                }


                all++;

                System.out.print("\r");
                System.out.print(graphFactory.toString() + ": " + passed * 100 / all + "%  " + passed+"/" + all);


                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    @SuppressWarnings("unchecked")
    private boolean czySpojny(UndirectedGraph graph) {
        return new ConnectivityInspector<>(graph).isGraphConnected();
    }


}
