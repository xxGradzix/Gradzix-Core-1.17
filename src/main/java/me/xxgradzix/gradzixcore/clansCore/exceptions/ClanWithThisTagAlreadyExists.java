package me.xxgradzix.gradzixcore.clansCore.exceptions;

public class ClanWithThisTagAlreadyExists extends Exception {

    public ClanWithThisTagAlreadyExists() {
        super("There is already a clan with this tag");
    }
}
