package de.telekom.sea.mystuff.frontend.android.util;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.model.Item;


public class ItemListViewModel extends MyStuffViewModel {
    
    public LiveData<ApiResponse<List<Item>>> getAllItems() {
        return this.getMyContext().getRepo().getAll();
    }
    public LiveData<ApiResponse<Item>> getItemById(long id) {
        return this.getMyContext().getRepo().getById(id);
    }

}




