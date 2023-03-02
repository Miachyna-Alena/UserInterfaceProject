package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class UploadUtils {
    private static final Logger LOGGER = AqualityServices.getLogger();

    public static void uploadFile(File file) {
        StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException interruptedException) {
            LOGGER.error("Interrupted exception has occurred.");
        }
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException awtException) {
            LOGGER.error("Abstract Window Toolkit exception has occurred.");
        }

        assert robot != null;
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
