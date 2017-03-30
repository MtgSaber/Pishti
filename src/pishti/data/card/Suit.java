package pishti.data.card;

/**
 * Author: Andrew Arnold (3/27/2017)
 */
public enum Suit {
    SPADES      ("Spades"),
    CLUBS       ("Clubs"),
    HEARTS      ("Hearts"),
    DIAMONDS    ("Diamonds");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}
