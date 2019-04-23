package sample.db.entity;

import sample.service.enums.Level;

import java.util.Date;

public class NoteCard extends Card{

    private String title;
    private String note;
    private String book;

    public NoteCard(String title, String note, String book) {
        this.title = title;
        this.note = note;
        this.book = book;
    }

    public NoteCard(int id, String title, String note, String book, Date lastDate, Level level, Date nextDate, int userId) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.book = book;
        this.lastDate = lastDate;
        this.level = level;
        this.nextDate = nextDate;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
