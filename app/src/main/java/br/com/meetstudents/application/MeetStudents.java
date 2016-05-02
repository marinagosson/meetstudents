package br.com.meetstudents.application;

import android.app.Application;

import br.com.meetstudents.utils.TypefaceUtil;

/**
 * Created by marinagosson on 02/05/16.
 */
public class MeetStudents extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize typeface helper
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/robotoregular.ttf");
    }
}
