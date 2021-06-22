package com.example.homeworke_3_android3.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.homeworke_3_android3.R;
import com.example.homeworke_3_android3.databinding.FragmentCreateBinding;
import com.example.homeworke_3_android3.model.PostModel;
import com.example.homeworke_3_android3.network.PostRepasitory;

import org.jetbrains.annotations.NotNull;

public class CreateFragment extends Fragment {

    protected FragmentCreateBinding binding;
    protected String title, content;
    protected Integer group, user;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clickListener();
    }

    private void clickListener() {
        binding.btnCreateFragmentCreate.setOnClickListener(v -> {
            collectTexts();
            createPost();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
            navController.navigateUp();
        });
    }

    private void createPost() {
        PostModel model = new PostModel(title, content, group, user);
        PostRepasitory.createPost(model);
    }

    private void collectTexts() {
        title = binding.editTitleFragmentCreate.getText().toString();
        content = binding.editContentFragmentCreate.getText().toString();
        user = Integer.valueOf(binding.editUserFragmentCreate.getText().toString());
        group = Integer.valueOf(binding.editGroupFragmentCreate.getText().toString());
    }
}

