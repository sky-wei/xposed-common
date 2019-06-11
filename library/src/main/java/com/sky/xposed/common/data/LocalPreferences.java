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

package com.sky.xposed.common.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.sky.xposed.common.interfaces.XPreferences;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by sky on 2019-06-05.
 */
public class LocalPreferences implements XPreferences {

    private Context mContext;
    private SimplePreferences mSimplePreferences;
    private Map<String, XPreferences> mManagerMap = new HashMap<>();

    private LocalPreferences(Build build) {
        mContext = build.mContext;
        mSimplePreferences = new SimplePreferences(build.mContext, build.mName);
    }

    @Override
    public String getString(String key, String defValue) {
        return mSimplePreferences.getString(key, defValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return mSimplePreferences.getBoolean(key, defValue);
    }

    @Override
    public int getInt(String key, int defValue) {
        return mSimplePreferences.getInt(key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return mSimplePreferences.getFloat(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return mSimplePreferences.getLong(key, defValue);
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defValue) {
        return mSimplePreferences.getStringSet(key, defValue);
    }

    @Override
    public XPreferences putString(String key, String value) {
        return mSimplePreferences.putString(key, value);
    }

    @Override
    public XPreferences putBoolean(String key, boolean value) {
        return mSimplePreferences.putBoolean(key, value);
    }

    @Override
    public XPreferences putInt(String key, int value) {
        return mSimplePreferences.putInt(key, value);
    }

    @Override
    public XPreferences putFloat(String key, float value) {
        return mSimplePreferences.putFloat(key, value);
    }

    @Override
    public XPreferences putLong(String key, long value) {
        return mSimplePreferences.putLong(key, value);
    }

    @Override
    public XPreferences putStringSet(String key, Set<String> value) {
        return mSimplePreferences.putStringSet(key, value);
    }

    @Override
    public XPreferences remove(String key) {
        return mSimplePreferences.remove(key);
    }

    @Override
    public XPreferences clear() {
        return mSimplePreferences.clear();
    }

    @Override
    public XPreferences getPreferences(String name) {

        if (mManagerMap.containsKey(name)) {
            // 存在了直接返回
            return mManagerMap.get(name);
        }

        // 创建对象
        SimplePreferences preferences = new SimplePreferences(mContext, name);
        mManagerMap.put(name, preferences);

        return preferences;
    }

    @Override
    public void release() {
        mManagerMap.clear();
    }

    /**
     * 一个简单属性配置管理类
     */
    private final class SimplePreferences implements XPreferences {

        private SharedPreferences mSharedPreferences;

        public SimplePreferences(Context context, String name) {
            mSharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }

        @Override
        public String getString(String key, String defValue) {
            return mSharedPreferences.getString(key, defValue);
        }

        @Override
        public boolean getBoolean(String key, boolean defValue) {
            return mSharedPreferences.getBoolean(key, defValue);
        }

        @Override
        public int getInt(String key, int defValue) {
            return mSharedPreferences.getInt(key, defValue);
        }

        @Override
        public float getFloat(String key, float defValue) {
            return mSharedPreferences.getFloat(key, defValue);
        }

        @Override
        public long getLong(String key, long defValue) {
            return mSharedPreferences.getLong(key, defValue);
        }

        @Override
        public Set<String> getStringSet(String key, Set<String> defValue) {
            return mSharedPreferences.getStringSet(key, defValue);
        }

        @Override
        public XPreferences putString(String key, String value) {
            mSharedPreferences.edit().putString(key, value).apply();
            return this;
        }

        @Override
        public XPreferences putBoolean(String key, boolean value) {
            mSharedPreferences.edit().putBoolean(key, value).apply();
            return this;
        }

        @Override
        public XPreferences putInt(String key, int value) {
            mSharedPreferences.edit().putInt(key, value).apply();
            return this;
        }

        @Override
        public XPreferences putFloat(String key, float value) {
            mSharedPreferences.edit().putFloat(key, value).apply();
            return this;
        }

        @Override
        public XPreferences putLong(String key, long value) {
            mSharedPreferences.edit().putLong(key, value).apply();
            return this;
        }

        @Override
        public XPreferences putStringSet(String key, Set<String> value) {
            mSharedPreferences.edit().putStringSet(key, value).apply();
            return this;
        }

        @Override
        public XPreferences remove(String key) {
            mSharedPreferences.edit().remove(key).apply();
            return this;
        }

        @Override
        public XPreferences clear() {
            mSharedPreferences.edit().clear().apply();
            return this;
        }

        @Override
        public XPreferences getPreferences(String name) {
            throw new IllegalArgumentException("不支持当前操作");
        }

        @Override
        public void release() {
            throw new IllegalArgumentException("不支持当前操作");
        }
    }

    public static class Build {

        private Context mContext;
        private String mName;

        public Build(Context context) {
            mContext = context;
        }

        public Build setConfigName(String name) {
            mName = name;
            return this;
        }

        public XPreferences build() {
            return new LocalPreferences(this);
        }
    }
}
