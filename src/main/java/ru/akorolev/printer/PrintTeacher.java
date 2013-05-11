package ru.akorolev.printer;

import ru.akorolev.entities.Cell;
import ru.akorolev.entities.Teacher;
import ru.akorolev.staticsVariables.Days;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 11.05.13
 * Time: 9:36
 * To change this template use File | Settings | File Templates.
 */
public class PrintTeacher {
    List<Cell> cells;
    Teacher teacher;
    PrintStream printStream;

    public PrintTeacher(List<Cell> cells, Teacher teacher) {
        this.cells = cells;
        this.teacher = teacher;
    }
    public void print() throws FileNotFoundException {
        printStream = new PrintStream(teacher.getName() + ".html");
        printStream.println("<html>");
        printStream.println("<head>");
        printStream.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
        printStream.println("</meta>");
        printStream.println("</head>");
        printDay(Days.MONDAY);
        printDay(Days.TUESDAY);
        printDay(Days.WEDNESDAY);
        printDay(Days.THURSDAY);
        printDay(Days.FRIDAY);
        printDay(Days.SATURDAY);
        printStream.println("</html>");
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
        List<Cell> dayCells = getDayCells(day);
        for (int i = 0; i < 5; i++) {
            printStream.println("<tr>");
            printStream.println("<td width=\"1%\">");
            printStream.println(i + 1);
            printStream.println("</td>");

            printStream.println("<td width=\"99%\">");
            for(Cell cell : dayCells) {
                if(cell.getTrainingNum() == i) {
                    printStream.println(cell + " " + cell.getGroupsId());
                }
            }
            printStream.println("</td>");

            printStream.println("</tr>");
        }
        printStream.println("</table>");
    }
}
