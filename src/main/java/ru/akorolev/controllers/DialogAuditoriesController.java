package ru.akorolev.controllers;

import ru.akorolev.entities.Auditory;
import ru.akorolev.forms.DialogAuditories;
import ru.akorolev.formsDataModels.DialogAuditoriesDataModel;
import ru.akorolev.informer.Informer;
import ru.akorolev.staticsVariables.DialogAuditoriesMode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 12:53
 * To change this template use File | Settings | File Templates.
 */
public class DialogAuditoriesController extends AbstractController{
    private DialogAuditories view;
    private DialogAuditoriesDataModel dataModel;
    public DialogAuditoriesController(int mode) {
        super();
        if(mode == DialogAuditoriesMode.ADD_ONLY) {
            this.view.getjButtonRemAuditory().setVisible(false);
        }
        view.setVisible(true);
    }

    @Override
    void initDataModel() {
        dataModel = new DialogAuditoriesDataModel();
    }
    @Override
    void initView() {
        view = new DialogAuditories(null, true);
        view.getjListAuditories().setModel(dataModel.getAuditoriesModel());
        view.getjListAuditories().setSelectedIndex(0);
    }
    @Override
    void regDataListeners() {
        this.dataModel.setListener(new DialogAuditoriesDataModel.OnChangeListener() {
            @Override
            public void onAuditoryDeleted() {
                try {
                    view.getjListAuditories().setModel(dataModel.getAuditoriesModel());
                    view.getjListAuditories().setSelectedIndex(view.getjListAuditories().getModel().getSize() -1);
                } catch (Exception e) {
                    new Informer(null, true).setVisible(true);
                }
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
        view.getjButtonAddAuditory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddAuditoryButtonClick();
            }
        });
        view.getjButtonRemAuditory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemAuditoryButtonClick();
            }
        });
    }

    private void onRemAuditoryButtonClick() {
        if(view.getjListAuditories().getSelectedValue() != null) {
            try{
                dataModel.removeAuditory((Auditory)view.getjListAuditories().getSelectedValue());
            } catch(Exception e) {
                new Informer(null, true).setVisible(true);
            }
        }
    }

    private void onAddAuditoryButtonClick() {
        try {
            DialogAddAuditoryController dialogAddAuditoryController = new DialogAddAuditoryController();
            if(dialogAddAuditoryController.isSuccess()) {
                view.getjListAuditories().setModel(dataModel.getAuditoriesModel());
                view.getjListAuditories().setSelectedIndex(view.getjListAuditories().getModel().getSize() - 1);
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }


}
