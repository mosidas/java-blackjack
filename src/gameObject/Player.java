package gameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * プレイヤー
 */
public class Player {
    protected List<Hand> hands;
    private int money;
    private int bet;
    private String name;
    private boolean _isSurrender;

    /**
     * コンストラクタ
     */
    public Player(String n, int m){
        resetHand();
        money = m;
        bet = 0;
        name = n;
    }

    public String getName(){
        return name;
    }

    public void addMoney(int n){
        money += n;
    }

    public int getMoney(){
        return money;
    }

    public void resetHand()
    {
        _isSurrender = false;
        hands = new ArrayList<Hand>();
        hands.add(new Hand());
    }

    public int getBet(){
        return bet;
    }

    public void setBet(int b){
        bet = b;
    }

    public List<Hand> getHands(){
        return hands;
    }

    /**
     * ゲームオーバーかどうか。お金がなければゲームオーバー。
     * @return
     */
    public boolean isGameOver(){
        return getMoney() == 0;
    }

    public boolean canSplit(){
        Hand hand1 = new Hand();
        hand1.add(hands.get(0).toList().get(0));

        Hand hand2 = new Hand();
        hand2.add(hands.get(0).toList().get(1));

        return hands.size() == 1
        && hand1.getScore() == hand2.getScore()
        && money >= bet * 2;
    }

    public void split(Deck deck){
        hands.add(new Hand());
        hands.get(1).add(hands.get(0).toList().get(1));
        hands.get(0).toList().remove(1);
        hands.get(0).hit(deck);
        hands.get(1).hit(deck);
    }

    public void surrender(){
        _isSurrender = true;
    }

    public boolean isSurrender(){
        return _isSurrender;
    }
}
