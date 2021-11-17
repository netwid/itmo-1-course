import java.util.ArrayList;

public class TopHat implements Storagable {
    ArrayList<Storable> items;
    {
        items = new ArrayList<Storable> ();
    }

    public void put(Storable item) {
        items.add(item);
    }
}
