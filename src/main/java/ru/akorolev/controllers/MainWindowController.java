package ru.akorolev.controllers;

import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.forms.MainWindow;
import ru.akorolev.formsDataModels.MainWindowDataModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                DialogTeachersAndSubjectsController dialogTeachersAndSubjectsController = new DialogTeachersAndSubjectsController();
            }
        });
        mainWindow.getjMenuItemAuditory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogAuditoriesController dialogAuditoriesController = new DialogAuditoriesController();
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
    }
    private void onAuditopryEmploymentClick() {
        DialogAuditoriesEmploymentController dialogAuditoriesEmploymentController = new DialogAuditoriesEmploymentController();
    }

    private void onTeachersEmploymentClick() {
        DialogTeachersEmploymentController dialogTeachersEmploymentController = new DialogTeachersEmploymentController();

    }

    private void onEditButtonClick() {
        if(mainWindow.getjListTrainingFeeds().getSelectedValue() != null) {
            DialogRaspisanieController dialogRaspisanieController =
                    new DialogRaspisanieController((TrainingFeed)mainWindow.getjListTrainingFeeds().getSelectedValue());
        }
    }

    private void onRemTFButtonClick() {
        if(mainWindow.getjListTrainingFeeds().getSelectedValue() != null) {
            dataModel.remTrainingFeed((TrainingFeed) mainWindow.getjListTrainingFeeds().getSelectedValue());
        }
    }

    private void onAddTFButtonClick() {
        DialogAddTrainingFeedController dialogAddTrainingFeedController = new DialogAddTrainingFeedController();
        if(dialogAddTrainingFeedController.isSuccess()) {
            mainWindow.getjListTrainingFeeds().setModel(dataModel.getTrainingFeedsModel());
        }
    }
}
