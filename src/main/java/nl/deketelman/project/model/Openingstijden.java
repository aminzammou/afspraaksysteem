package nl.deketelman.project.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Openingstijden {
    private int dag;
    private LocalTime openingstijd;
    private LocalTime sluitingstijd;
    private ArrayList<Openingstijden> alleOpeningstijden = new ArrayList();

    public Openingstijden(int d,LocalTime op, LocalTime sl){
        dag = d;
        openingstijd = op;
        sluitingstijd = sl;
    }

    public int getDag() {
        return dag;
    }

    public LocalTime getOpeningstijd() {
        return openingstijd;
    }

    public LocalTime getSluitingstijd() {
        return sluitingstijd;
    }
}
