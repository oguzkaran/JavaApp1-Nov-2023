package org.csystem.app.game.card;

import org.csystem.game.card.Card;

public class CardGameDemoApp {
    public static void run()
    {
        Card[] deck;

        deck = Card.getShuffledDeck();

        for (Card c : deck)
            System.out.println(c.toString());
    }

    public static void main(String[] args)
    {
        run();
    }
}
