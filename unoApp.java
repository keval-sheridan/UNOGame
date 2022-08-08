import java.util.*;

public class unoApp {
    public static void main(String[] args) {
        
        int gameResult;
        int index;
        String currentCardColor;
        ArrayList<unoCards> playerDeck = new ArrayList<unoCards>();
        ArrayList<unoCards> computerDeck = new ArrayList<unoCards>();
        unoCards topMostCard;
        boolean playerTurn;
        String playerName;
        Scanner in;

        gameStart:
        while(true) {
            playerDeck.clear();
            computerDeck.clear();
            gameResult = 0;
            topMostCard = new unoCards();
            currentCardColor = topMostCard.cardColor;

            System.out.println("");
            System.out.println("Welcome to the UNO game. Enjoy the game...\n");
            System.out.print("Enter player name -----> ");
            Scanner inp = new Scanner(System.in);
            playerName = inp.next();
            draw(7,playerDeck);
            draw(7,computerDeck);

            for(playerTurn = true; gameResult == 0; playerTurn ^= true) {
                
                index = 0;
                System.out.println("\nTop Card -----> " + topMostCard.cardFace());

                if(playerTurn) {
                    System.out.println(playerName +"'s Turn Now");
                    for(int i=0; i<playerDeck.size(); i++) {
                        System.out.print(String.valueOf(i+1) + " -> " + ((unoCards) playerDeck.get(i)).cardFace() + "\n");
                    }
                    System.out.println(String.valueOf(playerDeck.size() + 1) + " -> " + "Draw Card" + "\n"
                                        + String.valueOf(playerDeck.size() + 2) + " -> " + "Quit Game");
                    do {
                        System.out.println(playerName + " : Enter your choice -----> ");
                        in = new Scanner(System.in);
                    } while (!in.hasNextInt());
                    index = in.nextInt() - 1;
                    if(index == playerDeck.size()) {
                        draw(1, playerDeck);
                    }
                    else if(index == playerDeck.size() + 1) {
                        break gameStart;
                    }
                    else if(((unoCards)playerDeck.get(index)).cardPlaceOrNot(topMostCard,currentCardColor)) {
                        topMostCard = (unoCards)playerDeck.get(index);
                        playerDeck.remove(index);
                        currentCardColor = topMostCard.cardColor;
                        if(topMostCard.cardNum >= 10) {
                            playerTurn = false;
                            switch(topMostCard.cardNum) {
                                case 12:
                                    System.out.println("Draw-2, Drawing 2 Cards");
                                    draw(2,computerDeck);
                                    break;
                                case 13: case 14:
                                    do{
                                        System.out.println("\nEnter the new card color -----> ");
                                        in = new Scanner(System.in);
                                    } while(!in.hasNext("R..|r..|G....|g....|B...|b...|Y.....|y....."));
                                    if(in.hasNext("R..|r..")) {
                                        currentCardColor = "Red";
                                    }
                                    else if(in.hasNext("B...|b...")) {
                                        currentCardColor = "Blue";
                                    }
                                    else if(in.hasNext("G....|g....")) {
                                        currentCardColor = "Green";
                                    }
                                    else if(in.hasNext("Y.....|y.....")) {
                                        currentCardColor = "Yellow";
                                    }
                                    System.out.println(playerName + " choose the color -> " + currentCardColor);
                                    if(topMostCard.cardNum == 14) {
                                        System.out.println("Draw-4, Drawing 4 Cards");
                                        draw(4,computerDeck);
                                    }
                                    break;
                            }
                        }
                    } 
                    else {
                        System.out.println("Invalid selection, " + playerName + "'s turn skipped.");
                    }
                }
                else {
                    System.out.println("Computer : My turn & I have " + String.valueOf(computerDeck.size()) + " cards left" + ((computerDeck.size() == 1)? "...UNO..." : ""));
                    for(index=0; index<computerDeck.size(); index++) {
                        if(((unoCards)computerDeck.get(index)).cardPlaceOrNot(topMostCard,currentCardColor)) {
                            break;
                        }
                        if(index == computerDeck.size()) {
                            System.out.println("Computer : I have nothing & Drawing the cards");
                            draw(1, computerDeck);
                        }
                        else {
                            topMostCard = (unoCards)computerDeck.get(index);
                            computerDeck.remove(index);
                            currentCardColor = topMostCard.cardColor;
                            System.out.println("Computer : I choosed " + topMostCard.cardFace());
                            if(topMostCard.cardNum >= 10) {
                                playerTurn = true;
                                switch(topMostCard.cardNum) {
                                    case 12:
                                        System.out.println("Draw-2, Drawing 2 Cards");
                                        draw(2,playerDeck);
                                        break;
                                    case 13: case 14:
                                        do{
                                            currentCardColor = new unoCards().cardColor;
                                        } while(currentCardColor == "Null");

                                        System.out.println("Computer : New color is " + currentCardColor);
                                        if(topMostCard.cardNum == 14) {
                                            System.out.println("Draw-4, Drawing 4 Cards");
                                            draw(4,playerDeck);
                                        }
                                        break;
                                }
                            }
                        }
                        if(playerDeck.size() == 0) {
                            gameResult = 1;
                        }
                        else if(computerDeck.size() == 0) {
                            gameResult = -1;
                        }
                    }
                }
            }
                if(gameResult == 1) {
                    System.out.println("Keval, You win the game");
                }
                else {
                    System.out.println("Keval, You lose the game");
                }

                System.out.println("\nDo you want to play again?");
                in = new Scanner(System.in);
                if(in.next().toLowerCase().contains("n")) {
                    break;
                }
            }
            System.out.println("Bye Keval");
        }
        
    public static void draw(int card, ArrayList<unoCards> deck)
    {
        for (int i = 0; i < card; i++)
            deck.add(new unoCards() );
    }
}