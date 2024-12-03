/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blanket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author anmol
 * Group8 Game Code
 * December 3, 2024
 */
public class CardGame {

   private final Deck deck;
   private final TurnManager turnManager;
   private final GameRules gameRules;

   public CardGame(String[] playerNames) { 
      deck = new GroupOfCards(); 
      initializeDeck(); 

      List<Player> players = new ArrayList<>(); 

      for (String name : playerNames) { 
          players.add(PlayerFactory.createPlayer(name, name.equalsIgnoreCase("computer"))); 
      } 

      turnManager = new TurnManager(players); 
      gameRules = new MemoryGameRules(); 
  } 

  private void initializeDeck() { 
      String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"}; 
      String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; 

      for (String suit : suits) { 
          for (String value : values) { 
              deck.addCard(new Card(suit, value)); 
              deck.addCard(new Card(suit, value)); 
          } 
      } 

      deck.shuffle(); 
  } 

  public void startGame() { 
      List<Card> revealedCards = new ArrayList<>(); 
      
      while (!gameRules.isGameOver(deck)) { 
          Player currentPlayer = turnManager.getCurrentPlayer(); 
          gameRules.handleTurn(currentPlayer, deck, revealedCards, turnManager); 
          turnManager.nextPlayer();
      } 

      gameRules.declareWinner(turnManager.getPlayers()); 
  } 

  public static void main(String[] args) { 
      Scanner scanner = new Scanner(System.in); 
      System.out.println("Enter number of players:"); 

      int numPlayers = scanner.nextInt(); 
      scanner.nextLine(); 

      String[] playerNames = new String[numPlayers]; 

      for (int i = 0; i < numPlayers; i++) { 
          System.out.println("Enter player " + (i + 1) + " name (type 'computer' for an AI player):"); 
          playerNames[i] = scanner.nextLine(); 
      } 

      CardGame game = new CardGame(playerNames);   
      game.startGame();   
  }  
}