package pl.andrzejressel.sieci.lista2.zadanie2;

import org.jgrapht.graph.DefaultEdge;

public class MyEdge extends DefaultEdge {

    private final int przepustowoscMax;
    private int przepustowosc = 0;

    public MyEdge(int przepustowoscMax) {
        this.przepustowoscMax = przepustowoscMax;
    }

    public int getPrzepustowoscMax() {
        return przepustowoscMax;
    }

    public int getPrzepustowosc() {
        return przepustowosc;
    }

    public void setPrzepustowosc(int przepustowosc) {
        this.przepustowosc = przepustowosc;
    }

    public String toString() {
        return getSource() + " -> " + getTarget() + ": " + Integer.toString(przepustowosc);
    }
}
