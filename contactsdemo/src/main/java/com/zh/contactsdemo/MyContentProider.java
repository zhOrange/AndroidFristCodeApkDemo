package com.zh.contactsdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProider extends ContentProvider {

    private static final int TABLE_1_All = 1;
    private static final int TABLE_1_ITEM = 2;
    private static final int TABLE_2_All = 3;
    private static final int TABLE_2_ITEM = 4;

    private static UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.zh.contactsdemo.provider", "table1", TABLE_1_All);
        uriMatcher.addURI("com.zh.contactsdemo.provider", "table1/#", TABLE_1_ITEM);
        uriMatcher.addURI("com.zh.contactsdemo.provider", "table2", TABLE_2_All);
        uriMatcher.addURI("com.zh.contactsdemo.provider", "table2/#", TABLE_2_ITEM);
    }


    /**
     * 创建ContentProvider调用。
     * @return
     */
    @Override
    public boolean onCreate() {
        return false;
    }

    /**
     * 数据查询
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //return null;
        switch (uriMatcher.match(uri)){
            case TABLE_1_All:
                break;
            default:break;
        }
        return null;
    }

    /**
     * 根据传入内容的URI返回相应的MIME类型。
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    /**
     * 数据插入
     * @param uri
     * @param values
     * @return
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    /**
     * 删除数据
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /**
     * 更新修改数据
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
