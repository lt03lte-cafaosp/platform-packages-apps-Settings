/*
 * Copyright (c) 2016, The Linux Foundation. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *    * Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 *      copyright notice, this list of conditions and the following
 *      disclaimer in the documentation and/or other materials provided
 *      with the distribution.
 *    * Neither the name of The Linux Foundation nor the names of its
 *      contributors may be used to endorse or promote products derived
 *      from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
 * IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
// Copyright 2011 Google Inc. All Rights Reserved.

package com.android.settings.deviceinfo;

import android.content.Context;
import android.os.Environment.UserEnvironment;
import android.os.UserHandle;
import android.util.AttributeSet;
import android.view.ViewDebug;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.settings.R;

/**
 * Handles display of a single row entry on Settings --> Storage --> Misc Files screen
 */
public class FileItemInfoLayout extends RelativeLayout implements Checkable {
    private TextView mFileNameView;
    private TextView mFileSizeView;
    private CheckBox mCheckbox;

    private static final int sLengthExternalStorageDirPrefix = new UserEnvironment(
            UserHandle.myUserId()).getExternalStorageDirectory().getAbsolutePath().length() + 1;

    public FileItemInfoLayout(Context context) {
        this(context, null);
    }

    public FileItemInfoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FileItemInfoLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void toggle() {
        setChecked(!mCheckbox.isChecked());
    }

    /* (non-Javadoc)
     * @see android.view.View#onFinishInflate()
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mFileNameView = (TextView) findViewById(R.id.misc_filename);
        mFileSizeView = (TextView) findViewById(R.id.misc_filesize);
        mCheckbox = (CheckBox) findViewById(R.id.misc_checkbox);
    }

    public void setFileName(String fileName) {
        mFileNameView.setText(fileName.substring(sLengthExternalStorageDirPrefix));
    }

    public void setFileSize(String filesize) {
        mFileSizeView.setText(filesize);
    }

    @ViewDebug.ExportedProperty
    public boolean isChecked() {
        return mCheckbox.isChecked();
    }

    public CheckBox getCheckBox() {
        return mCheckbox;
    }

    /**
     * <p>Changes the checked state of this text view.</p>
     *
     * @param checked true to check the text, false to uncheck it
     */
    public void setChecked(boolean checked) {
        mCheckbox.setChecked(checked);
    }
}