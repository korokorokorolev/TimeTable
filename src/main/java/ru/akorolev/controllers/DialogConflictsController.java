package ru.akorolev.controllers;

import ru.akorolev.entities.Conflict;
import ru.akorolev.forms.DialogConflicts;
import ru.akorolev.formsDataModels.DialogConflictsDataModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 09.05.13
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class DialogConflictsController extends AbstractController {
    private DialogConflicts view;
    private DialogConflictsDataModel dataModel;


    public DialogConflictsController() {
        super();
        view.setVisible(true);
    }
    @Override
    void initDataModel() {
        dataModel = new DialogConflictsDataModel();
    }

    @Override
    void initView() {
        view = new DialogConflicts(null, true);
        view.getjListConflicts().setModel(dataModel.getConflictsModel());
        view.getjListNoConflicts().setModel(dataModel.getNoConflictsModel());
    }

    @Override
    void regDataListeners() {
        dataModel.setListener(new DialogConflictsDataModel.OnChangeListener() {
            @Override
            public void onChangeStatus() {
                view.getjListConflicts().setModel(dataModel.getConflictsModel());
                view.getjListNoConflicts().setModel(dataModel.getNoConflictsModel());
            }
        });
    }

    @Override
    void regListeners() {
        view.getjButtonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });

        view.getjButtonToNoConflict().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onToNoConflictButtonClick();
            }
        });
        view.getjButtonToConflicts().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onToConflictButtonClick();
            }
        });
    }

    private void onToConflictButtonClick() {
        if(view.getjListNoConflicts().getSelectedValue() != null) {
            dataModel.setStatusToYes((Conflict)view.getjListNoConflicts().getSelectedValue());
        }
    }

    private void onToNoConflictButtonClick() {
        if(view.getjListConflicts().getSelectedValue() != null) {
            dataModel.setStatusToNo((Conflict)view.getjListConflicts().getSelectedValue());
        }
    }
}
