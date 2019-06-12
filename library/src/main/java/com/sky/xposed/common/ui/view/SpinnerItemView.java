/*
 * Copyright (c) 2018 The sky Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.xposed.common.ui.view;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky.xposed.common.ui.util.LayoutUtil;
import com.sky.xposed.common.ui.util.ViewUtil;
import com.sky.xposed.common.util.DisplayUtil;

/**
 * Created by sky on 2018/8/20.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class SpinnerItemView extends XFrameItemView<String> implements View.OnClickListener {

    private TextView tvName;
    private TextView tvDesc;
    private TextView tvValue;
    private OnValueChangeListener mOnValueChangeListener;
    private String[] mDisplayItems;

    public SpinnerItemView(Context context) {
        super(context);
    }

    public SpinnerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpinnerItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OnValueChangeListener getOnValueChangeListener() {
        return mOnValueChangeListener;
    }

    public void setOnValueChangeListener(OnValueChangeListener onValueChangeListener) {
        mOnValueChangeListener = onValueChangeListener;
    }

    @Override
    protected void initView() {

        int left = DisplayUtil.dip2px(getContext(), 15);

        setPadding(left, 0, left, 0);
        setBackground(ViewUtil.newBackgroundDrawable());
        setLayoutParams(LayoutUtil.newViewGroupParams(
                LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(getContext(), 40)));

        LinearLayout tvLayout = new LinearLayout(getContext());
        tvLayout.setOrientation(LinearLayout.VERTICAL);

        tvName = new TextView(getContext());
        tvName.setTextColor(Color.BLACK);
        tvName.setTextSize(15);

        tvDesc = new TextView(getContext());
        tvDesc.setTextColor(Color.GRAY);
        tvDesc.setTextSize(9);
        tvDesc.setPadding(DisplayUtil.dip2px(getContext(), 1), 0, 0, 0);

        tvLayout.addView(tvName);
        tvLayout.addView(tvDesc);

        FrameLayout.LayoutParams params = LayoutUtil.newWrapFrameLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL;

        addView(tvLayout, params);

        // 扩展的Layout
        LinearLayout extendLayout = new LinearLayout(getContext());
        extendLayout.setGravity(Gravity.CENTER_VERTICAL);
        extendLayout.setOrientation(LinearLayout.HORIZONTAL);

        tvValue = new TextView(getContext());
        tvValue.setTextColor(Color.BLACK);
        tvValue.setTextSize(14);

        int pLeft = DisplayUtil.dip2px(getContext(), 10);
        TextView tvSymbol = new TextView(getContext());
        tvSymbol.setTextColor(Color.BLACK);
        tvSymbol.setPadding(pLeft, 0, pLeft, 0);
        tvSymbol.setTextSize(20);
        tvSymbol.setText("▾");

        extendLayout.addView(tvValue);
        extendLayout.addView(tvSymbol);

        params = LayoutUtil.newFrameLayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;

        addView(extendLayout, params);

        setOnClickListener(this);
    }

    public TextView getNameView() {
        return tvName;
    }

    public TextView getDescView() {
        return tvDesc;
    }

    public TextView getValueView() {
        return tvValue;
    }

    public void setChooseItem(String... items) {

        if (items == null) return;

        mDisplayItems = items;
    }

    public void setName(String title) {
        tvName.setText(title);
    }

    public String getName() {
        return tvName.getText().toString();
    }

    public void setDesc(String desc) {
        tvDesc.setText(desc);
        ViewUtil.setVisibility(tvDesc,
                TextUtils.isEmpty(desc) ? View.GONE : View.VISIBLE);
    }

    public String getDesc() {
        return tvDesc.getText().toString();
    }

    public void setValue(String value) {
        tvValue.setText(value);
    }

    public String getValue() {
        return tvValue.getText().toString();
    }

    @Override
    public void onClick(View v) {

        if (mDisplayItems == null) return;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setItems(mDisplayItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mOnValueChangeListener != null) {
                    String item1 = mDisplayItems[which];
                    mOnValueChangeListener.onValueChanged(SpinnerItemView.this, item1);
                }
            }
        });
        builder.setTitle(getName());
        builder.show();
    }

    @Override
    protected void bindView() {

        setValue(getKeyValue());
        setOnValueChangeListener(new OnValueChangeListener() {

            @Override
            public void onValueChanged(View view, String item) {

                if (mStatusChangeListener == null) {
                    // 保存信息
                    setValue(item);
                    mPreferences.putString(mKey, item);
                    return;
                }

                if (mStatusChangeListener.onStatusChange(view, mKey, item)) {
                    // 保存信息
                    setValue(item);
                    mPreferences.putString(mKey, item);
                }
            }
        });
    }

    @Override
    public String getKeyValue() {
        return mPreferences.getString(mKey, mDefValue);
    }

    public interface OnValueChangeListener {

        void onValueChanged(View view, String value);
    }
}
