package com.cyj.chanyouji.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by maocalm on 2016/11/7.
 */
@Table(name = "tabtrans")
public class Tabletrans {
    @Column(name = "id", isId = true, autoGen = true)
    private int id;
    @Column(name = "from")
    private String from;
    @Column(name = "to")
    private String to;

    public Tabletrans(int id, String from, String to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public Tabletrans() {
    }

    public Tabletrans(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
