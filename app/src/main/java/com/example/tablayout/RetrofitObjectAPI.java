package com.example.tablayout;


import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;


public interface RetrofitObjectAPI {

    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of employee.
     */
    @POST("global-user")
    Call<User> getEmployeeDetails(@Body Data email);
}