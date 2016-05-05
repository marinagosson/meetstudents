package br.com.meetstudents.application;

import android.app.Application;

import br.com.meetstudents.utils.TypefaceUtil;

/**
 * Created by marinagosson on 02/05/16.
 */
public class MeetStudentsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Inicializacao da fonte padrao da aplicacao
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/robotoregular.ttf");
    }
}
