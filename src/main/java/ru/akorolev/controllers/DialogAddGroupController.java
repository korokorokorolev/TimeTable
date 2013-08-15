package ru.akorolev.controllers;

import ru.akorolev.entities.Groups;
import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.forms.DialogAddGroup;
import ru.akorolev.formsDataModels.DialogAddGroupDataModel;
import ru.akorolev.informer.Informer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 17:02
 * To change this template use File | Settings | File Templates.
 */
public class DialogAddGroupController extends AbstractController{
    private DialogAddGroup view;
    private DialogAddGroupDataModel dataModel;
    private boolean isSuccess = false;
    TrainingFeed trainingFeed;

    public DialogAddGroupController(TrainingFeed trainingFeed) {
        super();
        this.trainingFeed = trainingFeed;
        view.setVisible(true);
    }

    @Override
    void initDataModel() {
        dataModel = new DialogAddGroupDataModel();
    }

    @Override
    void initView() {
        view = new DialogAddGroup(null, true);
    }

    @Override
    void regDataListeners() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void regListeners() {
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
        try {
            if(!view.getjTextFieldGroupName().getText().isEmpty()) {
                Groups group = new Groups();
                group.setName(view.getjTextFieldGroupName().getText());
                dataModel.addGroupToTrainingFeed(trainingFeed, group);
                this.isSuccess = true;
                view.dispose();
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
