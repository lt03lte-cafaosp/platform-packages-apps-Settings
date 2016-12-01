/*
 * Copyright (c) 2016, The Linux Foundation. All rights reserved.
 *
 * Not a Contribution.
 *
 * Copyright (C) 2012 The Android Open Source Project
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

import android.app.StatusBarManager;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class WifiSubsidyLockActivity extends WifiSetupActivity {
    private StatusBarManager mStatusBarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStatusBarManager = (StatusBarManager)
            this.getSystemService(Context.STATUS_BAR_SERVICE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (null != mStatusBarManager) {
            mStatusBarManager.disable(StatusBarManager.DISABLE_EXPAND);
        }
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return WifiSettingsForSubsidyLock.class.getName().equals(fragmentName);
    }

    @Override
    /* package */Class<? extends PreferenceFragment> getWifiSettingsClass() {
        return WifiSettingsForSubsidyLock.class;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != mStatusBarManager) {
            mStatusBarManager.disable(StatusBarManager.DISABLE_NONE);
        }
    }
}
