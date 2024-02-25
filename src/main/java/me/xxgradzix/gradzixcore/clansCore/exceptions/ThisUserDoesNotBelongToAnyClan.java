package me.xxgradzix.gradzixcore.clansCore.exceptions;

public class ThisUserDoesNotBelongToAnyClan extends Exception {
    public ThisUserDoesNotBelongToAnyClan() {
        super("This user does not belong to any clan");
    }
}
