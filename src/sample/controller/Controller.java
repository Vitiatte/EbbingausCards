package sample.controller;

import sample.db.entity.User;
import sample.service.util.SceneLoader;


public abstract class Controller {

    protected User registeredUser = null;
    protected SceneLoader sceneLoader = new SceneLoader();
}
