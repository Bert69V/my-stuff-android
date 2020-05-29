package de.telekom.sea.mystuff.frontend.android.api;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.model.Item;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ItemApi {

    @GET("/api/v1/items")
    public LiveData<ApiResponse<List<Item>>>getAll();

    @GET("/api/v1/items/{id}")
    public LiveData<ApiResponse<Item>> getById(@Path("id") Long id);

    @POST("/api/v1/items")
    public LiveData<ApiResponse<Item>> updateItem(@Body Item newItem);

}
