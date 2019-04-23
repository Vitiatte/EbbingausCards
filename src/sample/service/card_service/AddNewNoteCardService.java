package sample.service.card_service;

import sample.constant.AddNewNoteMessageConstant;
import sample.db.DatabaseCardHandler;
import sample.db.DatabaseNoteHandler;
import sample.db.entity.NoteCard;
import sample.service.enums.Level;

import java.util.Date;

public class AddNewNoteCardService implements AddNewCardService {

    private DatabaseCardHandler<NoteCard> handler;

    private String title;
    private String note;
    private String book;
    private int userId;

    public AddNewNoteCardService(String title, String note, String book, int userId) {
        this.title = title;
        this.note = note;
        this.book = book;
        this.userId = userId;
    }

    @Override
    public String addNewCard() {

        String errorMessage = checkInputData();

        if (errorMessage.isEmpty()) {
            handler = new DatabaseNoteHandler();
            NoteCard noteCard = createNote();
            handler.addCard(noteCard);
            return AddNewNoteMessageConstant.NOTE_ADDED_SUCCESSFUL;
        }
        return errorMessage;
    }

    private String checkInputData() {
        String errorString = "";

        if (title.isEmpty())
            errorString = AddNewNoteMessageConstant.IF_TITLE_FIELD_EMPTY;
        if (note.isEmpty())
            errorString = errorString + ",\n" + AddNewNoteMessageConstant.IF_NOTE_FIELD_EMPTY;
        if (book.isEmpty())
            errorString = errorString + ",\n" + AddNewNoteMessageConstant.IF_BOOK_FIELD_EMPTY;

        return errorString;

    }

    private NoteCard createNote() {
        NoteCard noteCard = new NoteCard(title, note, book);
        noteCard.setLevel(Level.Today);
        noteCard.setLastDate(new Date());
        noteCard.setNextDate(new Date());
        noteCard.setUserId(userId);

        return noteCard;
    }
}
