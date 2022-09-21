package gameObject;

/**
 * カード
 */
public class Card {
    /**
     * スート型
     */
    public enum Suit {
        Heart,
        Crab,
        Spade,
        Diamond
    }

    private Suit suitName;
    private int number;

    /**
     * コンストラクタ スートと数値を指定する
     * @param suit スート
     * @param number 数値
     */
    public Card(Suit suit, int number) {
        suitName = suit;
        this.number = number;
    }

    /**
     * カードのテキストを取得する。
     * @return テキスト表記(♡K、♣4など)
     */
    public String getText(){
        String mark =
                    suitName == Suit.Heart ? "♡" :
                    suitName == Suit.Crab ? "♣":
                    suitName == Suit.Spade ? "♠":
                    suitName == Suit.Diamond ? "♦":
                    "";

        String noStr =
                number == 1 ? "A" :
                number == 11 ? "J":
                number == 12 ? "Q":
                number == 13 ? "K":
                String.valueOf(number);

        return mark + noStr;
    }

    /**
     * カードの数値を取得する。
     * @return カードの数値
     */
    public int getNumber(){
        return number;
    }
}
