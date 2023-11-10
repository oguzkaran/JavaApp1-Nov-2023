package org.csystem.game.card;

public class Card {
   private CardValue m_value;
   private CardType m_type;

   //...
   private static void fillDeck(Card [] deck)
   {
      int index = 0;

      for (CardType cardType : CardType.values())
         for (CardValue cardValue : CardValue.values())
            deck[index++] = new Card(cardValue, cardType);
   }
	
   public static Card[] getShuffledDeck()
   {
      Card[] deck = new Card[52];

      fillDeck(deck);

      //TODO

      return deck;
   }

   public Card(CardValue value, CardType type)
   {
       m_value = value;
       m_type = type;
   }

   public Card(String name)
   {
      throw new UnsupportedOperationException("Not implemented yet!...");
   }

   public CardValue getValue()
   {
      return m_value;
   }

   public void setValue(CardValue value)
   {
      m_value = value;
   }

   public CardType getType()
   {
      return m_type;
   }

   public void setType(CardType type)
   {
      m_type = type;
   }


   public String getName()
   {
      throw new UnsupportedOperationException("Not implemented yet!...");
   }

   public void setName(String name)
   {
      throw new UnsupportedOperationException("Not implemented yet!...");
   }

   public boolean equals(Object other)
   {
      return other instanceof Card c && c.m_type == m_type && c.m_value == m_value;
   }

   public String toString()
   {
      return String.format("%s-%s", m_type.toString(), m_value.toString());
   }
}
