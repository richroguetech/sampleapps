package com.cardflight.mobilebowling.model;

import java.util.HashMap;
import java.util.Map;

public class Roll {
    private int numPins = -1;
    private String rollValue = "";

    public enum RollType
    {
        STRIKE("X"), SPARE("/"), GUTTER("-");

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
            setRollValue(rollString);
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
        } else if (type == RollType.SPARE) {
            setNumPins(10);
        } else if (type == RollType.GUTTER) {
            setNumPins(0);
        }
        else {
            try {
                int pins = Integer.parseInt(input);
                setNumPins(pins);
            } catch(Exception ex) {
                // double check exception
            }
        }
    }

}
