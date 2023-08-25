package com.itai.coupons.utils;

import com.itai.coupons.threads.SendStatisticsThread;

public class StatisticsUtils extends Thread {
    // התפקיד שלה זה להיקרא מנקודות שונות בקוד ולדווח לשרת הסטטיסטיקה מה קרה.
    /* פו' זו היא נחמדה אבל לא קריטית. כלומר גם אם קרה משהו לא צפוי
    בתרגיל הזה נרצה שהפונקציה תיצור לוג שהמשתמש ביצע פונקציה ותכל את הפונקציה ואת הלוג
     */
    public static void sendStatistics(String text) {
        SendStatisticsThread sendStatisticsThread = new SendStatisticsThread(text);
        sendStatisticsThread.start();
    }
}
