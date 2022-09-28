package gameObject;
public class GameSettings {
    private final int playerCount;
    private final int deckCount;
    private final boolean soft17;
    private final int defaultMoney;

    public GameSettings(int pc, int dc, boolean s17, int dm){
        playerCount = pc;
        deckCount = dc;
        soft17 = s17;
        defaultMoney = dm;
    }

    public int getPlayerCount(){
        return playerCount;
    }

    public int getDeckCount(){
        return deckCount;
    }

    public boolean getSoft17(){
        return soft17;
    }

    public int getDefaultMoney(){
        return defaultMoney;
    }
}
