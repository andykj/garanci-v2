/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garanci.utility;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;

/**
 *
 * @author zishan
 */
public class TimeScheduler {

    private Timer timer;

    private static TimeScheduler object = null;

    public static TimeScheduler getInstance() {

        if (object == null) {

            object = new TimeScheduler();

        }
        return object;
    }

    private TimeScheduler() {

    }

    private boolean doShutDown() {

        try {

            if (timer != null) {

                timer.cancel();

                timer.purge();

                return true;
            } else {
                return false;
            }

        } catch (Exception er) {

            return false;
        }

    }

    public void changeTimer(int hr, int min, long clientTimeDiff) {

        /**
         * shutdown previously running timer *
         */
        doShutDown();

        /**
         * create new timer
         *
         */
        timer = new Timer();

        int timeDiff = getGMTTimeDifference(clientTimeDiff);

        /**
         * getTime difference in minutes *
         */
        hr = hr + timeDiff / 60;

        min = min + timeDiff % 60;

        Calendar calende = Calendar.getInstance();

        calende.set(calende.get(Calendar.YEAR), calende.get(Calendar.MONTH), calende.get(Calendar.DATE), hr, min);

        System.out.println(calende.getTime());

        if (calende.getTimeInMillis() < new Date().getTime()) {

            /**
             * if time already passed then add one day to schedule next day *
             */
            calende.add(Calendar.DATE, 1);

        }

        System.out.println(calende.getTime());

        /**
         * schedule to execute every 24 hr *
         */
        timer.scheduleAtFixedRate(new StagingRecordFetcher(), new Date(calende.getTimeInMillis()), (long) 24 * 60 * 60 * 1000);
    }

    private int getGMTTimeDifference(long clientTimeDifference) {

        Calendar calende = Calendar.getInstance(TimeZone.getDefault().getTimeZone("GMT"));

        calende.set(calende.get(Calendar.YEAR), calende.get(Calendar.MONTH), calende.get(Calendar.DATE), 0, 0);

        System.out.println(calende.getTime());

        long gmtTIme = calende.getTimeInMillis();

        calende = Calendar.getInstance(TimeZone.getDefault());

        calende.set(calende.get(Calendar.YEAR), calende.get(Calendar.MONTH), calende.get(Calendar.DATE), 0, 0);

        System.out.println(calende.getTime());

        long serverTime = calende.getTimeInMillis();

        return (int) (clientTimeDifference - (serverTime - gmtTIme)) / 1000 / 60;

    }


    /*   
     public static void main(String[] args) {

     TimeScheduler t = TimeScheduler.getInstance();

     t.changeTimer(12, 23, 0);

     }
     */
}
