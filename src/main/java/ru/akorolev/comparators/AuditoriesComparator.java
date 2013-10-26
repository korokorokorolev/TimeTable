package ru.akorolev.comparators;

import ru.akorolev.entities.Auditory;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 26.10.13
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public class AuditoriesComparator implements Comparator<Auditory> {
    @Override
    public int compare(Auditory o1, Auditory o2) {
        return o1.getAuditory().compareTo(o2.getAuditory());
    }
}
