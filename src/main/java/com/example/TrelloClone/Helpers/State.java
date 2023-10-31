package com.example.TrelloClone.Helpers;

//This enum describes the three states in which a task can be in.
public enum State {
    TODO(0),
    DOING(1),
    DONE(2);
    private int numVal;

    State(int numval) {
        this.numVal = numval;
    }

    public int getNumVal() {
        return numVal;
    }
}
