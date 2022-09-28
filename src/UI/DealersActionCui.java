package UI;

import java.util.List;

import gameObject.Card;
import gameObject.Game;
import gameObject.Hand;
import gameObject.Player;

public class DealersActionCui {
    /**
     * 最初の手札を配る。
     */
    public static void dealInitialHand(Game gameManager) {
        // 山札を作成する。
        gameManager.getDeck().create();
        // 手札を配る。
        gameManager.dealInitialHand();
        // 各プレイヤーの手札を表示する。
        for(Player player : gameManager.getPlayers()){
            System.out.print(player.getName() + "の手札：");
            for(Card card : player.getHands().get(0).toList()){
                System.out.print(card.getText() + " ");
            }
            System.out.println("");
        }
        // ディーラーの手札を表示する。
        System.out.print("ディーラーの手札：");
        List<Card> dcards = gameManager.getDealer().getHands().get(0).toList();
        System.out.print(dcards.get(0).getText() + " " + "??");
        System.out.println("");
    }

    /**
     * ディーラーのターンを表示する。
     * @throws InterruptedException
     */
    public static void doDealerTurns(Game gameManager) throws InterruptedException {
        System.out.print("ディーラーのターン.");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.println(".");
        Hand hand = gameManager.getDealer().getHands().get(0);
        while(!hand.isBust()
                && (hand.getScore() < 17 || (hand.isSoft17() && gameManager.isSoft17Hit()) )
                && gameManager.getDeck().size() > 0){
                    hand.hit(gameManager.getDeck());
        }
        System.out.print("ディーラーの手札：");
        for(Card card : hand.toList()){
            System.out.print(card.getText() + " ");
        }
        System.out.println("");
        Thread.sleep(1000);
    }
}
