package edu.postech.csed332.homework1;

import java.util.*;

/**
 * Bank manages a collection of accounts. An account number is assigned
 * incrementally from 100000. E.g., the first account has 100000, the second
 * has 100001, etc. There are also functions for finding specific accounts.
 */
public class Bank {
    /**
     * A list of account instances.
     */
    List<Account> accs;

    /**
     * A map from an ownwer name to accounts for searching.
     */
    Map<String, ArrayList<Account>> accsByName;

    private int nextAccountNumber = 100000;

    /**
     * Create a bank and initialize its collections.
     */
    Bank() {
        accs = new ArrayList<>();
        accsByName = new HashMap<>();
    }

    /**
     * Find an account by a given account number.
     *
     * @param accNum an account number
     * @return the account with number accNum; null if no such account exists
     */
    Account findAccount(int accNum) {
        for (Account a : accs) {
            if (a.getAccountNumber() == accNum) {
                return a;
            }
        }
        return null;
    }

    /**
     * Find an account by owner's name.
     *
     * @param name owner's name
     * @return a list of accounts by name
     */
    List<Account> findAccountByName(String name) {
        ArrayList<Account> aList = accsByName.get(name);
        Comparator<Account> compareByAccountNumber = (Account a1, Account a2) -> Integer.compare(a1.getAccountNumber(), a2.getAccountNumber());
        Collections.sort(aList, compareByAccountNumber);
        return aList;
    }

    /**
     * Create a new ccount and register it to the bank. The collections
     * accs and accsByName must be updated accordingly.
     *
     * @param name    owner name
     * @param accType HIGH or LOW
     * @param initial initial balance
     * @return the newly created account; null if not possible
     */
    Account createAccount(String name, ACCTYPE accType, double initial) {
        BaseAccount account = null;
        try {
            switch (accType) {
                case LOW:
                    account = new LowInterestAccount(getNextAccountNumber(), initial, name);
                    break;
                case HIGH:
                    account = new HighInterestAccount(getNextAccountNumber(), initial, name);
                    break;
            }
        }catch (IllegalOperationException e) {
            return null;
        }
        accs.add(account);
        if (!accsByName.containsKey(name)) {
            accsByName.put(name, new ArrayList<Account>());
        }
        accsByName.get(name).add(account);
        return account;
    }

    /**
     * Transfer a given amount of money from src account to dst account.
     *
     * @param src    source account
     * @param dst    destination acount
     * @param amount of money
     * @throws IllegalOperationException if not possible
     */
    void transfer(Account src, Account dst, double amount) throws IllegalOperationException {
        src.withdraw(amount);
        dst.deposit(amount);
    }

    private int getNextAccountNumber() {
        int a = nextAccountNumber;
        nextAccountNumber++;
        return a;
    }
}