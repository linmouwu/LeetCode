/**
 * Created by mowerlin on 20/01/2017.
 */

import javax.smartcardio.Card;
import java.util.*;

public class CardDeck {

    private Set<PCard> used = new HashSet<>();
    private Random cardGen = new Random();

    public PCard getNewCard() {

        int randCard = cardGen.nextInt(13) + 1;
        Suit randSuit = Suit.random();
        PCard newCard = new PCard(randCard, randSuit);
        if (used.contains(newCard)) {
            return getNewCard();
        } else {
            return newCard;
        }
    }

    public void shuffle() {
        this.used = new HashSet<>();
    }


}

enum Suit {
    DIAMOND(1),
    CLUB(2),
    HEART(3),
    SPADE(4);
    private final int val;
    private final static Random random = new Random();
    private static final Suit[] suits = values();

    Suit(int val) {
        this.val = val;
    }

    public int getValue() {
        return this.val;
    }

    public static Suit random() {
        return suits[random.nextInt(4) + 1];
    }
}

class PCard {
    int card;
    Suit suit;

    PCard(int card, Suit suit) {
        this.card = card;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Card) {
            return false;
        }
        PCard other = (PCard) object;
        return this.card == other.card && this.suit == other.suit;
    }

    @Override
    public int hashCode() {
        return 13 * (suit.getValue() - 1) + card;
    }

}

abstract class User {
    int id;

    public abstract void drink();

    public String say() {
        return "Hello";
    }
}

interface Dance {
    void whirl();

    default void jump() {
        System.out.println("JUMP");
    }
}


