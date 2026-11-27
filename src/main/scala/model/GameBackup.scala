package model

case class GameBackup (player1Hand: List[Card],
                  player2Hand: List[Card],
                  deckCards: List[Card],
                  discardCards: List[Card],
                  attacker: Player,
                  defender: Player,
                  state: GameState,
                  attackingCard: Option[Card],
                  defendingCard: Option[Card])
