/*
 * Copyright (C) 2009 The Android Open Source Project
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

package com.android.inputmethod.pinyin;

import java.util.Vector;

import android.content.Context;

/**
 * Class used to cache previously loaded soft keyboard layouts.
 *
 * 类，用来缓存预先加载的键盘布局。
 */
    //Skb池是用来缓存SoftKeyboard键盘的，其内部放置的SoftKeyboard。而不是SoftKeyboardView
public class SkbPool {
    private static SkbPool mInstance = null;

    private Vector<SkbTemplate> mSkbTemplates = new Vector<SkbTemplate>();
    private Vector<SoftKeyboard> mSoftKeyboards = new Vector<SoftKeyboard>();//Vector类值得学习。

    private SkbPool() {
    }

    public static SkbPool getInstance() {
        if (null == mInstance) mInstance = new SkbPool();
        return mInstance;
    }

    public void resetCachedSkb() {
        mSoftKeyboards.clear();
    }

    public SkbTemplate getSkbTemplate(int skbTemplateId, Context context) {
        for (int i = 0; i < mSkbTemplates.size(); i++) {
            SkbTemplate t = mSkbTemplates.elementAt(i);
            if (t.getSkbTemplateId() == skbTemplateId) {
                return t;
            }
        }

        if (null != context) {
            XmlKeyboardLoader xkbl = new XmlKeyboardLoader(context);
            SkbTemplate t = xkbl.loadSkbTemplate(skbTemplateId);
            if (null != t) {
                mSkbTemplates.add(t);
                return t;
            }
        }
        return null;
    }

    // Try to find the keyboard in the pool with the cache id. If there is no
    // keyboard found, try to load it with the given xml id.
    public SoftKeyboard getSoftKeyboard(int skbCacheId, int skbXmlId,
            int skbWidth, int skbHeight, Context context) {
        for (int i = 0; i < mSoftKeyboards.size(); i++) {
            SoftKeyboard skb = mSoftKeyboards.elementAt(i);
            if (skb.getCacheId() == skbCacheId && skb.getSkbXmlId() == skbXmlId) {
                skb.setSkbCoreSize(skbWidth, skbHeight);
                skb.setNewlyLoadedFlag(false);
                return skb;
            }
        }
        //如果没找到匹配的布局，则加载布局。另外：搜索整个项目，发现XmlKeyboardLoader类，只在这里使用了该类。
        if (null != context) {
            XmlKeyboardLoader xkbl = new XmlKeyboardLoader(context);
            SoftKeyboard skb = xkbl.loadKeyboard(skbXmlId, skbWidth, skbHeight);//加载键盘布局
            if (skb != null) {
                if (skb.getCacheFlag()) {
                    skb.setCacheId(skbCacheId);
                    mSoftKeyboards.add(skb);
                }
            }
            return skb;
        }
        return null;
    }
}
