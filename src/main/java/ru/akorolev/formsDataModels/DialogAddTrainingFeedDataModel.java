package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Groups;
import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.models.ListModelImplementation;

import javax.swing.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
public class DialogAddTrainingFeedDataModel {
    DAO dao = DAOImplementation.getInstance();
    private OnChangeListener listener;

    public void setOnchangeListener(OnChangeListener listener) {
        this.listener = listener;
    }

    private List getGroupsList(TrainingFeed trainingFeed) {
        return trainingFeed.getGroupsList();
    }
    public ListModel getGroupsListModel(TrainingFeed trainingFeed) {
        return new ListModelImplementation(getGroupsList(trainingFeed));
    }

    public void remGroupFromTrainingFeed(TrainingFeed trainingFeed, Groups group) {
        trainingFeed.deleteGroup(group);
        if(listener != null) {
            listener.onGroupRemoved(trainingFeed);
        }
    }

    public void saveTrainingFeed(TrainingFeed trainingFeed) {
        dao.saveTrainingFeed(trainingFeed);
    }

    public interface OnChangeListener{
        public void onGroupRemoved(TrainingFeed trainingFedd);
    }
}
