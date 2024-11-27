package Banking.view;

import javax.swing.*;

import Banking.controller.AccountController;
import Banking.model.Account;
import Banking.session.UserSession;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountView {
    private JFrame frame;

    // show main menu
    public void displayMainMenu(AccountController controller) {
        frame = new JFrame("Main Menu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to the Banking System");
        welcomeLabel.setBounds(100, 30, 200, 25);
        panel.add(welcomeLabel);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(50, 70, 120, 25);
        panel.add(registerButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 70, 120, 25);
        panel.add(loginButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(125, 120, 120, 25);
        panel.add(exitButton);

        // add action listener to buttons
        registerButton.addActionListener(e -> {
            frame.dispose();
            controller.navigateToRegister();
        });

        loginButton.addActionListener(e -> {
            frame.dispose();
            controller.navigateToLogin();
        });

        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    // show register form
    public void displayRegisterForm(AccountController controller) {
        frame = new JFrame("Register");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel accountLabel = new JLabel("Account Number(6-digital,from 100000):");
        accountLabel.setBounds(150, 50, 400, 25);
        panel.add(accountLabel);

        JTextField accountNumberField = new JTextField(20);
        accountNumberField.setBounds(450, 50, 200, 25);
        panel.add(accountNumberField);

        JLabel clientLabel = new JLabel("Client Name:");
        clientLabel.setBounds(250, 100, 200, 25);
        panel.add(clientLabel);

        JTextField clientNameField = new JTextField(20);
        clientNameField.setBounds(450, 100, 200, 25);
        panel.add(clientNameField);

        JLabel passwordLabel = new JLabel("Password(4-digital):");
        passwordLabel.setBounds(250, 150, 200, 25);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(450, 150, 200, 25);
        panel.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(250, 400, 120, 25);
        panel.add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(500, 400, 120, 25);
        panel.add(backButton);

        // add action listener to buttons
        registerButton.addActionListener(e -> {
            int accountNumber = Integer.parseInt(accountNumberField.getText());
            String clientName = clientNameField.getText();
            String password = new String(passwordField.getPassword());
            // validate input
            if (accountNumber > 999999 || accountNumber < 100000) {
                JOptionPane.showMessageDialog(frame, "Invalid client name, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (password.length() != 4) {
                JOptionPane.showMessageDialog(frame, "Invalid password, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else{
                UserSession currentUser = controller.handleRegister(accountNumber, clientName, password);
                JOptionPane.showMessageDialog(frame, "Account created successfully!");
                if (currentUser != null){
                    frame.dispose();
                    controller.navigateToUserMenu(currentUser);
                }else{
                    JOptionPane.showMessageDialog(frame, "Account creation failed, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            controller.navigateToMainMenu();
        });

        frame.setVisible(true);
    }

    // show login form
    public void displayLoginForm(AccountController controller) {
        frame = new JFrame("Login");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel accountLabel = new JLabel("Account Number:");
        accountLabel.setBounds(250, 50, 200, 25);
        panel.add(accountLabel);

        JTextField acountNumberField = new JTextField(20);
        acountNumberField.setBounds(450, 50, 200, 25);
        panel.add(acountNumberField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(250, 100, 200, 25);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(450, 100, 200, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 400, 120, 25);
        panel.add(loginButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(500, 400, 120, 25);
        panel.add(backButton);

        // add action listener to buttons
        loginButton.addActionListener(e -> {
            int accountNumber = Integer.parseInt(acountNumberField.getText());
            String password = new String(passwordField.getPassword());
            // validate input
            if (accountNumber > 999999 || accountNumber < 100000){
                JOptionPane.showMessageDialog(frame, "Invalid account number, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (password.length() != 4){
                JOptionPane.showMessageDialog(frame, "Invalid password, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            UserSession currentUser = controller.handleLogin(accountNumber, password);
            if (currentUser != null){
                JOptionPane.showMessageDialog(frame, "Login successfully!");
                frame.dispose();
                controller.navigateToUserMenu(currentUser);
            }else {
                JOptionPane.showMessageDialog(frame, "Login failed, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            controller.navigateToMainMenu();
        });

        frame.setVisible(true);
    }    

    // show user menu
    public void displayUserMenu(AccountController controller, UserSession currentUser) {
        frame = new JFrame("User Menu");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to your account!");
        welcomeLabel.setBounds(100, 30, 200, 25);
        panel.add(welcomeLabel);

        JButton checkAccountButton = new JButton("Check Account Information");
        checkAccountButton.setBounds(50, 80, 250, 25);
        panel.add(checkAccountButton);

        JButton depositButton = new JButton("Make a Deposit");
        depositButton.setBounds(50, 120, 250, 25);
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Make a Withdraw");
        withdrawButton.setBounds(50, 160, 250, 25);
        panel.add(withdrawButton);

        JButton changeInfoButton = new JButton("Change Account Information");
        changeInfoButton.setBounds(50, 200, 250, 25);
        panel.add(changeInfoButton);

        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(50, 240, 250, 25);
        panel.add(changePasswordButton);

        JButton deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setBounds(50, 280, 250, 25);
        panel.add(deleteAccountButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(50, 320, 250, 25);
        panel.add(logoutButton);

        // add action listener to buttons
        checkAccountButton.addActionListener(e -> {
            Account account = controller.getAccountDetail(currentUser);
            JOptionPane.showMessageDialog(frame,account.toString());
        });

        depositButton.addActionListener(e -> {
            double amount = Double.parseDouble(JOptionPane.showInputDialog("Please input the amount you want to deposit:"));
            if (amount <= 0){
                JOptionPane.showMessageDialog(frame, "Invalid amount, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else {
                if (controller.makeDeposit(currentUser,amount)){
                    JOptionPane.showMessageDialog(frame, "Deposit successfully!");
                }else{
                    JOptionPane.showMessageDialog(frame, "Deposit failed, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        withdrawButton.addActionListener(e -> {
            double amount = Double.parseDouble(JOptionPane.showInputDialog("Please input the amount you want to withdraw:"));
            if (amount <= 0){
                JOptionPane.showMessageDialog(frame, "Invalid amount, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else {
                if (controller.makeWithdrawal(currentUser,amount)) {
                    JOptionPane.showMessageDialog(frame, "Withdraw successfully!");
                }else{
                    JOptionPane.showMessageDialog(frame, "Insufficient balance, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        changeInfoButton.addActionListener(e -> {
            String clientName = JOptionPane.showInputDialog("Please input the new client name:");
            if (clientName == null || clientName.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Invalid client name, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else {
                if (controller.updateAccountInfo(currentUser, clientName)){
                    JOptionPane.showMessageDialog(frame, "Account information updated successfully!");
                }else {
                    JOptionPane.showMessageDialog(frame, "Account information update failed, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        changePasswordButton.addActionListener(e -> {
            String password = JOptionPane.showInputDialog("Please input the new password:");
            if (password.length() != 4){
                JOptionPane.showMessageDialog(frame, "Invalid password, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else {
                if (controller.updatePassword(currentUser, password)){
                    JOptionPane.showMessageDialog(frame, "Password updated successfully!");
                }else{
                    JOptionPane.showMessageDialog(frame, "Password update failed, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteAccountButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete your account?", "Delete Account", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION){
                if (controller.deleteAccount(currentUser)){
                    JOptionPane.showMessageDialog(frame, "Account deleted successfully!");
                    frame.dispose();
                    controller.logout(currentUser);
                    controller.navigateToMainMenu();
                }else{
                    JOptionPane.showMessageDialog(frame, "Account deletion failed, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        logoutButton.addActionListener(e -> {
            frame.dispose();
            controller.logout(currentUser);
            controller.navigateToMainMenu();
        });

        frame.setVisible(true);
    }
}
