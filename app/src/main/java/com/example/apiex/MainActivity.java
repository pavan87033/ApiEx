package com.example.apiex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*Create handle for the RetrofitInstance interface*/
        APIInterface service = APIClient.getClient().create(APIInterface.class);

        Call<EmployeeData> call = (Call<EmployeeData>) service.getEmployeeData(100);
        call.enqueue(new Callback<EmployeeData>() {
            @Override
            public void onResponse(Call<EmployeeData> call, Response<EmployeeData> response) {
                Log.e("response", new Gson().toJson(response));
                ArrayList<EmployeeData.EmployeeList> employeeListArrayList = new ArrayList<EmployeeData.EmployeeList>(response.body().getEmployeeList());

                generateEmployeeList(employeeListArrayList);


            }

            @Override
            public void onFailure(Call<EmployeeData> call, Throwable t) {

            }
        });
    }

    private void generateEmployeeList(ArrayList<EmployeeData.EmployeeList> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_employee_list);

        adapter = new EmployeeAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
