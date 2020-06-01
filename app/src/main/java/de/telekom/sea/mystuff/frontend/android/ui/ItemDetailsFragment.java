package de.telekom.sea.mystuff.frontend.android.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.databinding.ItemDetailsFragmentBinding;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.util.ItemListViewModel;
import lombok.Getter;

public class ItemDetailsFragment extends Fragment {

    @Getter
    private ItemDetailsFragmentBinding binding;
    @Getter
    private ItemListViewModel viewModel;


    public static ItemDetailsFragment newInstance() {
        return new ItemDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.item_details_fragment, container, false);
        return binding.getRoot();
                   }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(de.telekom.sea.mystuff.frontend.android.util.ItemListViewModel.class);
        viewModel.initWithApplication(getActivity().getApplication());

        long itemId = getArguments().getLong("itemId");
        Toast.makeText(getActivity(), "ItemId:" + itemId, Toast.LENGTH_LONG);

        LiveData<ApiResponse<Item>> itemApi = viewModel.getItemById(itemId);
        itemApi.observe(this.getViewLifecycleOwner(), apiResponse -> {
            if(apiResponse.isSuccessful()){
              binding.setItem(apiResponse.body);
            }else{
              Toast.makeText(getActivity(),"Could not item", Toast.LENGTH_LONG);
            }
        });

    }
}



