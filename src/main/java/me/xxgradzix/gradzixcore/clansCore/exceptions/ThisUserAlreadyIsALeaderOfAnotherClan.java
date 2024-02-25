package me.xxgradzix.gradzixcore.clansCore.exceptions;

public class ThisUserAlreadyIsALeaderOfAnotherClan extends Exception {

    public ThisUserAlreadyIsALeaderOfAnotherClan() {
        super("There is already a clan with this tag");
    }
}
