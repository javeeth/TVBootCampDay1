package Repository;

import java.util.Comparator;
import Models.SportsDetail;

public class EventsComparator implements Comparator<SportsDetail> {

    public int compare(SportsDetail o1, SportsDetail o2) {
        return o1.get_amount() - o2.get_amount();
    }
}
