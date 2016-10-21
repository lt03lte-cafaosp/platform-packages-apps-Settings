/*
 * Copyright (c) 2016, The Linux Foundation. All rights reserved.
 *
 * Not a Contribution.
 *
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.wifi;

import android.app.Dialog;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.android.settings.R;
import com.android.settings.SetupWizardUtils;
import com.android.setupwizardlib.SetupWizardListLayout;
import com.android.setupwizardlib.view.NavigationBar;

/**
 * This customized version of WifiSettings is shown to the user only during
 * Subsidy Lock.
 */
public class WifiSettingsForSubsidyLock extends WifiSettings {

    private static final String TAG = "WifiSettingsForSetupWizard";

    private SetupWizardListLayout mLayout;
    private View mAddOtherNetworkItem;
    private TextView mEmptyFooter;
    private boolean mListLastEmpty = false;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        mLayout = (SetupWizardListLayout) inflater.inflate(
                R.layout.setup_wifi_layout, container, false);

        final NavigationBar navigationBar = mLayout.getNavigationBar();
        if (navigationBar != null) {
            navigationBar.setVisibility(View.GONE);
        }
        return mLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WindowManager.LayoutParams lp = getActivity()
            .getWindow().getAttributes();
        lp.flags |= WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAccessPointsChanged() {
        super.onAccessPointsChanged();
    }

    @Override
    public void onWifiStateChanged(int state) {
        super.onWifiStateChanged(state);
    }

    @Override
    public void registerForContextMenu(View view) {
    }

    @Override
    /* package */WifiEnabler createWifiEnabler() {
        // Not shown during setup wizard
        return super.createWifiEnabler();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do not show menu during setup wizard
    }

    @Override
    public Dialog onCreateDialog(int dialogId) {
        final Dialog dialog = super.onCreateDialog(dialogId);
        dialog.getWindow().setSoftInputMode(WindowManager
                .LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        dialog.getWindow().addFlags(WindowManager
                .LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        SetupWizardUtils.applyImmersiveFlags(dialog);
        return dialog;
    }

    @Override
    protected void connect(final WifiConfiguration config) {
        WifiSetupActivity activity = (WifiSetupActivity) getActivity();
        activity.networkSelected();
        super.connect(config);
    }

    @Override
    protected void connect(final int networkId) {
        WifiSetupActivity activity = (WifiSetupActivity) getActivity();
        activity.networkSelected();
        super.connect(networkId);
    }

    @Override
    protected TextView initEmptyView() {
        final LayoutInflater inflater = LayoutInflater.from(getActivity());
        mEmptyFooter = (TextView) inflater.inflate(
                R.layout.setup_wifi_empty, getListView(), false);
        return mEmptyFooter;
    }

    @Override
    public View setPinnedHeaderView(int layoutResId) {
        // Pinned header is not supported in setup wizard
        return null;
    }

    @Override
    public void setPinnedHeaderView(View pinnedHeader) {
        // Pinned header is not supported in setup wizard
    }

    @Override
    protected void setProgressBarVisible(boolean visible) {
        if (mLayout != null) {
            if (visible) {
                mLayout.showProgressBar();
            } else {
                mLayout.hideProgressBar();
            }
        }
    }
}
