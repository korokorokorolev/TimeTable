package ru.akorolev.comparators;

import ru.akorolev.entities.Teacher;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 26.10.13
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class TeachersComparator implements Comparator<Teacher> {
    @Override
    public int compare(Teacher o1, Teacher o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
