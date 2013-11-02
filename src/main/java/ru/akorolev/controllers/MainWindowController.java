package ru.akorolev.controllers;

import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.forms.MainWindow;
import ru.akorolev.formsDataModels.MainWindowDataModel;
import ru.akorolev.informer.Informer;
import ru.akorolev.printer.Printer;
import ru.akorolev.staticsVariables.DialogAuditoriesMode;
import ru.akorolev.staticsVariables.DialogTeachersMode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Класс-контроллер для стартового окна приложения
 */
public class MainWindowController extends AbstractController{
    private MainWindow mainWindow;
    private MainWindowDataModel dataModel;

    /**
     * Конструктор вызывает родительский кончтруктор для
     * инициализации полей класса
     */
    public MainWindowController() {
        super();
    }

    /**
     * Создание модели данных
     */
    @Override
    void initDataModel() {
        dataModel = new MainWindowDataModel();
    }

    /**
     * Создание, настройки и запуск стартового окна
     */
    @Override
    void initView() {
        mainWindow = new MainWindow();
        mainWindow.getjListTrainingFeeds().setModel(dataModel.getTrainingFeedsModel());
        mainWindow.setVisible(true);
        mainWindow.setTitle("Расписание факультета ИВТ");
    }

    /**
     * Регистрация слушателя модели данных
     */
    @Override
    void regDataListeners() {
        this.dataModel.setListener(new MainWindowDataModel.OnChangeListener() {
            @Override
            public void onTrainingFeedRemoved() {
                mainWindow.getjListTrainingFeeds().setModel(dataModel.getTrainingFeedsModel());
                mainWindow.getjListTrainingFeeds().setSelectedIndex(mainWindow.getjListTrainingFeeds().getModel().getSize() -1);
            }
        });
    }

    /**
     * Регистрация слушателей визуальных элементов формы
     */
    @Override
    void regListeners() {
        mainWindow.getjMenuItemTeachersAndSubjects().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogTeachersAndSubjectsController dialogTeachersAndSubjectsController = new DialogTeachersAndSubjectsController(DialogTeachersMode.ADD_AND_REMOVE);
            }
        });
        mainWindow.getjMenuItemAuditory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogAuditoriesController dialogAuditoriesController = new DialogAuditoriesController(DialogAuditoriesMode.ADD_AND_REMOVE);
            }
        });
        mainWindow.getjButtonAddTF().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddTFButtonClick();
            }
        });
        mainWindow.getjButtonRemTF().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemTFButtonClick();
            }
        });
        mainWindow.getjButtonEdit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEditButtonClick();
            }
        });
        mainWindow.getjMenuItemTeachersEmployment().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onTeachersEmploymentClick();
            }
        });
        mainWindow.getjMenuItemAuditoryEmployment().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAuditopryEmploymentClick();
            }
        });
        mainWindow.getjMenuItemPrint().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                print();
            }
        });
    }

    private void print() {
        if(this.mainWindow.getjListTrainingFeeds().getSelectedValue() != null) {
            List cells = dataModel.getCellsFromTF((TrainingFeed)this.mainWindow.getjListTrainingFeeds().getSelectedValue());
            System.out.println(cells);
            Printer printer = new Printer(cells,(TrainingFeed)this.mainWindow.getjListTrainingFeeds().getSelectedValue());
            try {
                printer.print();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                new Informer(null, true, "Проблемы").setVisible(true);
            }
        }
    }

    private void onAuditopryEmploymentClick() {
        try {
            DialogAuditoriesEmploymentController dialogAuditoriesEmploymentController = new DialogAuditoriesEmploymentController();
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onTeachersEmploymentClick() {
        try {
            DialogTeachersEmploymentController dialogTeachersEmploymentController = new DialogTeachersEmploymentController();
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onEditButtonClick() {
        try {
            if(mainWindow.getjListTrainingFeeds().getSelectedValue() != null) {
                DialogRaspisanieController dialogRaspisanieController =
                        new DialogRaspisanieController((TrainingFeed)mainWindow.getjListTrainingFeeds().getSelectedValue());
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onRemTFButtonClick() {
        try {
            if(mainWindow.getjListTrainingFeeds().getSelectedValue() != null) {
                dataModel.remTrainingFeed((TrainingFeed) mainWindow.getjListTrainingFeeds().getSelectedValue());
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onAddTFButtonClick() {
        DialogAddTrainingFeedController dialogAddTrainingFeedController = new DialogAddTrainingFeedController();
        try {
            if(dialogAddTrainingFeedController.isSuccess()) {
                mainWindow.getjListTrainingFeeds().setModel(dataModel.getTrainingFeedsModel());
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }
}
