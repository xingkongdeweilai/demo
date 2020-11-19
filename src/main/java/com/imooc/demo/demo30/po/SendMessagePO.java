package com.imooc.demo.demo30.po;

import com.imooc.demo.demo30.form.SendMessageForm;

import java.io.Serializable;

public class SendMessagePO implements Serializable {

    private static final long serialVersionUID = 6512113100482999661L;
    /**
     * 消息接收者
     */
    private String messageTo;

    /**
     * 消息发送者
     */
    private String messageFrom;

    /**
     * 消息内容
     */
    private String content;

    /**
     * SendMessageForm转SendMessagePO
     * @param sendMessageForm
     * @param messageFrom
     * @return
     */
    public static SendMessagePO buildSendMessagePo(SendMessageForm sendMessageForm, String messageFrom) {
        SendMessagePO sendMessagePo = new SendMessagePO();
        sendMessagePo.setContent(sendMessageForm.getContent());
        sendMessagePo.setMessageTo(sendMessageForm.getMessageTo());
        sendMessagePo.setMessageFrom(messageFrom);
        return sendMessagePo;
    }

    public String getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SendMessagePO{" +
                "messageTo='" + messageTo + '\'' +
                ", messageFrom='" + messageFrom + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
