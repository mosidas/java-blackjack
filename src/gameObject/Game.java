package gameObject;
import java.util.ArrayList;
import java.util.List;

/**
 * ゲーム管理クラス
 */
public class Game {
    private Deck deck;
    private List<Player> players;
    private Player dealer;
    private List<Player> retiredPlayers;
    private GameSettings settings;

    /**
     * ゲームの結果
     */
    public enum Results{
        win,
        lose,
        draw,
        surrender
    }

    public Game(GameSettings settings){
        players = new ArrayList<Player>();
        retiredPlayers = new ArrayList<Player>();
        dealer = new Player("ディーラー",0);
        deck = new Deck();
        this.settings = settings;
        initPlayers(this.settings.getPlayerCount(),this.settings.getDefaultMoney());
        getDeck().setDeckNumber(this.settings.getDeckCount());
    }

    public void initPlayers(int n, int m){
        for(int i = 0; i < n;i++){
            players.add(new Player("プレイヤー" + (i + 1), m));
        }
    }

    public List<Player> getPlayers(){
        return players;
    }

    public Player getDealer(){
        return dealer;
    }

    public Deck getDeck(){
        return deck;
    }

    public boolean isSoft17Hit(){
        return settings.getSoft17();
    }

    public int defaultMoney(){
        return settings.getDefaultMoney();
    }

    /**
     * 最初の手札を配る。
     */
    public void dealInitialHand(){
        for(Player player: getPlayers()){
            player.resetHand();
            player.getHands().get(0).hit(getDeck());
            player.getHands().get(0).hit(getDeck());
        }
        getDealer().resetHand();
        getDealer().getHands().get(0).hit(getDeck());
        getDealer().getHands().get(0).hit(getDeck());
    }

    public Results getResult(Player player, Hand hand){
        Hand dealersHand = getDealer().getHands().get(0);
        if(player.isSurrender())
        {
            return Results.surrender;
        }
        if(hand.isBust()){
            return Results.lose;
        }
        else if(dealersHand.isBust() ){
            return Results.win;
        }
        else{
            if(hand.isNaturalBlackjack() && !dealersHand.isNaturalBlackjack()){
                return Results.win;
            }
            else if(hand.isNaturalBlackjack() && dealersHand.isNaturalBlackjack()){
                return Results.draw;
            }
            else if(!hand.isNaturalBlackjack() && dealersHand.isNaturalBlackjack()){
                return Results.lose;
            }
            else if(hand.getScore() > dealersHand.getScore())
            {
                return Results.win;
            }
            else if (hand.getScore() < dealersHand.getScore())
            {
                return Results.lose;
            }
            else{
                return Results.draw;
            }
        }

    }

    public void calcReturn(Player player, Hand hand){
        Results r = getResult(player, hand);
            if(r == Results.win){
                player.addMoney(player.getBet());
            }
            else if(r == Results.lose){
                player.addMoney(-player.getBet());
            }
            else if (r == Results.surrender){
                player.addMoney(-player.getBet() * 1/2);
            }
    }

    public boolean allPlayerHandsIsBust(){
        for(Player player : getPlayers()){
            for(Hand hand : player.getHands())
            if(!hand.isBust()){
                return false;
            }
        }

        return true;
    }

    public void putRetiredPlayer(Player player){
        retiredPlayers.add(player);
        getPlayers().remove(player);
    }

    public List<Player> GetAllPlayer(){
        List<Player> ps = new ArrayList<Player>();
        for(Player p : getPlayers()){
            ps.add(p);
        }

        for(Player p : retiredPlayers){
            ps.add(p);
        }

        return ps;
    }
}
