package ru.akorolev.controllers;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 07.05.13
 * Time: 10:44
 * To change this template use File | Settings | File Templates.
 */
abstract class AbstractController {
    AbstractController() {
        initDataModel();
        initView();
        regDataListeners();
        regListeners();
    }

    abstract void initDataModel();
    abstract void initView();
    abstract void regDataListeners();
    abstract void regListeners();
}
