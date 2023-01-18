package com.project.codetuts.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.codetuts.Adapters.CourseAdapter;
import com.project.codetuts.Adapters.CoursesAdapterAll;
import com.project.codetuts.Model.CoursesModel;
import com.project.codetuts.R;
import com.project.codetuts.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    //popular courses
    RecyclerView courseRecyclerV;
    ArrayList<CoursesModel> coursesModels;
    CourseAdapter courseAdapter;

    //All courses
    RecyclerView courseRecyclerAll;
    ArrayList<CoursesModel> coursesModelList;
    CoursesAdapterAll coursesAdapterAll;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Popular courses
        courseRecyclerV =root.findViewById(R.id.popular_courses);
        coursesModels =new ArrayList<>();

        coursesModels.add(new CoursesModel(R.drawable.pic1,"Android"));
        coursesModels.add(new CoursesModel(R.drawable.pic2,"Android"));
        coursesModels.add(new CoursesModel(R.drawable.pic3,"Android"));
        coursesModels.add(new CoursesModel(R.drawable.pic4,"Android"));
        coursesModels.add(new CoursesModel(R.drawable.pic5,"Android"));
        coursesModels.add(new CoursesModel(R.drawable.pic6,"Android"));
        coursesModels.add(new CoursesModel(R.drawable.pic7,"Android"));
        coursesModels.add(new CoursesModel(R.drawable.pic8,"Android"));
        coursesModels.add(new CoursesModel(R.drawable.pic9,"Android"));
        coursesModels.add(new CoursesModel(R.drawable.pic10,"Android"));
        coursesModels.add(new CoursesModel(R.drawable.pic11,"Android"));


        courseAdapter = new CourseAdapter(getActivity(), coursesModels, this);
        courseRecyclerV.setAdapter(courseAdapter);
        courseRecyclerV.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
        courseRecyclerV.setHasFixedSize(true);
        courseRecyclerV.setNestedScrollingEnabled(false);

        //All courses
        courseRecyclerAll =root.findViewById(R.id.all_courses);
        coursesModelList =new ArrayList<>();

        coursesModelList.add(new CoursesModel(R.drawable.pic1,"Android"));
        coursesModelList.add(new CoursesModel(R.drawable.pic2,"Android"));
        coursesModelList.add(new CoursesModel(R.drawable.pic3,"Android"));
        coursesModelList.add(new CoursesModel(R.drawable.pic4,"Android"));
        coursesModelList.add(new CoursesModel(R.drawable.pic5,"Android"));
        coursesModelList.add(new CoursesModel(R.drawable.pic6,"Android"));
        coursesModelList.add(new CoursesModel(R.drawable.pic7,"Android"));
        coursesModelList.add(new CoursesModel(R.drawable.pic8,"Android"));
        coursesModelList.add(new CoursesModel(R.drawable.pic9,"Android"));
        coursesModelList.add(new CoursesModel(R.drawable.pic10,"Android"));
        coursesModelList.add(new CoursesModel(R.drawable.pic11,"Android"));


        coursesAdapterAll = new CoursesAdapterAll(getActivity(), coursesModelList, this);
        courseRecyclerAll.setAdapter(coursesAdapterAll);
        courseRecyclerAll.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL, false));
        courseRecyclerAll.setHasFixedSize(true);
        courseRecyclerAll.setNestedScrollingEnabled(false);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}