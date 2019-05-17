package com.example.tablayout;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwoEmptyState3 extends Fragment {

    private CircleImageView circleImageView;
    private LinearLayout linearLayout1, linearLayout2, linearLayout3;
    private TextView textView1, textView2;
    private ImageButton imageButton;


    public FragmentTwoEmptyState3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View postView = inflater.inflate(R.layout.fragment_two_empty_state3, container, false);

        //--------------------------MAPPING WITH XML AND ONCLICK LISTENERS--------------------------

        circleImageView = postView.findViewById(R.id.prof_image2);
        imageButton = postView.findViewById(R.id.badge_2);
        linearLayout1 = postView.findViewById(R.id.ll_msg);
        linearLayout2 = postView.findViewById(R.id.ll_followers);
        linearLayout3 = postView.findViewById(R.id.ll_following);
        textView1 = postView.findViewById(R.id.follow_2);
        textView2 = postView.findViewById(R.id.message_2);


        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Opens image.", Toast.LENGTH_SHORT).show();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Premium Badge", Toast.LENGTH_SHORT).show();
            }
        });

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Opens Messages", Toast.LENGTH_SHORT).show();
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Opens Followers", Toast.LENGTH_SHORT).show();
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Opens Following", Toast.LENGTH_SHORT).show();
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Follows the user", Toast.LENGTH_SHORT).show();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Messages the user", Toast.LENGTH_SHORT).show();
            }
        });
        return postView;
    }
}

