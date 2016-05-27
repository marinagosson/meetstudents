package br.com.meetstudents.utils;

import java.sql.Timestamp;
import java.util.Date;

public class Utils {

    public Date convertTimestampToDate(long timestamp) {
        return new Date(new Timestamp(timestamp).getTime());
    }

}
