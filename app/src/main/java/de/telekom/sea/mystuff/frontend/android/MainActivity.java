package de.telekom.sea.mystuff.frontend.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

import de.telekom.sea.mystuff.frontend.android.ui.ItemListRecyclerViewAdapter;
import de.telekom.sea.mystuff.frontend.android.util.ItemListViewModel;


public class MainActivity extends AppCompatActivity {

    private ItemListViewModel viewModel;
    private ItemListRecyclerViewAdapter viewAdapter;
    private RecyclerView listViewItems;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        setTitle("Rumpelkammer");
        return view;
        }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewAdapter = new ItemListRecyclerViewAdapter(new ArrayList<>());
        listViewItems.setAdapter(viewAdapter);

        viewModel = new ViewModelProvider(this).get(ItemListViewModel.class);
        viewModel.getMyContext();

        viewModel.getAllItems();

    }

}
