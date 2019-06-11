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

package com.sky.xposed.common.app;

import android.app.Application;

import com.sky.xposed.common.ui.interfaces.XPreferences;
import com.sky.xposed.common.util.ToastUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by sky on 2018/8/17.
 */
public class App extends Application {

    private XPreferences mPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化
        ToastUtil.getInstance().init(this);
        Picasso.setSingletonInstance(new Picasso.Builder(this).build());

        mPreferences = new CorePreferences.Build(this)
                .setConfigName(Constant.Name.XPOSED)
                .build();
    }

    public XPreferences getPreferences() {
        return mPreferences;
    }
}
