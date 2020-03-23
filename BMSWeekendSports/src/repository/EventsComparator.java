package repository;

import java.util.Comparator;
import models.SportsDetail;

public class EventsComparator implements Comparator<SportsDetail> {

    public int compare(SportsDetail o1, SportsDetail o2) {
        return o1.getAmount() - o2.getAmount();
    }
}
