package nl.deketelman.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlleKlassen implements Serializable {
    private List<Afspraak> alleAfspraken = new ArrayList<>();

    public AlleKlassen() {
        alleAfspraken = Bedrijf.getalleafspraken();
    }
}
