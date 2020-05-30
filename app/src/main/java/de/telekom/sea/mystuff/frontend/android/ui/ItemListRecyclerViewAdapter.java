package de.telekom.sea.mystuff.frontend.android.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.databinding.MyStuffItemBinding;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import lombok.Getter;


public class ItemListRecyclerViewAdapter extends RecyclerView.Adapter <ItemListRecyclerViewAdapter.ViewHolder> {
    @Getter
    private final NavController navController;
    @Getter
    private List<Item> liste;

    public ItemListRecyclerViewAdapter(List<Item> liste, NavController navController) {
        this.liste = liste;
        this.navController = navController;
    }

    public void updateList(List<Item> liste) {
        this.liste.clear();
        this.liste.addAll(liste);
        notifyDataSetChanged();
    }


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        MyStuffItemBinding listItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.my_stuff_item, parent, false);
        Log.d("ItemListRecyclerViewAdapter", "listItemBinding:" + listItemBinding);
        return new ViewHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = getListe().get(position);
        holder.binding.setItem(item);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("itemId", item.getId());
                navController.navigate(R.id.action_itemListFragment_to_itemDetailsFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Getter
        private MyStuffItemBinding binding;

        public ViewHolder(@NotNull MyStuffItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
