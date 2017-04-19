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
        Card[] cards = new Card[deck.size()];
        Random rng = new Random();
        
        for (Card card: deck) {
            int index = rng.nextInt(cards.length);
            while (cards[index]!=null) { index = rng.nextInt(cards.length); }
            cards[index] = deck.remove(index);
        }

        deck.addAll(Arrays.asList(cards));
    }
}
