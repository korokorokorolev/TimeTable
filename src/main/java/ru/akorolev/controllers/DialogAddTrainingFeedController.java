package ru.akorolev.controllers;

import ru.akorolev.entities.Groups;
import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.forms.DialogAddTrainingFeed;
import ru.akorolev.formsDataModels.DialogAddTrainingFeedDataModel;
import ru.akorolev.informer.Informer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class DialogAddTrainingFeedController extends AbstractController{
    private DialogAddTrainingFeed view;
    private DialogAddTrainingFeedDataModel dataModel;
    private TrainingFeed trainingFeed = new TrainingFeed();
    private boolean isSuccess = false;

    public boolean isSuccess() {
        return isSuccess;
    }

    public DialogAddTrainingFeedController() {
        super();
        this.view.setVisible(true);
    }

    @Override
    void initDataModel() {
        dataModel = new DialogAddTrainingFeedDataModel();
    }

    @Override
    void initView() {
        this.view = new DialogAddTrainingFeed(null, true);
    }

    @Override
    void regDataListeners() {
        this.dataModel.setOnchangeListener(new DialogAddTrainingFeedDataModel.OnChangeListener() {
            @Override
            public void onGroupRemoved(TrainingFeed trainingFeed1) {
                view.getjListGroups().setModel(dataModel.getGroupsListModel(trainingFeed1));
            }
        });
    }

    @Override
    void regListeners() {
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
                new Informer(null, true).setVisible(true);
            }
        }
    }

    private void onRemGroupButtonClick() {
        if(view.getjListGroups().getSelectedValue() != null) {
            try {
                dataModel.remGroupFromTrainingFeed(trainingFeed, (Groups) view.getjListGroups().getSelectedValue());
            } catch (Exception e) {
                new Informer(null, true).setVisible(true);
            }
        }
    }

    private void onAddGroupButtonClick() {
        try {
            DialogAddGroupController dialogAddGroupController = new DialogAddGroupController(trainingFeed);
            if(dialogAddGroupController.isSuccess()) {
                view.getjListGroups().setModel(dataModel.getGroupsListModel(trainingFeed));
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }
}
