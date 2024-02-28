package com.example.bitcoinwalletapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitcoinwalletapp.R;
import com.example.bitcoinwalletapp.models.CurrencyItem;

import java.util.List;

/**
 * @author tlhogi-manye
 * @date 28/02/2024
 * adapter to handle the currency conversion rate amounts
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private List<CurrencyItem> currencyList;
    private Context context;

    public CurrencyAdapter(Context context, List<CurrencyItem> currencyList) {
        this.context = context;
        this.currencyList = currencyList;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_currency, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        CurrencyItem currencyItem = currencyList.get(position);

        // Set data to views
        holder.iconCurrency.setImageResource(currencyItem.getIconResourceId());
        holder.textCurrencyCode.setText(currencyItem.getCurrencyCode());
        holder.textCurrencyValue.setText(currencyItem.getCurrencyValue());
        holder.textFluctuation.setText(currencyItem.getFluctuationPercentage());
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    static class CurrencyViewHolder extends RecyclerView.ViewHolder {
        ImageView iconCurrency;
        TextView textCurrencyCode;
        TextView textCurrencyValue;
        TextView textFluctuation;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            iconCurrency = itemView.findViewById(R.id.icnCurrency);
            textCurrencyCode = itemView.findViewById(R.id.txtCurrencyCode);
            textCurrencyValue = itemView.findViewById(R.id.txtCurrencyValue);
            textFluctuation = itemView.findViewById(R.id.txtFluctuation);
        }
    }
}
