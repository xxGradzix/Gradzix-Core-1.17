package me.xxgradzix.gradzixcore.clansExtension.exceptions;

public class CantDeclareWarDuringWarTimeException extends Throwable {
    public CantDeclareWarDuringWarTimeException(String youAlreadyHaveWar) {
        super(youAlreadyHaveWar);
    }
}
