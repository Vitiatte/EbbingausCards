package sample.service.util;


import sample.db.entity.Card;
import sample.service.enums.Level;
import sample.service.enums.CardRememberedSuccessful;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * Intervals:
 * today -> 1 day -> 1 week -> 1 month -> 3 month -> 1 year -> 2 years
 */
public class CardIntervalTimeChanger {

    private Card cardToBeChanged;

    public void changeTimeInterval(Card cardToBeChanged, CardRememberedSuccessful cardRememberedSuccessful) {
        this.cardToBeChanged = cardToBeChanged;

        if (cardRememberedSuccessful.equals(CardRememberedSuccessful.YES))
            changeIntervalUp();
        if (cardRememberedSuccessful.equals(CardRememberedSuccessful.NO))
            startFromBeginning();

        this.cardToBeChanged = null;
    }

    private void changeIntervalUp() {
        switch (cardToBeChanged.getLevel()) {   // Level pointed at next date of training,
                                                // begin counting from last date of training.
            case Today:
                oneDayUp();
                break;
            case OneDay:
                oneWeekUp();
                break;
            case OneWeek:
                oneMonthUp();
                break;
            case OneMonth:
                threeMonthsUp();
                break;
            case ThreeMonths:
                oneYearUp();
                break;
            case OneYear:
                twoYearsUp();
                break;
            case TwoYears:
                twoYearsUp();
                break;
        }
    }

    //Utility methods
    private void oneDayUp(){
        daysUpper(1);
        cardToBeChanged.setLevel(Level.OneDay);
    }
    private void oneWeekUp(){
        daysUpper(7);
        cardToBeChanged.setLevel(Level.OneWeek);
    }
    private void oneMonthUp(){
        daysUpper(30);
        cardToBeChanged.setLevel(Level.OneMonth);
    }
    private void threeMonthsUp(){
        daysUpper(90);
        cardToBeChanged.setLevel(Level.ThreeMonths);
    }
    private void oneYearUp(){
        daysUpper(365);
        cardToBeChanged.setLevel(Level.OneYear);
    }
    private void twoYearsUp(){
        daysUpper(730);
        cardToBeChanged.setLevel(Level.OneWeek);
    }
    private void startFromBeginning(){
        cardToBeChanged.setLastDate(new Date());
        cardToBeChanged.setNextDate(cardToBeChanged.getLastDate());
        cardToBeChanged.setLevel(Level.Today);
    }
    private void daysUpper(int days){
        Date date = new Date();
        cardToBeChanged.setLastDate(date);
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).plusDays(days);
        date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        cardToBeChanged.setNextDate(date);
    }
}
