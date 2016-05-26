package br.com.meetstudents.application;

import android.app.Application;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import br.com.meetstudents.database.DatabaseHelper;
import br.com.meetstudents.sharedpreferences.AppPreferences;
import br.com.meetstudents.utils.TypefaceUtil;

/**
 * Created by marinagosson on 02/05/16.
 */
public class MeetStudentsApplication extends Application {

    private final String TAG = MeetStudentsApplication.class.getName();

    private static DatabaseHelper databaseHelper;

    private static AppPreferences appPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        appPreferences = new AppPreferences();

        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/robotoregular.ttf");

        this.databaseHelper = initDataBaseHelper();
    }

    public static DatabaseHelper getDataBaseHelper() {
        return databaseHelper;
    }

    private DatabaseHelper initDataBaseHelper() {
        if (databaseHelper == null) {
            Log.d(TAG, "Init DataBaseHelper");
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public static AppPreferences getAppPreferences() {
        return appPreferences;
    }
}
