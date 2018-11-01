package com.example.administrator.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/29.
 */

public class EvaluateAdapter extends RecyclerView.Adapter<EvaluateAdapter.ViewHolder> {

    private List<Evaluate> mEvaluteList;


    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView evaluate;
        TextView evaluatetime;

        public ViewHolder(View itemView) {
            super(itemView);
            evaluate = itemView.findViewById(R.id.evaluate);
            evaluatetime = itemView.findViewById(R.id.time);
        }
    }

    public EvaluateAdapter(List<Evaluate> evaluteList){
        mEvaluteList = evaluteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EvaluateAdapter.ViewHolder holder, int position) {
        Evaluate evaluate = mEvaluteList.get(position);
        holder.evaluate.setText(evaluate.getEvaluate());
        holder.evaluatetime.setText(evaluate.getCreatedAt().toString());
    }

    @Override
    public int getItemCount() {
        return mEvaluteList.size();
    }




}
