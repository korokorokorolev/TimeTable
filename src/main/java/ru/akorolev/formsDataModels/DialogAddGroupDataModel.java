package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Groups;
import ru.akorolev.entities.TrainingFeed;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 17:02
 * To change this template use File | Settings | File Templates.
 */
public class DialogAddGroupDataModel {

    public void addGroupToTrainingFeed(TrainingFeed trainingFeed, Groups group) {
        trainingFeed.addGroup(group);
    }
}
