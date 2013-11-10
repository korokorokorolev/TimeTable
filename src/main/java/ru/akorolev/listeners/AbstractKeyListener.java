package ru.akorolev.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 02.11.13
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(condition(e)) {
            action();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public abstract boolean condition(KeyEvent e);
    public abstract void action();
}
