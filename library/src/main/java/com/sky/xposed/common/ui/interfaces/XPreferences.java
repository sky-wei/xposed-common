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

package com.sky.xposed.common.ui.interfaces;

import java.util.Set;

/**
 * Created by sky on 2019-06-05.
 */
public interface XPreferences {

    /**
     * 获取字符串
     * @param key
     * @param defValue
     * @return
     */
    String getString(String key, String defValue);

    boolean getBoolean(String key, boolean defValue);

    int getInt(String key, int defValue);

    float getFloat(String key, float defValue);

    long getLong(String key, long defValue);

    Set<String> getStringSet(String key, Set<String> defValue);

    XPreferences putString(String key, String value);

    XPreferences putBoolean(String key, boolean value);

    XPreferences putInt(String key, int value);

    XPreferences putFloat(String key, float value);

    XPreferences putLong(String key, long value);

    XPreferences putStringSet(String key, Set<String> value);

    XPreferences remove(String key);

    XPreferences clear();

    /**
     * 获取指定名称的配置管理对象
     * @param name
     * @return
     */
    XPreferences getPreferences(String name);

    void release();
}
