package me.xxgradzix.gradzixcore.clansCore.exceptions;

public class ClanWithThisNameAlreadyExists extends Exception {

    public ClanWithThisNameAlreadyExists() {
        super("There is already a clan with this tag");
    }
}
