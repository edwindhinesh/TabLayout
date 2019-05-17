package com.example.tablayout;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.CustomViewHolder> {
    private List<Data> employees;

    public EmployeesAdapter(List<Data> employees) {
        this.employees = employees;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_list, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Data employee = employees.get(position);
        holder.employeeName.setText(employee.getName());
        holder.email.setText(employee.getEmail());
        holder.mobile.setText(employee.getMobile());
        holder.phone.setText(employee.getPhone());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView employeeName, email, mobile, phone;

        public CustomViewHolder(View view) {
            super(view);
            employeeName = (TextView) view.findViewById(R.id.employeeName);
            email = (TextView) view.findViewById(R.id.email);
            mobile = (TextView) view.findViewById(R.id.mobile);
            phone = (TextView) view.findViewById(R.id.phone);
        }
    }
}
