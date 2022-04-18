package com.zj.tableNode;

public class GoodsType {
    private Integer id;

    private String typeEname;

    private String typeCname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeEname() {
        return typeEname;
    }

    public void setTypeEname(String typeEname) {
        this.typeEname = typeEname == null ? null : typeEname.trim();
    }

    public String getTypeCname() {
        return typeCname;
    }

    public void setTypeCname(String typeCname) {
        this.typeCname = typeCname == null ? null : typeCname.trim();
    }
}