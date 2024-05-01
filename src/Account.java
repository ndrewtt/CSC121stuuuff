public class Account {
    private double balance;
    private int accountNumber;
    private static int numberOfAccounts = 1000;
    public Account(double initialDeposit) {
        this.balance = initialDeposit;
        this.accountNumber = numberOfAccounts++;
    }
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Amount deposited: $" + amount + ", Updated balance: $" + balance);
    }
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("Amount withdrawn: $" + amount + ", Updated balance: $" + balance);
        }
    }
    public String toString() {
        return "Account Number: " + accountNumber + ", Balance: $" + balance;
    }
    public double getBalance() {
        return balance;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
}
