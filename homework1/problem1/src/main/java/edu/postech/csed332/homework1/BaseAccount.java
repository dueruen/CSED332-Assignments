package edu.postech.csed332.homework1;

public abstract class BaseAccount implements Account{

    protected int accountNumber;
    protected double balance;
    protected String owner;
    protected final double DAILY_INTEREST_RATE;
    protected final int MIN_BALANCE;

    public BaseAccount(int accountNumber, double balance, String owner, double interest, int minBalance) throws IllegalOperationException {
        DAILY_INTEREST_RATE = interest;
        MIN_BALANCE = minBalance;

        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;

        if (balance < MIN_BALANCE) {
            throw new IllegalOperationException("To small start balance");
        }
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void updateBalance(int elapsedDate) {
        balance =  balance * Math.pow((1.0 + (DAILY_INTEREST_RATE / 100.0)), elapsedDate);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) throws IllegalOperationException {
        if (balance - amount >= MIN_BALANCE) {
            return;
        }
        throw new IllegalOperationException("To low balance");
    }
}