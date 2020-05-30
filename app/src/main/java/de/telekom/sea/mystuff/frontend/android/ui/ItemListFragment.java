package de.telekom.sea.mystuff.frontend.android.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.model.Item;

public class ItemListFragment extends Fragment {

    private de.telekom.sea.mystuff.frontend.android.util.ItemListViewModel viewModel;
    private ItemListRecyclerViewAdapter adapter;
    private RecyclerView listViewItems;

    public static ItemListFragment newInstance() {
        return new ItemListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.item__list__fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(de.telekom.sea.mystuff.frontend.android.util.ItemListViewModel.class);
        viewModel.initWithApplication(getActivity().getApplication());
        adapter = new ItemListRecyclerViewAdapter(new ArrayList<>(), Navigation.findNavController(view));

        listViewItems = view.findViewById(R.id.rv_items);

        listViewItems.setLayoutManager(new LinearLayoutManager(getContext()));
        listViewItems.setAdapter(adapter);

        LiveData<ApiResponse<List<Item>>> allItems = viewModel.getAllItems();

        allItems.observe(this.getViewLifecycleOwner(), new Observer<ApiResponse<List<Item>>>() {
            @Override
            public void onChanged(ApiResponse<List<Item>> listApiResponse) {
                if (listApiResponse.isSuccessful()) {

                    adapter.updateList(Objects.requireNonNull(allItems.getValue()).body);

                }else{
                    Toast.makeText(getContext(),"could not load list", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}