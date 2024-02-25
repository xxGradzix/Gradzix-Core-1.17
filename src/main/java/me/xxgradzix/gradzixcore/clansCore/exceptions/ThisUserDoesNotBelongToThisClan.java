package me.xxgradzix.gradzixcore.clansCore.exceptions;

public class ThisUserDoesNotBelongToThisClan extends Exception {
    public ThisUserDoesNotBelongToThisClan() {
        super("This user does not belong to any clan");
    }
}
