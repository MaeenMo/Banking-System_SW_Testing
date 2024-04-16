package org.robot;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
public class bot {
    //to access the clipboard
    public static void clip(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents (stringSelection, stringSelection);
    }
    public static void main(String[] args) throws IOException,AWTException {
        Robot robot = new Robot();
        //Copy&paste
        String [] text=new String[10];
        text[0]="d:";//select hard disk i
        text[1]="clear";//clean the screen
        text[2]="mkdir important";//create directory important
        text[3]="wget \"https://pbs.twimg.com/media/DsYYDFYWoAAwEUl?format=jpg&name=small\" -OUTFILE \"d:/important/wc.jpg\"";
        text[4]="exit";
        text[5]="d:\\important";

        //Desktop Wait
        robot.delay(1000);
        //powershell
        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyPress(KeyEvent.VK_X);
        robot.keyRelease(KeyEvent.VK_WINDOWS);
        robot.keyRelease(KeyEvent.VK_X);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_I);
        robot.keyRelease(KeyEvent.VK_I);
        //loop for copy&paste
        for (int i = 0; i<4;i++) {
            robot.delay(600);
            clip(text[i]);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        //open directory
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyRelease(KeyEvent.VK_WINDOWS);
        robot.delay(800);
        clip(text[5]);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress (KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(500); robot.keyPress (KeyEvent. VK_ENTER); robot.keyRelease(KeyEvent.VK_ENTER);
        //select pic
        robot.delay(800);
        robot.keyPress (KeyEvent.VK_CONTROL);
        robot.keyPress (KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_A);
        robot.delay(800);
        robot.keyPress (KeyEvent.VK_CONTEXT_MENU);
        robot.keyRelease(KeyEvent.VK_CONTEXT_MENU);
        robot.delay(800);
        for (int i=0; i<5;i++) {
            robot.keyPress (KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
