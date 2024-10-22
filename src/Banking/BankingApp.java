package Banking;

import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountDAO accountDAO = new BnkAccountDaoImpl(); // Assuming you have an implementation of AccountDAO
        TransactionView transactionView = new TransactionView();
        TransactionController controller = new TransactionController(accountDAO, transactionView);

        int choice;
        do {
            System.out.println("1. Create Account\n2. Make Deposit\n3. Make Withdrawal\n4. Update Balance\n5. View Balance\n6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Create Account
                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();
                    System.out.print("Enter customer name: ");
                    scanner.nextLine(); // Consume the newline character
                    String customerName = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();

                    Account newAccount = new Account(accountNumber, customerName, initialBalance);
                    controller.createAccount(newAccount);
                    break;

                case 2:
                    // Make Deposit
                    System.out.print("Enter account number: ");
                    int depositAccountNumber = scanner.nextInt();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();

                    controller.makeDeposit(depositAccountNumber, depositAmount);
                    break;

                case 3:
                    // Make Withdrawal
                    System.out.print("Enter account number: ");
                    int withdrawalAccountNumber = scanner.nextInt();
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();

                    controller.makeWithdrawal(withdrawalAccountNumber, withdrawalAmount);
                    break;

                case 4:
                    // Update Balance
                    System.out.print("Enter account number: ");
                    int updateAccountNumber = scanner.nextInt();
                    System.out.print("Enter new balance: ");
                    double newBalance = scanner.nextDouble();

                    controller.updateBalance(updateAccountNumber, newBalance);
                    break;

                case 5:
                    // View Balance
                    System.out.print("Enter account number: ");
                    int viewAccountNumber = scanner.nextInt();

                    controller.viewBalance(viewAccountNumber);
                    break;

                case 6:
                    System.out.println("Exiting the application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        scanner.close();
    }
}
