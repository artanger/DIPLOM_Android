package com.example.arthur.easysendler;

import android.app.Application;
import android.content.Context;

import com.example.arthur.easysendler.services.MailService;

/**
 * Created by student on 11.07.2018.
 */

public class App extends Application {
    private MailService mailService;
    public static App get(Context ctx){
        return (App)ctx.getApplicationContext();
    }
    public MailService getMailService(){
        if(mailService==null) mailService = new MailService();
        return mailService;
    }

}
