
// model.Card data structure
case class Card(rank: String, suit: String) {
  def isTrump(trumpSuit: String): Boolean = suit == trumpSuit
}

// Create cards
val card1 = model.Card("7", "♦")
val card2 = model.Card("10", "♥")

// Access card properties
card1.rank
card1.suit
card2.isTrump("♥") // Returns true


// 2. model.Player hand (a list of cards)
val myHand = List(
  model.Card("6", "♥"),
  model.Card("9", "♣"),
  model.Card("King", "♦")
)

// Access hand data
myHand.length       // Number of cards in hand
myHand.head         // First card in hand
myHand.head.rank    // Rank of the first card


// Simple Game Field data structure
case class Field(
                  trumpSuit: String,
                  cardsInDeck: Int,
                  attackCard: model.Card,
                  defendCard: model.Card
                )

// Create an instance of the game field
val gameField = Field(
  trumpSuit = "♥",
  cardsInDeck = 24,
  attackCard = model.Card("7", "♦"),
  defendCard = model.Card("Jack", "♦")
)

// Access game field data
gameField.trumpSuit
gameField.attackCard.rank
gameField.defendCard.suit