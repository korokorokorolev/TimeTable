package ru.akorolev.controllers;

import ru.akorolev.forms.DialogTeacher;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 04.05.13
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
public class DialogTeacherController {
    private DialogTeacher dialogTeacher;

    public DialogTeacherController() {
        dialogTeacher = new DialogTeacher(null, true);
        dialogTeacher.setVisible(true);
    }
}
