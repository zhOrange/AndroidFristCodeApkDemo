package com.zh.orangeweather.db;

import org.litepal.crud.LitePalSupport;

/**
 * 数据库 Province表.
 */
public class Province extends LitePalSupport {
    /**
     * id
     */
    private int id;
    /**
     * Province 名称
     */
    private String provinceName;
    /**
     * province 代号
     */
    private int provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
