package by.itacademy.jd2.votetask.util;

import by.itacademy.jd2.votetask.domain.SavedVote;

import java.util.Comparator;

public class SavedVoteComparatorByTime implements Comparator<SavedVote> {
    public int compare(SavedVote o1, SavedVote o2) {
        return o2.getCreateDateTime().compareTo(o1.getCreateDateTime());
    }
}