package org.robot;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class GUITestCases {

    // Helper method to type text using the robot
    private static void type(Robot robot, String text) {
        // Clear the existing text
        // Select all text in the field
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Press backspace to delete the selected text
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            if (Character.isUpperCase(c)) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.setAutoDelay(40);

        try {
            // Assuming the JavaFX app is packaged as a runnable jar named 'BankingApp.jar'
            String command = "java -jar path/to/BankingApp.jar";
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            System.out.println("Error executing the application");
            e.printStackTrace();
            return;
        }

        // Wait for the application to load
        Thread.sleep(10000); // Adjust this time based on your application's startup time

        // Coordinates should be adjusted based on your actual screen resolution and application window size
        // These coordinates assume the username and password fields and login button are visible and not occluded.
        // Move to the username field and type an incorrect username
        robot.mouseMove(600, 400); // Example coordinates for the username field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "wronguser");

        // Move to the password field and type an incorrect password
        robot.mouseMove(600, 450); // Example coordinates for the password field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "wrongpassword123");

        // Move to the login button and click
        robot.mouseMove(650, 550); // Example coordinates for the login button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        // Move to the username field and type 'username'
        robot.mouseMove(600, 400); // Example coordinates for the username field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "testuser");

        // Move to the password field and type 'password'
        robot.mouseMove(600, 450); // Example coordinates for the password field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "password123");

        // Move to the login button and click
        robot.mouseMove(650, 550); // Example coordinates for the login button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        System.out.println("Login test executed");

        //TESTING TRANSACTION

        // Move to the Transaction button and click
        robot.mouseMove(650, 550); // Example coordinates for the transaction button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        // Move to the Withdraw button and click
        robot.mouseMove(650, 550); // Example coordinates for the Withdraw button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        // Move to the text field and type 'amount'
        //TestCase#1
        robot.mouseMove(600, 400); // Example coordinates for the text field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "1000");
        //TestCase#2
        robot.mouseMove(600, 400); // Example coordinates for the text field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "-1000");
        //TestCase#3
        robot.mouseMove(600, 400); // Example coordinates for the text field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "0");
        //TestCase#4
        robot.mouseMove(600, 400); // Example coordinates for the text field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "account_balance"); // Replace 'account_balance' with the actual account balance
        //TestCase#5
        robot.mouseMove(600, 400); // Example coordinates for the text field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "account_balance+1"); // Replace 'account_balance' with the actual account balance
        //TestCase#6
        robot.mouseMove(600, 400); // Example coordinates for the text field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "0.0000000000001"); // Testing with a small amount

        System.out.println("Withdraw test executed");

        //Clicking Back to return to transactions page
        robot.mouseMove(650, 550); // Example coordinates for the Back button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        //entering deposit menu
        robot.mouseMove(650, 550); // Example coordinates for the Deposit button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        //TestCase#1
        robot.mouseMove(600, 400); // Example coordinates for the text field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "1000");
        //TestCase#2
        robot.mouseMove(600, 400); // Example coordinates for the text field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "-1000");
        //TestCase#3
        robot.mouseMove(600, 400); // Example coordinates for the text field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "0");
        //TestCase#4
        robot.mouseMove(600, 400); // Example coordinates for the text field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "10000000000000000000000000000000"); // Testing with a large amount
        //TestCase#5
        robot.mouseMove(600, 400); // Example coordinates for the text field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "0.0000000000001"); // Testing with a small amount

        System.out.println("Deposit test executed");

        //Clicking Back to return to transactions page
        robot.mouseMove(650, 550); // Example coordinates for the Back button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        //entering transfer menu
        robot.mouseMove(650, 550); // Example coordinates for the Transfer button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        //TestCase#1
        // Move to the amount field and type a valid amount
        robot.mouseMove(600, 400); // Example coordinates for the amount field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "1000");

        // Move to the destination account number field and type a valid account number
        robot.mouseMove(600, 450); // Example coordinates for the destination account number field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "123456789");

        // Move to the transfer button and click
        robot.mouseMove(650, 550); // Example coordinates for the transfer button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        //TestCase#2
        // Move to the amount field and type a negative amount
        robot.mouseMove(600, 400); // Example coordinates for the amount field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "-1000");

        // Move to the destination account number field and type a valid account number
        robot.mouseMove(600, 450); // Example coordinates for the destination account number field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "123456789");

        // Move to the transfer button and click
        robot.mouseMove(650, 550); // Example coordinates for the transfer button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        //Testcase#3
        // Move to the amount field and type an amount equal to the account balance
        robot.mouseMove(600, 400); // Example coordinates for the amount field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "account_balance"); // Replace 'account_balance' with the actual account balance

        // Move to the destination account number field and type a valid account number
        robot.mouseMove(600, 450); // Example coordinates for the destination account number field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "123456789");

        // Move to the transfer button and click
        robot.mouseMove(650, 550); // Example coordinates for the transfer button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        //TestCase#4
        // Move to the amount field and type an amount just over the account balance
        robot.mouseMove(600, 400); // Example coordinates for the amount field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "account_balance+1"); // Replace 'account_balance' with the actual account balance

        // Move to the destination account number field and type a valid account number
        robot.mouseMove(600, 450); // Example coordinates for the destination account number field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "123456789");

        // Move to the transfer button and click
        robot.mouseMove(650, 550); // Example coordinates for the transfer button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        //TestCase5
        // Move to the amount field and type a valid amount
        robot.mouseMove(600, 400); // Example coordinates for the amount field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "1000");

        // Move to the destination account number field and type an invalid account number
        robot.mouseMove(600, 450); // Example coordinates for the destination account number field
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        type(robot, "invalid_account_number"); // Replace 'invalid_account_number' with an actual invalid account number

        // Move to the transfer button and click
        robot.mouseMove(650, 550); // Example coordinates for the transfer button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        System.out.println("Transfer test executed");
    }
}
