package ru.akorolev.controllers;

import ru.akorolev.entities.Cell;
import ru.akorolev.forms.DialogCell;
import ru.akorolev.formsDataModels.DialogCellDataModel;
import ru.akorolev.informer.Informer;
import ru.akorolev.staticsVariables.DialogAuditoriesMode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 07.05.13
 * Time: 20:43
 * To change this template use File | Settings | File Templates.
 */
public class DialogCellController extends AbstractController{
    private DialogCell view;
    private DialogCellDataModel dataModel;
    private boolean isSuccess = false;
    private boolean isEdit = false;
    private Cell cell = null;
    private Panel4PartController panel4PartController1 = null;
    private Panel4PartController panel4PartController2 = null;
    private Panel4PartController panel4PartController3 = null;
    private Panel4PartController panel4PartController4 = null;

    public DialogCellController(boolean isEdit, Cell cell) {
        super();
        this.isEdit = isEdit;
        this.cell = cell;
        additionallyInit();
        view.setVisible(true);
    }

    private void additionallyInit() {
        if(!isEdit) {
            panel4PartController1 = new Panel4PartController(view.getPanel4Part1());
            panel4PartController2 = new Panel4PartController(view.getPanel4Part2());
            panel4PartController3 = new Panel4PartController(view.getPanel4Part3());
            panel4PartController4 = new Panel4PartController(view.getPanel4Part4());
        } else {
            panel4PartController1 = new Panel4PartController(view.getPanel4Part1(), cell.getSubject1(), cell.getAuditory1());
            panel4PartController2 = new Panel4PartController(view.getPanel4Part2(), cell.getSubject2(), cell.getAuditory2());
            panel4PartController3 = new Panel4PartController(view.getPanel4Part3(), cell.getSubject3(), cell.getAuditory3());
            panel4PartController4 = new Panel4PartController(view.getPanel4Part4(), cell.getSubject4(), cell.getAuditory4());
        }
        initInfo();

        String title ="Группа: " + cell.getGroupsId() + " День: " + cell.getDay() + " Номер занятия: " + (cell.getTrainingNum() + 1);
        view.setTitle(title);
    }

