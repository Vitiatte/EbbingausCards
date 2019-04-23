package sample.db.entity;

import sample.service.enums.Level;

import java.util.Date;

public class EnglishCard extends Card{
    private String ua;
    private String eng;
    private String eng_trans;
    private String eng_context;

    public EnglishCard(String ua, String eng, String eng_trans, String eng_context) {
        this.ua = ua;
        this.eng = eng;
        this.eng_trans = eng_trans;
        this.eng_context = eng_context;
    }

    public EnglishCard(int id, String ua, String eng, String eng_trans, String eng_context, Date lastDate, Level level, Date nextDate, int userId) {
        this.id = id;
        this.ua = ua;
        this.eng = eng;
        this.eng_trans = eng_trans;
        this.eng_context = eng_context;
        this.lastDate = lastDate;
        this.level = level;
        this.nextDate = nextDate;
        this.userId = userId;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getEng_trans() {
        return eng_trans;
    }

    public void setEng_trans(String eng_trans) {
        this.eng_trans = eng_trans;
    }

    public String getEng_context() {
        return eng_context;
    }

    public void setEng_context(String eng_context) {
        this.eng_context = eng_context;
    }
}
