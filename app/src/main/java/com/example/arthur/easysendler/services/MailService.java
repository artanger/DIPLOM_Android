package com.example.arthur.easysendler.services;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by student on 11.07.2018.
 */

public class MailService {
    private String recipientId;
    private String settingId;
    private String templateId;
    PublishSubject<Boolean> changeSubject = PublishSubject.create();

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
        changeSubject.onNext(true);
    }

    public String getSettingId() {
        return settingId;
    }

    public void setSettingId(String settingId) {
        this.settingId = settingId;
        changeSubject.onNext(true);
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
        changeSubject.onNext(true);
    }

    public PublishSubject<Boolean> getChangeSubject() {
        return changeSubject;
    }
}
