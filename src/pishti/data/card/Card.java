package pishti.data.card;

/**
 * Author: Andrew Arnold (3/27/2017)
 *
 * wraps all data describing a standard playing card in a standard, organized deck of 52.
 */
public class Card {
    private int number;
    private Suit suit;
    private Rank rank;
    private boolean isFaceUp;
    private boolean isPisti;

    public Card(int number) {
        this.number = number;
        this.isFaceUp = false;
        this.isPisti = false;
        switch ((this.number-1)/13) {
            case 0: this.suit = Suit.SPADES; System.out.print(Suit.SPADES.getName()); break;
            case 1: this.suit = Suit.HEARTS; System.out.print(Suit.HEARTS.getName()); break;
            case 2: this.suit = Suit.DIAMONDS; System.out.print(Suit.DIAMONDS.getName()); break;
            case 3: this.suit = Suit.CLUBS; System.out.print(Suit.CLUBS.getName()); break;
            default: System.err.println("Invalid Card number initialization. Must be 1-52.");
        }
        for (Rank rank: Rank.values())
            if (rank.getNumber() == ((this.number-1)%13)+1) {
                this.rank = rank;
                System.out.println(rank.getName());
            }
    }

    public int getNumber() { return this.number; }
    public Suit getSuit() { return this.suit; }
    public Rank getRank() { return this.rank; }
    public boolean isFaceUp() { return this.isFaceUp; }
    public boolean isPisti() { return this.isPisti; }

    public void setFaceUp(boolean isFaceUp) { this.isFaceUp = isFaceUp; }
    public void setPisti(boolean isPisti) { this.isPisti = isPisti; }
}
