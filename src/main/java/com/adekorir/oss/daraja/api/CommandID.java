package com.adekorir.oss.daraja.api;

/**
 * These are wrapper values for the M-Pesa Command IDs.
 */
public enum CommandID {

    /**
     * Reversal for an erroneous C2B transaction.
     */
    TRANSACTION_REVERSAL("TransactionReversal"),

    /**
     * Used to send money from employer to employee eg. salaries
     */
    SALARY_PAYMENT("SalaryPayment"),

    /**
     * Used to send money from business to customer eg. refunds
     */
    BUSINESS_PAYMENT("BusinessPayment"),

    /**
     * Used to send money when promotions take place eg. raffle winners
     */
    PROMOTION_PAYMENT("PromotionPayment"),

    /**
     * Used to check the balance in a PayBill/buy goods account (includes
     * utility, MMF, Merchant, Charges paid account).
     */
    ACCOUNT_BALANCE("AccountBalance"),

    /**
     * Used to simulate a transaction taking place in the case of C2B
     * Simulate Transaction.
     */
    CUSTOMER_PAY_BILL_ONLINE("CustomerPayBillOnline"),

    /**
     * Used to query the details of a transaction.
     */
    TRANSACTION_STATUS_QUERY("TransactionStatusQuery"),

    /**
     * Similar to STK push, uses M-Pesa PIN as a service
     */
    CHECK_IDENTITY("CheckIdentity"),

    /**
     * Sending funds from one paybill to another paybill.
     */
    BUSINESS_PAY_BILL("BusinessPayBill"),

    /**
     * Sending funds from buy goods to another buy goods.
     */
    BUSINESS_BUY_GOODS("BusinessBuyGoods"),

    /**
     * Transfer of funds from utility to MMF account.
     */
    DISBURSE_FUNDS_TO_BUSINESS("DisburseFundsToBusiness"),

    /**
     * Transferring funds from one paybills MMF to another paybills
     * MMF account.
     */
    BUSINESS_TO_BUSINESS_TRANSFER("BusinessToBusinessTransfer"),

    /**
     * Transferring funds from paybills to another paybills utility account.
     */
    BUSINESS_TRANSFER_FROM_MMF_TO_UTILITY("BusinessTransferFromMMFToUtility"),
    ;

    private final String command;

    CommandID(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return command;
    }
}
