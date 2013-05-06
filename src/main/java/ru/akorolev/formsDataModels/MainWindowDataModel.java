package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.models.ListModelImplementation;

import javax.swing.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public class MainWindowDataModel {
    private DAO dao = DAOImplementation.getInstance();
    private  OnChangeListener listener;

    public void setListener(OnChangeListener listener) {
        this.listener = listener;
    }

    public ListModel getTrainingFeedsModel() {
        return new ListModelImplementation(this.getTrainingFeeds());
    }

    private List getTrainingFeeds() {
        return dao.getTrainingFeeds();
    }

    public void remTrainingFeed(TrainingFeed trainingFeed) {
        dao.removeTrainingFeed(trainingFeed);
        if(listener != null) {
            listener.onTrainingFeedRemoved();
        }
    }
    public interface OnChangeListener{
        public void onTrainingFeedRemoved();
    }
}
