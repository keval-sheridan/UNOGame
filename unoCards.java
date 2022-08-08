import java.util.*;

public class unoCards {
    
    private Random randomNum;
    private String cardFace;
    String cardColor;
    int cardNum;

    public unoCards(int cn, String cc) {
        this.cardNum = cn;
        this.cardFace = cc;
    }

    public unoCards() {

        randomNum = new Random();
        cardNum = randomNum.nextInt(28);

        if(cardNum >= 14) {
            cardNum = cardNum - 14;
        }

        randomNum = new Random();
        switch(randomNum.nextInt(4)) {
            case 0:
                cardColor = "Yellow";
                break;

            case 1:
                cardColor = "Green";
                break;

            case 2:
                cardColor = "Red";
                break;

            case 3:
                cardColor = "Blue";
                break;
        }

        if(cardNum >= 13) {
            cardColor = "Null";
        }

    }

    public String cardFace() {

        cardFace = "(";

        if(cardColor != "Null") {
            cardFace = cardFace + this.cardColor + " ";
        }

        switch(this.cardNum) {
            case 10:
                cardFace = cardFace + "Skip";
                break;
            case 11:
                cardFace = cardFace + "Reverse";
                break;
            case 12:
                cardFace = cardFace + "Draw-2";
                break;
            case 13:
                cardFace = cardFace + "Wild";
                break;
            case 14:
                cardFace = cardFace + "Draw-4";
                break;
            default:
                cardFace = cardFace + String.valueOf(this.cardNum);
        }

        cardFace = cardFace + ")";

        return cardFace;
    
    }

    public boolean cardPlaceOrNot(unoCards obj, String ucc) {
        if(this.cardColor == ucc) {
            return true;
        }
        else if(this.cardNum == obj.cardNum) {
            return true;
        }
        else if(this.cardColor == "Null") {
            return true;
        }
        else {
            return false;
        }
    }

}