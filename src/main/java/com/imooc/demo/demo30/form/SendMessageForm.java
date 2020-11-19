package com.imooc.demo.demo30.form;

import java.io.Serializable;

/**
 * 发送消息form
 */
public class SendMessageForm implements Serializable {

    /**
     * 消息接收者
     */
    private String messageTo;

    /**
     * 消息内容
     */
    private String content;

    public SendMessageForm() {
    }

    public SendMessageForm(String messageTo, String content) {
        this.messageTo = messageTo;
        this.content = content;
    }

    public String getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SendMessageForm{" +
                "messageTo='" + messageTo + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
