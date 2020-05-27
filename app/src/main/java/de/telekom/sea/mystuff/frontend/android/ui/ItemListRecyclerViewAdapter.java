package de.telekom.sea.mystuff.frontend.android.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.databinding.MyStuffItemBinding;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import lombok.Getter;


public class ItemListRecyclerViewAdapter extends RecyclerView.Adapter <ItemListRecyclerViewAdapter.ViewHolder> {

    @Getter
    private final List<Item> liste;

    public ItemListRecyclerViewAdapter(List<Item> liste) {
        this.liste = liste;
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
                R.layout.activity_main, parent, false);
        return new ViewHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = getListe().get(position);
        holder.binding.setItem(item);
    }

     @Override
    public int getItemCount() {
        return liste.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private MyStuffItemBinding binding;

        public ViewHolder(@NotNull MyStuffItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
