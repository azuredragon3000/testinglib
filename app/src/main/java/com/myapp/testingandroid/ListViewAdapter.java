package com.myapp.testingandroid;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    final ArrayList<Product> listProduct;

    ListViewAdapter(ArrayList<Product> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public int getCount() {
        return listProduct.size();
    }

    @Override
    public Object getItem(int i) {
        return listProduct.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listProduct.get(i).productID;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View listview_item;
        if(view == null){
            listview_item = View.inflate(viewGroup.getContext(),R.layout.listview_item,null);
        }else{
            listview_item = view;
        }

        Product product = (Product) getItem(i);
        ((TextView) listview_item.findViewById(R.id.idproduct)).setText(String.format("ID = %d", product.productID));
        ((TextView) listview_item.findViewById(R.id.nameproduct)).setText(String.format("Tên SP : %s", product.name));
        ((TextView) listview_item.findViewById(R.id.priceproduct)).setText(String.format("Giá %d", product.price));


        return listview_item;

        //return null;
    }
}