    private void initInfo() {
        try {
            view.getjListTeachersChFreedom().setModel(dataModel.getTeachersChFreedomModel(cell));
            view.getjListTeachersChEmpl().setModel(dataModel.getTeachersChEmplModel(cell));
            view.getjListAudChEmpl().setModel(dataModel.getAudChEmplModel(cell));
            view.getjListAudChFreedom().setModel(dataModel.getAudChFreedomModel(cell));

            view.getjListTeachersZnEmpl().setModel(dataModel.getTeachersZnEmplModel(cell));
            view.getjListTeachersZnFreedom().setModel(dataModel.getTeachersZnFreedomModel(cell));
            view.getjListAudZnEmpl().setModel(dataModel.getAudZnEmplModel(cell));
            view.getjListAudZnFreedom().setModel(dataModel.getAudZnFreedomModel(cell));
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    @Override
    void initDataModel() {
        this.dataModel = new DialogCellDataModel();
    }

    @Override
    void initView() {
        view = new DialogCell(null, true);
        view.setMinimumSize(new Dimension(1000,600));
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
        view.getjButtonAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAllButtonClick();
            }
        });
        view.getjButton12().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButton12Click();
            }
        });
        view.getjButton13().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButton13Click();
            }
        });
        view.getjButton24().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButton24Click();
            }
        });
        view.getjButton34().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButton34CLick();
            }
        });
        view.getjButtonTest().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onTestButtonClick();
            }
        });
        view.getjMenuItemAuditories().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMenuItemAuditoryClick();
            }
        });
        view.getjMenuItemTeachers().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMenuItemTeachersClick();
            }
        });
    }

    private void onMenuItemTeachersClick() {
        DialogTeachersAndSubjectsController dialogTeachersAndSubjectsController = new DialogTeachersAndSubjectsController();
        panel4PartController1.updateTeachersAndSubjectCombos();
        panel4PartController2.updateTeachersAndSubjectCombos();
        panel4PartController3.updateTeachersAndSubjectCombos();
        panel4PartController4.updateTeachersAndSubjectCombos();
    }

    private void onMenuItemAuditoryClick() {
        DialogAuditoriesController dialogAuditoriesController = new DialogAuditoriesController(DialogAuditoriesMode.ADD_ONLY);
        panel4PartController1.updateAuditoriesCombo();
        panel4PartController2.updateAuditoriesCombo();
        panel4PartController3.updateAuditoriesCombo();
        panel4PartController4.updateAuditoriesCombo();

    }

    private void onTestButtonClick() {
        try {
            Cell cellCopy = new Cell(cell.getId(), cell.getDay(), cell.getTrainingNum());
            if(cellCopy.getId() == null) {
                cellCopy.setId(-1);
            }
            cellCopy.setAuditory1(panel4PartController1.getAuditory());
            cellCopy.setAuditory2(panel4PartController2.getAuditory());
            cellCopy.setAuditory3(panel4PartController3.getAuditory());
            cellCopy.setAuditory4(panel4PartController4.getAuditory());

            cellCopy.setSubject1(panel4PartController1.getSubject());
            cellCopy.setSubject2(panel4PartController2.getSubject());
            cellCopy.setSubject3(panel4PartController3.getSubject());
            cellCopy.setSubject4(panel4PartController4.getSubject());
            view.getjLabelTest().setText(dataModel.getStatusCurrentSituation(cellCopy));
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onButton34CLick() {
        try {
            if(panel4PartController3 != null && panel4PartController4 != null) {
                panel4PartController4.setupTSA(panel4PartController3.getSubject(), panel4PartController3.getAuditory());
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onButton24Click() {
        try {
            if(panel4PartController2 != null && panel4PartController4 != null) {
                panel4PartController4.setupTSA(panel4PartController2.getSubject(), panel4PartController2.getAuditory());
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onButton13Click() {
        try {
            if(panel4PartController1 != null && panel4PartController3 != null) {
                panel4PartController3.setupTSA(panel4PartController1.getSubject(), panel4PartController1.getAuditory());
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onButton12Click() {
        try {
            if(panel4PartController1 != null && panel4PartController2 != null) {
                panel4PartController2.setupTSA(panel4PartController1.getSubject(), panel4PartController1.getAuditory());
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onAllButtonClick() {
        try {
            if(panel4PartController1 != null && panel4PartController2 != null
                    && panel4PartController3 != null && panel4PartController4 != null) {
                panel4PartController2.setupTSA(panel4PartController1.getSubject(), panel4PartController1.getAuditory());
                panel4PartController3.setupTSA(panel4PartController1.getSubject(), panel4PartController1.getAuditory());
                panel4PartController4.setupTSA(panel4PartController1.getSubject(), panel4PartController1.getAuditory());
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onOkButtonClick() {
        if (panel4PartController1.isReady() && panel4PartController2.isReady() &&
                panel4PartController3.isReady() && panel4PartController4.isReady()) {
            cell.setAuditory1(this.panel4PartController1.getAuditory());
            cell.setAuditory2(this.panel4PartController2.getAuditory());
            cell.setAuditory3(this.panel4PartController3.getAuditory());
            cell.setAuditory4(this.panel4PartController4.getAuditory());

            cell.setSubject1(this.panel4PartController1.getSubject());
            cell.setSubject2(this.panel4PartController2.getSubject());
            cell.setSubject3(this.panel4PartController3.getSubject());
            cell.setSubject4(this.panel4PartController4.getSubject());

            try {
                if(!this.isEdit) {
                    dataModel.saveCell(cell);
                    this.isSuccess = true;
                    view.dispose();
                } else {
                    dataModel.editCell(cell);
                    this.isSuccess = true;
                    view.dispose();
                }
            } catch (Exception e) {
                new Informer(null, true).setVisible(true);
            }

        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
