package com.example.tablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwoEmptyState4 extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<FragmentModel4> appModelArrayList;
    private SingleListAdapter adapter;
    private LinearLayoutManager linearLayoutManager;


    private int images[] = {R.drawable.ic_graphic_eq_24dp,
            R.drawable.ic_camera_black_24dp,
            R.drawable.ic_person_black_24dp,
            R.drawable.ic_video_label_black_24dp,
            R.drawable.ic_lock_black_24dp,
            R.drawable.ic_storage_black_24dp,
            R.drawable.ic_do_not_disturb_white_24dp,
            R.drawable.ic_first_page_black_24dp,
            R.drawable.ic_list_black_24dp};
    private String names[] = {"App Settings", "Camera Settings", "Profile Settings", "Bottom Sheets", "Authentication", "Feed",
            "Block List", "Next/Previous", "Lists"};


    public FragmentTwoEmptyState4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View postView = inflater.inflate(R.layout.fragment_two_empty_state4, container, false);
        recyclerView = postView.findViewById(R.id.rv28);
        appModelArrayList = new ArrayList<>();
        adapter = new SingleListAdapter(appModelArrayList);
        for (int i = 0; i < names.length; i++) {
            FragmentModel4 appModel = new FragmentModel4();
            appModel.setNames(names[i]);
            appModel.setImages(images[i]);
            appModelArrayList.add(appModel);
        }

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return postView;
    }




    private class SingleListAdapter extends RecyclerView.Adapter<SingleListAdapter.ViewHolder> {

        private ArrayList<FragmentModel4> appModelArrayList;

        public SingleListAdapter(ArrayList<FragmentModel4> appModelArrayList) {
            this.appModelArrayList = appModelArrayList;
        }

        @NonNull
        @Override
        public SingleListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment4_contents2, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final SingleListAdapter.ViewHolder holder, final int position) {
            holder.textView.setText(appModelArrayList.get(position).getNames());
            holder.imageView.setImageResource(appModelArrayList.get(position).getImages());

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Position is : " + appModelArrayList.get(position).getNames(),
                            Snackbar.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return appModelArrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;
            private ImageView imageView, chev;
            private CardView cardView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                textView = itemView.findViewById(R.id.names);
                imageView = itemView.findViewById(R.id.images);
                chev = itemView.findViewById(R.id.chev);
                cardView = itemView.findViewById(R.id.cv1);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
        Runtime.getRuntime().gc();
    }
}

