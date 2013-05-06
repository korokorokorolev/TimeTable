package ru.akorolev.controllers;

import ru.akorolev.entities.Groups;
import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.forms.DialogAddTrainingFeed;
import ru.akorolev.formsDataModels.DialogAddTrainingFeedDataModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class DialogAddTrainingFeedController {
    private DialogAddTrainingFeed view;
    private DialogAddTrainingFeedDataModel dataModel = new DialogAddTrainingFeedDataModel();
    private TrainingFeed trainingFeed = new TrainingFeed();
    private boolean isSuccess = false;

    public boolean isSuccess() {
        return isSuccess;
    }

    public DialogAddTrainingFeedController() {
        this.view = new DialogAddTrainingFeed(null, true);
        regListeners();
        regDataListeners();
        this.view.setVisible(true);
    }

    private void regDataListeners() {
        this.dataModel.setOnchangeListener(new DialogAddTrainingFeedDataModel.OnChangeListener() {
            @Override
            public void onGroupRemoved(TrainingFeed trainingFeed1) {
                view.getjListGroups().setModel(dataModel.getGroupsListModel(trainingFeed1));
            }
        });
    }

    private void regListeners() {
        view.getjButtonCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
        view.getjButtonAddGroup().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddGroupButtonClick();
            }
        });
        view.getjButtonRemGroup().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemGroupButtonClick();
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
        if(!view.getjTextFieldTFName().getText().isEmpty() &&
                !trainingFeed.getGroupsList().isEmpty()) {
            try {
                trainingFeed.setName(view.getjTextFieldTFName().getText());
                dataModel.saveTrainingFeed(trainingFeed);
                this.isSuccess = true;
                view.dispose();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void onRemGroupButtonClick() {
        if(view.getjListGroups().getSelectedValue() != null) {
            dataModel.remGroupFromTrainingFeed(trainingFeed, (Groups) view.getjListGroups().getSelectedValue());
        }
    }

    private void onAddGroupButtonClick() {
        DialogAddGroupController dialogAddGroupController = new DialogAddGroupController(trainingFeed);
        if(dialogAddGroupController.isSuccess()) {
            view.getjListGroups().setModel(dataModel.getGroupsListModel(trainingFeed));
        }
    }
}
