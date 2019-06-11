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

package com.sky.xposed.common.app.ui.dialog;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.sky.xposed.common.app.BuildConfig;
import com.sky.xposed.common.app.Constant;
import com.sky.xposed.common.app.R;
import com.sky.xposed.common.app.ui.base.BaseDialog;
import com.sky.xposed.common.interfaces.TrackViewStatus;
import com.sky.xposed.common.ui.util.ViewUtil;
import com.sky.xposed.common.ui.view.CommonFrameLayout;
import com.sky.xposed.common.ui.view.SimpleItemView;
import com.sky.xposed.common.ui.view.SpinnerItemView;
import com.sky.xposed.common.ui.view.SwitchItemView;
import com.sky.xposed.common.ui.view.TitleView;
import com.sky.xposed.common.util.ResourceUtil;
import com.squareup.picasso.Picasso;

public class SettingsDialog extends BaseDialog {

    private TitleView mToolbar;
    private CommonFrameLayout mCommonFrameLayout;

    private SwitchItemView sivAutoPlay;
    private SpinnerItemView sivTest;
    private SwitchItemView sivAutoAttention;
    private SwitchItemView sivAutoLike;
    private SwitchItemView sivAutoComment;
    private SimpleItemView etiAutoCommentList;
    private SwitchItemView sivAutoSaveVideo;
    private SwitchItemView sivRemoveLimit;
    private SimpleItemView sivMoreSettings;
    private SimpleItemView sivDonate;
    private SimpleItemView sivAliPayHb;
    private SimpleItemView sivAbout;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {

        // 不显示默认标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        mCommonFrameLayout = new CommonFrameLayout(getContext());
        mToolbar = mCommonFrameLayout.getTitleView();

        sivAutoPlay = ViewUtil.newSwitchItemView(getContext(), "自动播放", "自动播放");
        sivTest = ViewUtil.newSpinnerItemView(getContext(), "操作方式", "", "默认", "定时");
        sivAutoAttention = ViewUtil.newSwitchItemView(getContext(), "自动关注");
        sivAutoLike = ViewUtil.newSwitchItemView(getContext(), "自动点赞");
        sivAutoComment = ViewUtil.newSwitchItemView(getContext(), "自动评论");
        etiAutoCommentList = ViewUtil.newSimpleItemView(getContext(), "评论内容");
        sivRemoveLimit = ViewUtil.newSwitchItemView(getContext(), "解除视频时间限制");
        sivMoreSettings = ViewUtil.newSimpleItemView(getContext(), "更多设置");
        sivDonate = ViewUtil.newSimpleItemView(getContext(), "支持我们");
        sivAliPayHb = ViewUtil.newSimpleItemView(getContext(), "领取红包(每日一次)");
        sivAbout = ViewUtil.newSimpleItemView(getContext(), "关于");

        sivAutoSaveVideo = ViewUtil.newSwitchItemView(getContext(), "自动保存视频");

        return mCommonFrameLayout
                .addContent(sivAutoPlay, true)
                .addContent(sivTest, true)
                .addContent(sivAutoAttention, true)
                .addContent(sivAutoLike, true)
                .addContent(sivAutoComment, true)
                .addContent(etiAutoCommentList, true)
                .addContent(sivAutoSaveVideo, true)
                .addContent(sivRemoveLimit, true)
                .addContent(sivMoreSettings, true)
                .addContent(sivDonate, true)
                .addContent(sivAliPayHb, true)
                .addContent(sivAbout);
    }

    @Override
    protected void initView(View view, Bundle args) {

        mToolbar.setTitle(Constant.Name.TITLE);

        mToolbar.showBack();
        Picasso.get().load(ResourceUtil.resourceIdToUri(BuildConfig.APPLICATION_ID, R.drawable.ic_action_arrow_back)).into(mToolbar.getBackView());
        mToolbar.setOnBackEventListener(new TitleView.OnBackEventListener() {
            @Override
            public void onEvent(View view) {
                dismiss();
            }
        });

        ImageButton newMoreButton = mToolbar.addMoreImageButton();
        Picasso.get().load(ResourceUtil.resourceIdToUri(BuildConfig.APPLICATION_ID, R.drawable.ic_action_more_vert)).into(newMoreButton);
        newMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v, Gravity.RIGHT);
                Menu menu = popupMenu.getMenu();

                menu.add(1, 1, 1, "导入配置");
                menu.add(1, 2, 1, "导出配置");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        sivMoreSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("测试标题");
                builder.setMessage("哈哈，哈哈，哈哈，哈哈，哈哈，哈哈，哈哈，哈哈，哈哈，哈哈，哈哈，哈哈，哈哈，哈哈，哈哈，哈哈，哈哈，");
                builder.setPositiveButton("确认", null);
                builder.show();
            }
        });

        trackBind(sivTest, "a", "默认", mStringChangeListener);
    }

    private TrackViewStatus.StatusChangeListener<String> mStringChangeListener = new TrackViewStatus.StatusChangeListener<String>() {
        @Override
        public boolean onStatusChange(View view, String key, String value) {
            sendRefreshPreferenceBroadcast(key, value);
            return true;
        }
    };

    private TrackViewStatus.StatusChangeListener<Boolean> mBooleanChangeListener = new TrackViewStatus.StatusChangeListener<Boolean>() {
        @Override
        public boolean onStatusChange(View view, String key, Boolean value) {
            sendRefreshPreferenceBroadcast(key, value);
            return true;
        }
    };
}
