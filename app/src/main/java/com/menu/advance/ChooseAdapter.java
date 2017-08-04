package com.menu.advance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by MkfsSion on 2017/8/4.
 */

public class ChooseAdapter extends ArrayAdapter<String> {
    RadioButton radioButton;
    boolean[] checks;
    public ChooseAdapter(Context context, String[] values,boolean[] checks)
    {
        super(context, R.layout.sample,values);
        this.checks = checks;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.sample, parent, false);
        String text = getItem(position);
        radioButton = (RadioButton) view.findViewById(R.id.radio);
        radioButton.setClickable(false);
        radioButton.setChecked(checks[position]);
        TextView textView = (TextView) view.findViewById(R.id.radiotext);
        textView.setText(text);
        return  view;
    }

}
