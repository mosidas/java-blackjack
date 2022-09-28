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
            player.hit(getDeck());
            player.hit(getDeck());
        }
        
        getDealer().resetHand();
        getDealer().hit(getDeck());
        getDealer().hit(getDeck());
    }

    /**
     * ゲームの結果を取得する。
     * @return Results列挙型(win, lose, draw)
     */
    public Results getResult(int n){
        Player player = getPlayers().get(n);
        if(player.getHand().isBust()){
            return Results.lose;
        }
        else if(getDealer().getHand().isBust() ){
            return Results.win;
        }
        else{
            if(player.getHand().isNaturalBlackjack() && !getDealer().getHand().isNaturalBlackjack()){
                return Results.win;
            }
            else if(player.getHand().isNaturalBlackjack() && getDealer().getHand().isNaturalBlackjack()){
                return Results.draw;
            }
            else if(!player.getHand().isNaturalBlackjack() && getDealer().getHand().isNaturalBlackjack()){
                return Results.lose;
            }
            else if(player.getHand().getScore() > getDealer().getHand().getScore())
            {
                return Results.win;
            }
            else if (player.getHand().getScore() < getDealer().getHand().getScore())
            {
                return Results.lose;
            }
            else{
                return Results.draw;
            }
        }
    }

    public void calcReturn(int n){
        int bet = getPlayers().get(n).getBet();
        Results r = getResult(n);
        if(r == Results.win){
            getPlayers().get(n).addMoney(bet);
        }
        else if(r == Results.lose){
            getPlayers().get(n).addMoney(-bet);
        }
        else if (r == Results.surrender){
            getPlayers().get(n).addMoney(-bet * 1/2);
        }
    }

    public boolean allPlayersIsBust(){
        for(Player player : getPlayers()){
            if(!player.getHand().isBust()){
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
