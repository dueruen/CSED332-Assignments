package edu.postech.csed332.homework1;

/**
 * An account with a low interest rate. The rate is 0.5% per day.
 * E.g., if the balance is initially 100, after 10 days (on the 11th day)
 * the balance will be 100*(1.005)^10.
 */
class LowInterestAccount extends BaseAccount {

    public LowInterestAccount(int accountNumber, double balance, String owner) throws IllegalOperationException{
        super(accountNumber, balance, owner, 0.5, 0);
    }
}
