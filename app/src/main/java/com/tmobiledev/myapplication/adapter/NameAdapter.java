package com.tmobiledev.myapplication.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmobiledev.myapplication.R;
import com.tmobiledev.myapplication.model.NameModel;

import java.util.ArrayList;
import java.util.List;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder> {
    private Context context;
    private List<NameModel> nameModelList = new ArrayList<>();
    private NameAdapterCallback nameAdapterCallback;

    public NameAdapter(Context context, NameAdapterCallback nameAdapterCallback){
        this.context = context;
        this.nameAdapterCallback = nameAdapterCallback;
    }

    @NonNull
    @Override
    public NameAdapter.NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.custom_name_list, parent, false);
        return new NameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NameAdapter.NameViewHolder holder, int position) {
        ((NameViewHolder) holder).setName(nameModelList.get(position));
        ((NameViewHolder) holder).checkBox_name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                nameModelList.get(position).setIsCheck(isChecked);
//                notifyItemChanged(position);
                nameAdapterCallback.onNameAdapterClickCheck(nameModelList.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(nameModelList != null){
            return  nameModelList.size();
        }
        return 0;
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {
        private TextView text_name;
        private CheckBox checkBox_name;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
            text_name = (TextView) itemView.findViewById(R.id.text_name);
            checkBox_name = (CheckBox) itemView.findViewById(R.id.checkBox_name);
        }

        public void setName(NameModel nameModel){
            text_name.setText(nameModel.getName());
            checkBox_name.setChecked(nameModel.getIsCheck());
        }
    }

    public void addNasme(String name){
        nameModelList.add(new NameModel(
                name, false
        ));
        notifyItemInserted(nameModelList.size() - 1);
    }

    public void removeItem(){
        List<NameModel> _nameModelList = new ArrayList<>();
        for(int i = 0;i < nameModelList.size();i++){
            if(!nameModelList.get(i).getIsCheck()){
                _nameModelList.add(nameModelList.get(i));
            }
        }
        nameModelList = _nameModelList;
        notifyDataSetChanged();
    }

    public List<NameModel> getNameModelList() {
        return nameModelList;
    }

    public void setNameModelList(ArrayList<NameModel> nameModelList){
        this.nameModelList = nameModelList;
        notifyDataSetChanged();
    }

    public interface NameAdapterCallback {
        void onNameAdapterClickCheck(NameModel nameModel, int position);
    }
}
