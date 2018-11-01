package com.example.administrator.recyclerviewtest;

import java.sql.Date;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/10/29.
 */

public class Message extends BmobObject {
    private String Message;
    private Date createAt;

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
