package Exercise5_Lab;

import java.util.ArrayList;

//@author S. Murat. ÖZDEMİR
//@since 29.04.2023 - 21:16
public class Ex5_20210808006 {

}

class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        if (balance < 0) {
            throw new InsufficientFundsException(balance);
        }
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new InvalidTransactionException(amount);
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new InvalidTransactionException(amount);
        }
        if (balance < amount) {
            throw new InsufficientFundsException(balance, amount);
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return "Account:" + accountNumber + ", Balance:" + balance;
    }
}

class Customer {
    private String name;
    private ArrayList<Account> accounts;

    public Customer(String name) {
        this.name = name;
        accounts = new ArrayList<>();
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFoundException(accountNumber);
    }

    public void addAccount(Account account) {
        try {
            getAccount(account.getAccountNumber());
            throw new AccountAlreadyExistsException(account.getAccountNumber());
        } catch (AccountNotFoundException e) {
            accounts.add(account);
        } finally {
            System.out.println(toString());
            System.out.println("Added account: " + account.getAccountNumber() + " with " + account.getBalance());
        }
    }

    public void removeAccount(String accountNumber) {
        try {
            Account account = getAccount(accountNumber);
            accounts.remove(account);
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void transfer(Account fromAccount, Account toAccount, double amount) {
        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        } catch (InvalidTransactionException e) {
            throw new InvalidTransactionException(e, "cannot transfer funds from account " + fromAccount + "to account" + toAccount);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        String str = "Customer " + name + ":\n";
        for (Account account : accounts) {
            str += "\t" + account.toString() + "\n";
        }
        return str;
    }
}

class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(double balance) {
        super("Wrong balance: " + balance);
    }

    public InsufficientFundsException(double balance, double amount) {
        super("Requested amount is " + amount + " but only " + balance + " remaining");
    }
}

class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException(String accountNumber) {
        super("Account number " + accountNumber + " already exists");
    }
}

class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String accountNumber) {
        super("Account number " + accountNumber + " does not exist");
    }
}

class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(double amount) {
        super("Invalid amount: " + amount);
    }

    public InvalidTransactionException(InvalidTransactionException e, String message) {
        super(message + ":\n\t" + e.getMessage());
    }
}