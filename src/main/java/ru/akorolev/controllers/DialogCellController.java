package ru.akorolev.controllers;

import ru.akorolev.entities.Cell;
import ru.akorolev.forms.DialogCell;
import ru.akorolev.formsDataModels.DialogCellDataModel;

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
//            Add start values to constructor
            panel4PartController1 = new Panel4PartController(view.getPanel4Part1(), cell.getSubject1(), cell.getAuditory1());
            panel4PartController2 = new Panel4PartController(view.getPanel4Part2(), cell.getSubject2(), cell.getAuditory2());
            panel4PartController3 = new Panel4PartController(view.getPanel4Part3(), cell.getSubject3(), cell.getAuditory3());
            panel4PartController4 = new Panel4PartController(view.getPanel4Part4(), cell.getSubject4(), cell.getAuditory4());
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
                System.out.println(e);
            }

        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
