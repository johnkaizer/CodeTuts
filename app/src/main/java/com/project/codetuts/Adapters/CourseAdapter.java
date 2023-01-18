package com.project.codetuts.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.codetuts.Model.CoursesModel;
import com.project.codetuts.R;
import com.project.codetuts.ui.home.HomeFragment;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    public CourseAdapter(Context context, ArrayList<CoursesModel> list, HomeFragment homeFragment) {
        this.context = context;
        this.list = list;
    }

    Context context;
    ArrayList<CoursesModel>list;

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        holder.courseImage.setImageResource(list.get(position).getImage());
        holder.courseTitle.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView courseImage;
        TextView courseTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseImage = itemView.findViewById(R.id.course_image);
            courseTitle = itemView.findViewById(R.id.course_header);
        }
    }
}
