package edu.postech.csed332.homework1;

/**
 * An account with a high interest rate and a minimum balance.
 * The rate is 1% per day. E.g., if the balance is initially 100,
 * after 10 days (on the 11th day) the balance will be 100*(1.01)^10.
 * The balance should always be greater than or equal to 1000.
 */
class HighInterestAccount extends BaseAccount {

    public HighInterestAccount(int accountNumber, double balance, String owner) throws IllegalOperationException{
        super(accountNumber, balance, owner, 1.0, 1000);
    }
}
