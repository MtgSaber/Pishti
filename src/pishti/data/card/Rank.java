package pishti.data.card;

/**
 * Author: Andrew Arnold (3/27/2017)
 */
public enum Rank {
    ACE     (1, "Ace"),
    TWO     (2, "Two"),
    THREE   (3, "Three"),
    FOUR    (4, "Four"),
    FIVE    (5, "Five"),
    SIX     (6, "Six"),
    SEVEN   (7, "Seven"),
    Eight   (8, "Eight"),
    NINE    (9, "Nine"),
    TEN     (10, "Ten"),
    JACK    (11, "Jack"),
    QUEEN   (12, "Queen"),
    KING    (13, "King");

    private final int number;
    private final String name;

    Rank(int number, String name) {
        this.name = name;
        this.number = number;
    }

    public int getNumber() { return this.number; }
    public String getName() { return this.name; }
}
