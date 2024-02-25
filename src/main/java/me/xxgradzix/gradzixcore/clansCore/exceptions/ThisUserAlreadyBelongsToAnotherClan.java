package me.xxgradzix.gradzixcore.clansCore.exceptions;

public class ThisUserAlreadyBelongsToAnotherClan extends Exception {

    public ThisUserAlreadyBelongsToAnotherClan() {
        super("There is already a clan with this tag");
    }
}
