package com.example.tablayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwoEmptyState1 extends Fragment {

    String url = "https://api.indiafilings.com/common/";

    private List<Data> employeeList,employeeList2;
    private RecyclerView recyclerView;
    private EmployeesAdapter eAdapter;
    private boolean isScrolling = false;
    private int itemsVisibleOnScreen;
    private int totalItemsInAdapter;
    private int scrolledUpItemsCount;
    private ProgressBar progressBar;
    private int isFirstTime = 0;
    private int i =0;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);
    Data data = new Data("ramu@verveindia.com", "get");
    Call<User> call = service.getEmployeeDetails(data);

    public FragmentTwoEmptyState1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View postView = inflater.inflate(R.layout.fragment_two_empty_state1, container, false);
        recyclerView = (RecyclerView)postView.findViewById(R.id.recycler_view);
        progressBar = postView.findViewById(R.id.pb);
        getRetrofitObject();
        return postView;
    }

    void getRetrofitObject() {

        call.clone().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {

                Log.d("TestTag", "rews: "+new Gson().toJson(response));


                try {

                    employeeList = response.body().getData();
                    eAdapter = new EmployeesAdapter(employeeList);
                    final LinearLayoutManager eLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(eLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);
                    for(i=0;i<=4;i++){
                        fetchData();
                    }
                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                isScrolling = true;
                            }
                        }

                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            itemsVisibleOnScreen = eLayoutManager.getChildCount();
                            totalItemsInAdapter = eLayoutManager.getItemCount();
                            scrolledUpItemsCount = eLayoutManager.findFirstVisibleItemPosition();
                            if (isScrolling && itemsVisibleOnScreen + scrolledUpItemsCount == totalItemsInAdapter) {
                                isScrolling = false;
                                fetchData();
                            }
                        }
                    });

                }

                catch (Exception e) {
                    Log.d("TestTag", "e: "+e);
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("TestTag", t.toString());
            }
        });
    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFirstTime == 0) {
                    loadMore();
                    isFirstTime = 1;

                } else if (isFirstTime == 1) {

                    loadMore();
                    isFirstTime = 0;
                }
            }
        }, 1000);
    }

    private void loadMore() {

        Call<User> call2 = service.getEmployeeDetails(data);
        call2.clone().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {

                Log.d("TestTag", "rews: "+new Gson().toJson(response));

                try {
                    employeeList2 = response.body().getData();
                    employeeList.addAll(employeeList2);
                    eAdapter.notifyDataSetChanged();
                }

                catch (Exception e) {
                    Log.d("TestTag", "e: "+e);
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("TestTag", t.toString());
            }
        });
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
        Runtime.getRuntime().gc();
    }

}

