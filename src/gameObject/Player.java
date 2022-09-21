package gameObject;
/**
 * プレイヤー
 */
public class Player {
    protected Hand hand;
    private int money;
    private int bet;
    private String name;

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
        hand = new Hand();
    }

    public int getBet(){
        return bet;
    }

    public void setBet(int b){
        bet = b;
    }

    /**
     *  ヒットする。山札から１枚とって手札に加える。
     * @param deck 山札
     */
    public void hit(Deck deck){
        hand.add(deck.pop());
    }

    /**
     * 手札を取得する。
     * @return 手札にあるカードのリスト
     */
    public Hand getHand() {
        return hand;
    }

    public boolean isGameOver(){
        return getMoney() == 0;
    }
}
