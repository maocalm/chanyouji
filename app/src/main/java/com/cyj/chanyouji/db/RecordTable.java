package com.cyj.chanyouji.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by mandy on 2016/11/5.
 */
@Table(name = "record")
public class RecordTable {
    @Column(name = "id",isId = true,autoGen = true)
    private String id;
    @Column(name = "money")
    private String money;
    @Column(name = "qita")
    private String qita;
    @Column(name = "huobi")
    private String huobi;
    @Column(name = "mingxi")
    private String mingxi;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getQita() {
        return qita;
    }

    public void setQita(String qita) {
        this.qita = qita;
    }

    public String getHuobi() {
        return huobi;
    }

    public void setHuobi(String huobi) {
        this.huobi = huobi;
    }

    public String getMingxi() {
        return mingxi;
    }

    public void setMingxi(String mingxi) {
        this.mingxi = mingxi;
    }



    public RecordTable(String money, String qita, String huobi, String mingxi) {
        this.money = money;
        this.qita = qita;
        this.huobi = huobi;
        this.mingxi = mingxi;
    }

    public RecordTable() {}
}
