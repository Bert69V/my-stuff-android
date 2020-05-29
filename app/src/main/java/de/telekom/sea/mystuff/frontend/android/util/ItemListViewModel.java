package de.telekom.sea.mystuff.frontend.android.util;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.model.Item;


public class ItemListViewModel extends MyStuffViewModel {
    
    public LiveData<ApiResponse<List<Item>>> getAllItems() {
        Log.d ("ItemListViewModel", "This getMyContext:" + getMyContext());
        return this.getMyContext().getRepo().getAll();
    }
}




