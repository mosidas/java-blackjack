package gameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 手札クラス
 */
public class Hand {
    private List<Card> cards;

    /**
     * コンストラクタ
     * フィールドの初期化
     */
    Hand(){
        cards = new ArrayList<Card>();
    }
    
    /**
     * カードを手札に加える。
     * @param card 手札に加えるカード
     */
    public void add(Card card){
        cards.add(card);
    }

    /**
     * 手札を取得する。
     * @return 手札(カードのリスト)
     */
    public List<Card> toList(){
        return cards;
    }

        /**
     * バーストしたか
     * @return スコアが21より大きい：true <br>
     * スコアが21以下：false
     */
    public boolean isBust(){
        if(getScore() > 21){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * スコアを取得する。
     * @return 現在の手札のスコア
     */
    public int getScore(){
        // 計算方法
        // まず、「10以下のカードの数値の合計 + ( 11以上(J, Q, K)のカードの枚数 × 10 )」を求める
        int score =
                (int) (cards.stream()
                    .filter(x -> x.getNumber() <= 10).mapToInt(x -> x.getNumber()).sum()
                    + cards.stream().filter(x -> x.getNumber() > 10).count() * 10);

        // その後、1がある場合は、21を超えない場合だけ10を加算する。
        for(int i = (int) cards.stream()
                        .filter(x -> x.getNumber() == 1).count(); i > 0; i--)
        {
            if(score + (10 * i )  <= 21)
            {
                score += 10 * i;
            }
        }

        return score;
    }

    /**
     * 手札がナチュラルブラックジャックか
     * @return true:ナチュラルブラックジャックである  false:ナチュラルブラックジャックではない
     */
    public boolean isNaturalBlackjack() {
        return getScore() == 21 && cards.stream().filter(x -> x.getNumber() == 1).count() == 1 && cards.size() == 2;
    }

    public boolean isSoft17(){
        if(getScore() == 17 && cards.stream().filter(x -> x.getNumber() == 1).count() >= 1){
            return true;
        }
        else{
            return false;
        }
        
    }
}
