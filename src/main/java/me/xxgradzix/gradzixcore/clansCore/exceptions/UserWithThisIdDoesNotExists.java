package me.xxgradzix.gradzixcore.clansCore.exceptions;

public class UserWithThisIdDoesNotExists extends Exception {

    public UserWithThisIdDoesNotExists() {
        super("There is already a clan with this tag");
    }
}
