package me.xxgradzix.gradzixcore.clansCore.exceptions;

public class ThisClanDoesNotExists extends Exception {

    public ThisClanDoesNotExists() {
        super("There is already a clan with this tag");
    }
}
