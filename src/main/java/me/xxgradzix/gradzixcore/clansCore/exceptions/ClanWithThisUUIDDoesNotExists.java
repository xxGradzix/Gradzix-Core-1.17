package me.xxgradzix.gradzixcore.clansCore.exceptions;

public class ClanWithThisUUIDDoesNotExists extends Exception {

    public ClanWithThisUUIDDoesNotExists() {
        super("There is already a clan with this tag");
    }
}
