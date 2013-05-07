package ru.akorolev.controllers;

import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.forms.DialogRaspisanie;
import ru.akorolev.formsDataModels.DialogRaspisanieDataModel;
import ru.akorolev.staticsVariables.Days;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 07.05.13
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
public class DialogRaspisanieController extends AbstractController{
    private DialogRaspisanie view;
    private DialogRaspisanieDataModel dataModel;
    private TrainingFeed trainingFeed;

    public DialogRaspisanieController(TrainingFeed trainingFeed) {
        super();
        this.trainingFeed = trainingFeed;
        additionallyInit();
        view.setVisible(true);
    }

    private void additionallyInit() {
        repaintTables();
    }

    private void repaintTables() {
        view.getDayTable1().setModel(dataModel.getDayTableModel(Days.MONDAY, trainingFeed));
        view.getDayTable2().setModel(dataModel.getDayTableModel(Days.TUESDAY, trainingFeed));
        view.getDayTable3().setModel(dataModel.getDayTableModel(Days.WEDNESDAY, trainingFeed));
        view.getDayTable4().setModel(dataModel.getDayTableModel(Days.THURSDAY, trainingFeed));
        view.getDayTable5().setModel(dataModel.getDayTableModel(Days.FRIDAY, trainingFeed));
        view.getDayTable6().setModel(dataModel.getDayTableModel(Days.SATURDAY, trainingFeed));
    }

    @Override
    void initDataModel() {
        dataModel = new DialogRaspisanieDataModel();
    }

    @Override
    void initView() {
        view = new DialogRaspisanie(null, true);
    }

    @Override
    void regDataListeners() {

    }

    private class MouseListenerImpl implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    @Override
    void regListeners() {
        view.getDayTable1().addMouseListener(new MouseListenerImpl());
        view.getDayTable2().addMouseListener(new MouseListenerImpl());
        view.getDayTable3().addMouseListener(new MouseListenerImpl());
        view.getDayTable4().addMouseListener(new MouseListenerImpl());
        view.getDayTable5().addMouseListener(new MouseListenerImpl());
        view.getDayTable6().addMouseListener(new MouseListenerImpl());

        view.getDayTable1().setDay(Days.MONDAY);
        view.getDayTable2().setDay(Days.TUESDAY);
        view.getDayTable3().setDay(Days.WEDNESDAY);
        view.getDayTable4().setDay(Days.THURSDAY);
        view.getDayTable5().setDay(Days.FRIDAY);
        view.getDayTable6().setDay(Days.SATURDAY);
    }
}
