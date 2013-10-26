package ru.akorolev.comparators;

import ru.akorolev.entities.TrainingFeed;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 26.10.13
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public class TrainingFeedsComparator implements Comparator<TrainingFeed> {
    @Override
    public int compare(TrainingFeed o1, TrainingFeed o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
