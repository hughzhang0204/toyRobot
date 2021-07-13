package com.hugo.component;

public enum Command {
    LEFT("left"),
    RIGHT("right"),
    MOVE("move"),
    REPORT("report"),
    DEFAULT("");

    private String value;

    Command(String str) {
        value = str;

    }

    public static Command get(String str){
        for(Command co: Command.values()){
            if(co.value.equalsIgnoreCase(str)){
                return co;
            }
        }
        return DEFAULT;
    }
}

