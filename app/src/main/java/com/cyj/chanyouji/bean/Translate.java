package com.cyj.chanyouji.bean;

import java.util.List;

/**
 * Created by maocalm on 2016/11/7.
 */
public class Translate {

    String from;
    String to; // ":"fra",
    List<Trans_result> trans_result; // ":Array[1] ;

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

    public List<Trans_result> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<Trans_result> trans_result) {
        this.trans_result = trans_result;
    }

    public class  Trans_result {

        String src; //
        String dst ; // ":"600 mètres de hauteur"

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }
}