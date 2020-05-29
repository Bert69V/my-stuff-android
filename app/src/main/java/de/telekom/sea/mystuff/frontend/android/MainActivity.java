package de.telekom.sea.mystuff.frontend.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import de.telekom.sea.mystuff.frontend.android.ui.ItemListRecyclerViewAdapter;
import de.telekom.sea.mystuff.frontend.android.util.ItemListViewModel;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private ItemListViewModel viewModel;
    private ItemListRecyclerViewAdapter adapter;
    private RecyclerView listViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Rumpelkammer");
        listViewItems = findViewById(R.id.rv_items);
        viewModel = new ViewModelProvider(this).get(ItemListViewModel.class);
        viewModel.initWithApplication(getApplication());
        adapter = new ItemListRecyclerViewAdapter(new ArrayList<>());

        listViewItems.setLayoutManager(new LinearLayoutManager(this));
        listViewItems.setAdapter(adapter);
        Log.d("MainActivity", "A:" + viewModel);
        LiveData<ApiResponse<List<Item>>> allItems = viewModel.getAllItems();
        Log.d("MainActivity", "B");
        allItems.observe(this, new Observer<ApiResponse<List<Item>>>() {
            @Override
            public void onChanged(ApiResponse<List<Item>> listApiResponse) {
                if (listApiResponse.isSuccessful()) {
                    Log.d("MainActivity", "C:" + allItems.getValue().body);
                    adapter.updateList(Objects.requireNonNull(allItems.getValue()).body);

                }else{
                    Toast.makeText(getApplicationContext(),"could not load list", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

