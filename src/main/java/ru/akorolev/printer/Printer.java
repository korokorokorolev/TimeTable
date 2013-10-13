package ru.akorolev.printer;

import ru.akorolev.entities.Cell;
import ru.akorolev.entities.Groups;
import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.staticsVariables.Days;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 10.05.13
 * Time: 10:46
 * To change this template use File | Settings | File Templates.
 */
public class Printer {
    List<Cell> cells;
    TrainingFeed trainingFeed;
    PrintStream printStream;

    public Printer(List<Cell> cells, TrainingFeed trainingFeed) {
        this.cells = cells;
        this.trainingFeed = trainingFeed;

    }
    public void print() throws FileNotFoundException {
        printStream = new PrintStream(trainingFeed.getName() + ".html");
        printStream.println("<html>");
        printStream.println("<head>");
        printStream.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
        printStream.println("</meta>");
        printStream.println("</head>");
        printGroups();
        printDay(Days.MONDAY);
        printDay(Days.TUESDAY);
        printDay(Days.WEDNESDAY);
        printDay(Days.THURSDAY);
        printDay(Days.FRIDAY);
        printDay(Days.SATURDAY);
        printStream.println("</html>");
        printStream.close();
    }

    private void printGroups() {
        printStream.println("<table border=4 width=\"100%\">");
        printStream.println("<tr>");
        printStream.println("<td width=\"1%\" border=0>");
        printStream.println("</td>");
        for(Groups g: trainingFeed.getGroupsList()) {
            printStream.println("<td>" + g + "</td>");
        }
        printStream.println("</tr>");
        printStream.println("</table>");
    }

    private List<Cell> getDayCells(String day) {
        List<Cell> res = new ArrayList<Cell>();
        for (Cell c : cells) {
            Cell cell = (Cell) c;
            if (cell.getDay().equals(day)) {
                res.add(cell);
            }
        }
        return res;
    }
    private void printDay(String day) {
        printStream.println(day);
        printStream.println("<table border=4 width=\"100%\">");
        for (int i = 0; i < 5; i++) {
            printStream.println("<tr>");
            printStream.println("<td width=\"1%\">");
            printStream.println(i + 1);
            printStream.println("</td>");
            for(Groups g: trainingFeed.getGroupsList()) {
                printStream.println("<td width=\"33%\">");
                List<Cell> dayCells = getDayCells(day);
                for(Cell cell : dayCells) {
                    if(cell.getGroupsId().equals(g) && cell.getTrainingNum() == i) {
                        printStream.println(cell);
                        break;
                    }
                }
                printStream.println("</td>");
            }
            printStream.println("</tr>");
        }
        printStream.println("</table>");
    }


}
