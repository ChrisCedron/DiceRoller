package com.ccedron.diceroller;

public class RollResult {
    public int rollNumeric;
    public String rollText;
    public String name;

    public RollResult(int num, String text, String _name){
        rollNumeric = num;
        rollText = text;
        name = _name;
    }
}
