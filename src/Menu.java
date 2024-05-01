import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private Bank bank;
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.bank = new Bank();
    }
    public void displayMainMenu() {
        System.out.println("---------MENU---------");
        System.out.println("1. Open a New Account");
        System.out.println("2. Access Account");
        System.out.println("3. Close All Accounts");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                openNewAccount();
                break;
            case 2:
                accessExistingAccount();
                break;
            case 3:
                closeAllAccounts();
                break;
            case 4:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid entry.");
                break;
        }
    }
    public void openNewAccount() {
        System.out.println("Are you a new customer? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();
        if (response.equals("yes")) {
            System.out.println("Please enter your first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Please enter your last name: ");
            String lastName = scanner.nextLine();
            System.out.println("Please enter your 4 digit PIN: ");
            int pin = scanner.nextInt();
            Customer customer = new Customer(firstName, lastName, pin);
            bank.addCustomer(customer);
            System.out.println("New customer added successfully!");
            System.out.println("Please enter deposit amount: ");
            double initialDeposit = scanner.nextDouble();
            Account account = new Account(initialDeposit);
            customer.addAccount(account);
            System.out.println("New account opened successfully. Account Number: " + account.getAccountNumber());
            System.out.println("");
        } else if (response.equals("no")) {
            System.out.println("Please enter your 4 digit PIN: ");
            int pin = scanner.nextInt();
            Customer existingCustomer = bank.getCustomerByPin(pin);
            if (existingCustomer == null) {
                System.out.println("Invalid PIN.");
                System.out.println("");
                return;
            }
            System.out.println("Please enter initial deposit amount: ");
            double initialDeposit = scanner.nextDouble();
            Account account = new Account(initialDeposit);
            existingCustomer.addAccount(account);
            System.out.println("New account opened successfully. Account Number: " + account.getAccountNumber());
        } else {
            System.out.println("Invalid input.");
            System.out.println("");
        }
    }
    public void accessExistingAccount() {
        System.out.println("Please enter your 4 digit PIN: ");
        int pin = scanner.nextInt();
        Customer customer = bank.getCustomerByPin(pin);
        if (customer == null) {
            System.out.println("Invalid PIN.");
            System.out.println("");
            return;
        }
        System.out.println("Accounts associated with " + customer.getFirstName() + " " + customer.getLastName() + ": ");
        System.out.println(customer.getAllAccountInfo());
        System.out.println("Please enter account number to access: ");
        int accountNumber = scanner.nextInt();
        Account account = customer.getAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("Invalid account number.");
            System.out.println("");
            return;
        }
        // Display account menu and handle account operations (deposit, withdraw, etc.)
        displayAccountMenu(customer, account);
    }
    public void closeAllAccounts() {
        System.out.println("Please enter your 4 digit PIN to confirm account closure: ");
        int pin = scanner.nextInt();
        Customer customer = bank.getCustomerByPin(pin);
        if (customer == null) {
            System.out.println("Invalid PIN.");
            System.out.println("");
            return;
        }
        bank.removeCustomer(customer);
        System.out.println("All accounts closed successfully for " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("");
    }

    public void displayAccountMenu(Customer customer, Account account) {
        System.out.println("1. Deposit funds");
        System.out.println("2. Withdraw funds");
        System.out.println("3. Check account balance");
        System.out.println("4. Close account");
        System.out.println("5. Back to main menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        switch (choice) {
            case 1:
                deposit(account);
                displayAccountMenu(customer, account);
                break;
            case 2:
                withdraw(account);
                displayAccountMenu(customer, account);
                break;
            case 3:
                checkBalance(account);
                displayAccountMenu(customer, account);
                break;
            case 4:
                closeAccount(customer, account);
                break;
            case 5:
                displayMainMenu();
                break;
            default:
                System.out.println("Invalid entry.");
                displayAccountMenu(customer, account);
                break;
        }
    }
    public void deposit(Account account) {
        System.out.print("Please enter deposit amount: $");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("");
    }
    public void withdraw(Account account) {
        System.out.print("Please enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
        System.out.println("");
    }
    public void checkBalance(Account account) {
        System.out.println("Current balance: $" + account.getBalance());
        System.out.println("");
    }
    public void closeAccount(Customer customer, Account account) {
        customer.removeAccount(account);
        System.out.println("Account closed successfully.");
        System.out.println("");
    }
    public String getFirstName(Customer customer) {
        return customer.getFirstName();
    }
    public String getLastName(Customer customer) {
        return customer.getLastName();
    }
}
