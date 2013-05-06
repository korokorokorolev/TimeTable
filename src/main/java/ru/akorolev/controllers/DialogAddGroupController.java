package ru.akorolev.controllers;

import ru.akorolev.entities.Groups;
import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.forms.DialogAddGroup;
import ru.akorolev.formsDataModels.DialogAddGroupDataModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 17:02
 * To change this template use File | Settings | File Templates.
 */
public class DialogAddGroupController {
    private DialogAddGroup view;
    private DialogAddGroupDataModel dataModel = new DialogAddGroupDataModel();
    private boolean isSuccess = false;
    TrainingFeed trainingFeed;

    public DialogAddGroupController(TrainingFeed trainingFeed) {
        view = new DialogAddGroup(null, true);
        this.trainingFeed = trainingFeed;
        regListeners();

        view.setVisible(true);
    }

    private void regListeners() {
        view.getjButtonCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
        view.getjButtonOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOkButtonClick();
            }
        });
    }

    private void onOkButtonClick() {
        if(!view.getjTextFieldGroupName().getText().isEmpty()) {
            Groups group = new Groups();
            group.setName(view.getjTextFieldGroupName().getText());
            dataModel.addGroupToTrainingFeed(trainingFeed, group);
            this.isSuccess = true;
            view.dispose();
        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
