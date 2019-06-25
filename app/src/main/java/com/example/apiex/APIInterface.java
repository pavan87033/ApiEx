package com.example.apiex;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    String baseurl="http://navjacinth9.000webhostapp.com/json/retrofit-demo.php?";

    @GET("retrofit-demo.php")
    Call<EmployeeData> getEmployeeData(@Query("company_no") int companyNo);

}
