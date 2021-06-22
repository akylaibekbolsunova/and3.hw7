package com.example.homeworke_3_android3.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworke_3_android3.databinding.ItemPostBinding;
import com.example.homeworke_3_android3.model.PostModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    protected List<PostModel> list = new ArrayList<>();
    protected OnClickPostAdapter listener;

    public PostAdapter(OnClickPostAdapter listener) {
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemPostBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PostAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<PostModel> list) {
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void deleteById(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        protected ItemPostBinding binding;

        public ViewHolder(@NonNull @NotNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PostModel model) {
            binding.textContentItemPost.setText(model.getContent());
            binding.textGroupItemPost.setText(String.valueOf(model.getGroup()));
            binding.textTitleItemPost.setText(model.getTitle());
            binding.textUserItemPost.setText(String.valueOf(model.getUser()));
            binding.getRoot().setOnClickListener(v -> {
                listener.onClick(model);
            });
            binding.getRoot().setOnLongClickListener(v -> {
                listener.onClickDelete(model);
                return true;
            });
        }
    }

    interface OnClickPostAdapter {
        void onClick(PostModel model);

        void onClickDelete(PostModel model);
    }
}
