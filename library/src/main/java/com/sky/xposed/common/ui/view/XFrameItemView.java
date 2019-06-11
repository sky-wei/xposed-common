/*
 * Copyright (c) 2019 The sky Authors.
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

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.sky.xposed.common.interfaces.TrackViewStatus;
import com.sky.xposed.common.interfaces.XPreferences;

/**
 * Created by sky on 2019-06-11.
 */
public abstract class XFrameItemView<T> extends FrameLayout implements TrackViewStatus<T> {

    protected XPreferences mPreferences;
    protected StatusChangeListener<T> mStatusChangeListener;
    protected String mKey;
    protected T mDefValue;

    public XFrameItemView(Context context) {
        this(context, null);
    }

    public XFrameItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XFrameItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化View
     */
    protected abstract void initView();

    protected abstract void bindView();

    public abstract T getKeyValue();

    @Override
    public TrackViewStatus<T> setPreferences(XPreferences preferences) {
        mPreferences = preferences;
        return this;
    }

    @Override
    public TrackViewStatus<T> bind(String key, T defValue) {
        mKey = key;
        mDefValue = defValue;
        bindView();
        return this;
    }

    @Override
    public TrackViewStatus<T> track(StatusChangeListener<T> listener) {
        mStatusChangeListener = listener;
        return this;
    }

    public String getKey() {
        return mKey;
    }

    public StatusChangeListener<T> getStatusChangeListener() {
        return mStatusChangeListener;
    }
}
