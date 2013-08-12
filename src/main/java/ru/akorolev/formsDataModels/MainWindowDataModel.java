package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.models.ListModelImplementation;

import javax.swing.*;
import java.util.List;

/**
 * Класс-модель данных для стартового окна приложения
 */
public class MainWindowDataModel {
    private DAO dao = DAOImplementation.getInstance();
    private  OnChangeListener listener;

    /**
     * Метод установки слушателя изменений
     * @param listener слушатель изменений
     */
    public void setListener(OnChangeListener listener) {
        this.listener = listener;
    }

    /**
     * Метод получения списка учебных потоков в виде,
     * необходимом для отображения
     * @return список учебных потоков
     */
    public ListModel getTrainingFeedsModel() {
        return new ListModelImplementation(this.getTrainingFeeds());
    }

    private List getTrainingFeeds() {
        return dao.getTrainingFeeds();
    }

    /**
     *  Удаление учебного потока
     * @param trainingFeed учебный поток
     */
    public void remTrainingFeed(TrainingFeed trainingFeed) {
        dao.removeTrainingFeed(trainingFeed);
        if(listener != null) {
            listener.onTrainingFeedRemoved();
        }
    }

    public List getCellsFromTF(TrainingFeed trainingFeed) {
        return this.dao.getCellsFromTF(trainingFeed);
    }

    /**
     * Интерфей слушателя изменений модели данных
     */
    public interface OnChangeListener{
        public void onTrainingFeedRemoved();
    }
}
