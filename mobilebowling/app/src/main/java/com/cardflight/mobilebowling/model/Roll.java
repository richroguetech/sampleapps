package com.cardflight.mobilebowling.model;

import java.util.HashMap;
import java.util.Map;

public class Roll {
    private int numPins = -1;
    private String rollValue = "";
    public int ballNumber;
    private RollType rollType;
    public boolean isValid = false;

    public enum RollType
    {
        STRIKE("X"), SPARE("/"), GUTTER("-"),
        NUMERIC("N");

        private String rollValue;

        RollType(String rollString)
        {
            this.setRollValue(rollString);
        }

        public String getRollValue()
        {
            return rollValue;
        }

        public void setRollValue(String rollString)
        {
            this.rollValue = rollString;
        }

        private static final Map<String, RollType> lookup = new HashMap<>();

        static
        {
            for(RollType type: RollType.values()) {
                lookup.put(type.getRollValue(), type);
            }
        }

        public static RollType get(String type)
        {
            return lookup.get(type);
        }
    }


    public void setType(RollType type) {

        this.rollType = type;
    }

    public RollType getType() {

        return this.rollType;
    }

    public void setRollValue(String rollValue) {

        this.rollValue = rollValue;
    }

    public String getRollValue() {

        return this.rollValue;
    }

    public int getNumPins() {
        return numPins;
    }

    public void setNumPins(int numPins) {
        this.numPins = numPins;
    }

    // this class is used to sanitize the input...
    public Roll(String input) {

        RollType type = RollType.get(input);
        setRollValue(input);

        if (type == RollType.STRIKE) {
            setNumPins(10);
            setType(RollType.STRIKE);
        } else if (type == RollType.SPARE) {
            setNumPins(10); // total pins is 10 - value is 10 - first ball..
            setType(RollType.SPARE);
        } else if (type == RollType.GUTTER) {
            setNumPins(0);
            setType(RollType.GUTTER);
        }
        else {
            try {
                int pins = Integer.parseInt(input);
                if (pins >= 0 && pins <=9) {
                    // valid roll type...
                    setNumPins(pins);
                    setType(RollType.NUMERIC);
                } else {
                    setNumPins(-1);
                    isValid = false; // outside of range..
                }
            } catch(Exception ex) {
                // double check exception
                isValid = false;
            }
        }
        isValid = true;
    }

}
