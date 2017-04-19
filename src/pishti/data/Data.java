package pishti.data;

import pishti.data.card.Card;

import java.util.*;

/**
 * Author: Andrew Arnold (3/31/2017)
 */
public class Data {
    private ArrayList<Card> deck, handUser, handAI, discard, capturedUser, capturedAI;
    private boolean userTurn, gameActive;

    public Data() {
        deck = new ArrayList<>();
        handUser = new ArrayList<>();
        handAI = new ArrayList<>();
        discard = new ArrayList<>();
        capturedUser = new ArrayList<>();
        capturedAI = new ArrayList<>();
        userTurn = true;
        gameActive = true;

        for (int i=1; i<=52; i++)
            deck.add(new Card(i));
    }

    public ArrayList<Card> getDeck() { return deck; }
    public ArrayList<Card> getHandUser() { return handUser; }
    public ArrayList<Card> getHandAI() { return handAI; }
    public ArrayList<Card> getDiscard() { return discard; }
    public ArrayList<Card> getCapturedUser() { return capturedUser; }
    public ArrayList<Card> getCapturedAI() { return capturedAI; }
    public boolean isUserTurn() { return userTurn; }
    public boolean isGameActive() { return gameActive; }

    public void switchTurn() { userTurn = !userTurn; }

    public void reset() {
        deck = new ArrayList<>();
        handUser = new ArrayList<>();
        handAI = new ArrayList<>();
        discard = new ArrayList<>();
        capturedUser = new ArrayList<>();
        capturedAI = new ArrayList<>();
        userTurn = true;
        gameActive = true;
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
}
