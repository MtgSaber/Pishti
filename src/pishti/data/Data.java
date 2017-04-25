package pishti.data;

import pishti.data.card.Card;

import java.util.*;

/**
 * Author(s): Andrew Arnold, Kevin Tricolli, Zack Jenkins
 */
public class Data {
    private ArrayList<Card> deck, handUser, handAI, discard, capturedUser, capturedAI;
    private boolean isFirstTurn;

    public Data() {
        deck = new ArrayList<>();
        handUser = new ArrayList<>();
        handAI = new ArrayList<>();
        discard = new ArrayList<>();
        capturedUser = new ArrayList<>();
        capturedAI = new ArrayList<>();
        isFirstTurn = true;

        for (int i=1; i<=52; i++) {
            System.out.print(i);
            deck.add(new Card(i));
        }
    }

    public ArrayList<Card> getDeck() { return deck; }
    public ArrayList<Card> getHandUser() { return handUser; }
    public ArrayList<Card> getHandAI() { return handAI; }
    public ArrayList<Card> getDiscard() { return discard; }
    public ArrayList<Card> getCapturedUser() { return capturedUser; }
    public ArrayList<Card> getCapturedAI() { return capturedAI; }
    public boolean getIsFirstTurn() { return isFirstTurn; }
    public void firstTurnFinish() { isFirstTurn = false; }

    public void reset() {
        deck = new ArrayList<>();
        handUser = new ArrayList<>();
        handAI = new ArrayList<>();
        discard = new ArrayList<>();
        capturedUser = new ArrayList<>();
        capturedAI = new ArrayList<>();
    }

    public void deckShuffle() {
        Card[] cards = deck.toArray(new Card[deck.size()]);
        deck = new ArrayList<>();
        Random rng = new Random();
        
        for (int i=0; i<52; i++) {
            int index = rng.nextInt(52);
            while (cards[index]==null) { index = rng.nextInt(cards.length); }
            deck.add(cards[index]);
            cards[index] = null;
        }
    }

    public String toString() {
        return "Data Info:\n\tDeck Size:\t\t"+deck.size()
                +"\n\tDiscard Size:"+discard.size()
                +"\n\tPlayer's Hand Size:\t\t"+handUser.size()
                +"\n\tAI's Hand Size:\t\t"+handAI.size()
                +"\n\tPlayer's Capture Size:\t\t"+capturedUser.size()
                +"\n\tAI's Capture Size:\t\t"+capturedAI.size();
    }
}
