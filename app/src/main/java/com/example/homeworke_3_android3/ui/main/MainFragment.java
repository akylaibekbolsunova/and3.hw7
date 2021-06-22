package com.example.homeworke_3_android3.ui.main;

import android.app.AlertDialog;
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
import com.example.homeworke_3_android3.databinding.FragmentMainBinding;
import com.example.homeworke_3_android3.model.PostModel;
import com.example.homeworke_3_android3.network.PostRepasitory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainFragment extends Fragment implements PostAdapter.OnClickPostAdapter {

    public static final String MODEL = "model";

    protected FragmentMainBinding binding;
    protected PostAdapter adapter;
    NavController navController;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPosts();
    }


    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        getPosts();
        clickListener();
    }


    private void clickListener() {
        binding.btnSaveFragmentMain.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainFragment_to_createFragment);
        });
    }

    private void init() {
        adapter = new PostAdapter(this);
        binding.recyclerViewPostFragmentMain.setAdapter(adapter);
        navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_container);
    }

    private void getPosts() {
        PostRepasitory.getPosts(new PostRepasitory.PostsCallBack() {
            @Override
            public void onSuccess(List<PostModel> list) {
                if (adapter != null) {
                    adapter.addList(list);
                }
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @Override
    public void onClick(PostModel model) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MODEL, model);
        navController.navigate(R.id.action_mainFragment_to_changeFragment, bundle);
    }

    @Override
    public void onClickDelete(PostModel model) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Вы точно хотите удалить?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    deleteById(model.getId());
                })
                .setNegativeButton("No", (dialog, id) -> {

                });
        AlertDialog alert = builder.create();
        alert.setTitle("Помощник!");
        alert.show();
    }

    private void deleteById(Integer id) {
        PostRepasitory.deleteById(id, new PostRepasitory.PostDeleteByIdCallback() {
            @Override
            public void onSuccess(PostModel model) {
                getPosts();
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }
}