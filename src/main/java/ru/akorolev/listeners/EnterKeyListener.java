package ru.akorolev.listeners;

import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 02.11.13
 * Time: 19:45
 * To change this template use File | Settings | File Templates.
 */
public abstract class EnterKeyListener extends AbstractKeyListener {
    @Override
    public boolean condition(KeyEvent e) {
        return e.getKeyCode() == KeyEvent.VK_ENTER;
    }
}
