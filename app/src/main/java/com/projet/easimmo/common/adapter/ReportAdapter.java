package com.projet.easimmo.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.ReportDTO;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by victor on 08/05/2016.
 */
public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder>{

    private List<ReportDTO> mReportList;

    public ReportAdapter(List<ReportDTO> mReportList) {

        this.mReportList = mReportList;
        //System.out.println("Size****************************: "+ mPropertyList.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_small_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReportDTO report = mReportList.get(position);
        if(report.getmType() != null){
            holder.mNameTxv.setText(report.getmType()+"_"+report.getmId().substring(0,9));
        }else {
            holder.mNameTxv.setText("Report_" + report.getmId().substring(0, 9));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(report.getmCreated_at());
        holder.mDateTxv.setText(dateString);
    }

    @Override
    public int getItemCount() {
        if(mReportList != null) {
            return mReportList.size();
        }else{
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTxv;
        private TextView mDateTxv;

        public ViewHolder(View v) {
            super(v);
            mNameTxv = (TextView) v.findViewById(R.id.report_name);
            mDateTxv = (TextView) v.findViewById(R.id.report_date);
        }
    }


}
