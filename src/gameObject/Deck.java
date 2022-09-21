package gameObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 山札クラス
 */
public class Deck {

    private List<Card> deck;
    private int deckNumber;

    public Deck(){
        deck = new ArrayList<Card>();
        deckNumber = 1;
    }

    /**
     * 山札を作成し、シャッフルする。
     */
    public void create() {
        deck = new ArrayList<Card>();

        Card.Suit[] marks = new Card.Suit[]{ Card.Suit.Heart, Card.Suit.Diamond, Card.Suit.Spade, Card.Suit.Crab };
        int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
        for(int i = 0; i < deckNumber; i++){
            for(Card.Suit mark : marks) {
                for(int number : numbers) {
                    Card card = new Card(mark,number);
                    deck.add(card);
                }
            }
        }
        
        Collections.shuffle(deck);
    }

    public void setDeckNumber(int i){
        deckNumber = i;
    }

    /**
     * 山札から1枚引く。
     * @return 引いたカード
     */
    public Card pop() {
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }

    /**
     * 山札の残りの枚数を取得する。
     * @return 山札の残りの枚数
     */
    public int size(){
        return deck.size();
    }
}
