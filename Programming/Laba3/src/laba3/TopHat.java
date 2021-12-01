package laba3;

import java.util.ArrayList;

public class TopHat implements Storagable {
    ArrayList<Storable> items;
    {
        items = new ArrayList<Storable> ();
    }

    public void put(Storable item) {
        System.out.println("В шляпу положен предмет");
        items.add(item);
    }
}
