/*
 * Copyright (C) 2015 The Android Open Source Project
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

package com.android.settings;

import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;

import com.android.internal.logging.MetricsLogger;

/**
 * Instrumented fragment that logs visibility state.
 */
public abstract class InstrumentedFragment extends PreferenceFragment {
    // Declare new temporary categories here, starting after this value.
    public static final int UNDECLARED = 100000;

    public static final int ACCESSIBILITY_TOGGLE_AUTOCLICK = UNDECLARED + 1;
    public static final int SOUND = UNDECLARED + 2;
    public static final int CONFIGURE_NOTIFICATION = UNDECLARED + 3;
    public static final int CONFIGURE_WIFI = UNDECLARED + 4;
    public static final int DISPLAY_SCREEN_ZOOM = UNDECLARED + 5;
    public static final int ACCESSIBILITY_FONT_SIZE = UNDECLARED + 6;
    public static final int DATA_USAGE_LIST = UNDECLARED + 7;
    public static final int BILLING_CYCLE = UNDECLARED + 8;
    public static final int APP_DATA_USAGE = UNDECLARED + 9;
    public static final int USER_LOCALE_LIST = UNDECLARED + 10;

    /**
     * Declare the view of this category.
     *
     * Categories are defined in {@link com.android.internal.logging.MetricsLogger}
     * or if there is no relevant existing category you may define one in
     * {@link com.android.settings.InstrumentedFragment}.
     */
    protected abstract int getMetricsCategory();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    }

    @Override
    public void onResume() {
        super.onResume();
        MetricsLogger.visible(getActivity(), getMetricsCategory());
    }

    @Override
    public void onPause() {
        super.onPause();
        MetricsLogger.hidden(getActivity(), getMetricsCategory());
    }
}