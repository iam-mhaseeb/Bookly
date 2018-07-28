package com.bookly.bookly.Utils;

import java.util.List;

/**
 * Created by Muhammad Haseeb on 2/18/2017.
 */

public interface ListFetcher {
    public interface OnDataloadListListener{

        void onDataloadListReady(List<Object> list);

    }
}
