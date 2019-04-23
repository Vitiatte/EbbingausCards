package sample.service.util;

import sample.controller.Controller;
import sample.controller.training.EnglishCardTrainingController;
import sample.controller.training.NoteTrainingController;

public interface Controllers {

    interface TypeController {
        Controller getController();
    }

    enum ForTrainingView implements TypeController{
        EnglishCardController {
            public Controller getController() {
                return new EnglishCardTrainingController();
            }
        },

        NoteCardController {
            public Controller getController() {
                return new NoteTrainingController();
            }
        }
    }

}
