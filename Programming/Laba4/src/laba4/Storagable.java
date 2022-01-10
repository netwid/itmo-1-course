package laba4;

import laba4.exceptions.EmptyException;
import laba4.exceptions.NotEnoughSpaceException;

public interface Storagable {
    void put(Storable item) throws NotEnoughSpaceException;
    Storable pullOut() throws EmptyException;
}
