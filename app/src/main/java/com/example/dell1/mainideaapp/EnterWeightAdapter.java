package com.example.dell1.mainideaapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class EnterWeightAdapter extends RecyclerView.Adapter<EnterWeightAdapter.ViewHolder> {


    Context context;
    private MyGroups clickedGrp;
    public static ArrayList<EditModel> editModelArrayList;

    public EnterWeightAdapter(Context context, MyGroups clickedGrp, ArrayList<EditModel> editModelArrayList) {
        this.editModelArrayList = editModelArrayList;
        this.context = context;
        this.clickedGrp = clickedGrp;
    }

    @NonNull
    @Override
    public EnterWeightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_enter_weight_to_split, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnterWeightAdapter.ViewHolder holder, int position) {

        GroupMembers currentGrpMember = clickedGrp.getGroupMembersArrayList().get(position);
        String initial = currentGrpMember.getName().substring(0, 1);
        holder.textView1.setText(initial);
        holder.textView2.setText(currentGrpMember.getName());
        holder.editText.setText(editModelArrayList.get(position).getEditTextValue());
    }

    @Override
    public int getItemCount() {
        return editModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        TextView textView2;
        EditText editText;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.tv_itemrowenterwtforsplitIcon);
            textView2 = itemView.findViewById(R.id.tv_itemrowenterwtforsplitName);
            editText = itemView.findViewById(R.id.etAddWeighttoSplit);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!editText.getText().toString().equals(""))
                        editModelArrayList.get(getAdapterPosition()).setEditTextValue(editText.getText().toString());
                    else {
                        editModelArrayList.get(getAdapterPosition()).setEditTextValue("0");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }
}