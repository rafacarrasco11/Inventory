package com.example.inventory.data.model;

import java.util.Comparator;

/**
 * Esta clase se encarga de indicar como se ordena una dependencia que no sea
 * por el atributo que se haya escogido en el metodo equals de la clase Dependency
 */
public class DependencyComparator implements Comparator<Dependency> {

    @Override
    public int compare(Dependency o1, Dependency o2) {
        return o1.getDescription().compareTo(o2.getDescription());
    }
}
