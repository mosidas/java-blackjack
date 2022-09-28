package UI;
import java.util.Comparator;
import java.util.stream.Collectors;

import gameObject.Game;
import gameObject.Player;

/**
 * ゲーム結果のCUI
 */
public class GameResultCui {
    /**
     * ゲームの結果を表示する。
     * @throws InterruptedException
     */
    public static void showResult(Game gameManager) throws InterruptedException{
        System.out.println("------------結果------------");
        Thread.sleep(1000);
        System.out.print("ディーラーの点数：");
        System.out.println(gameManager.getDealer().getHand().getScore());

        for(int i = 0;i < gameManager.getPlayers().size();i++){
            Player player = gameManager.getPlayers().get(i);
            Thread.sleep(1000);
            System.out.print(player.getName()+"の点数：");
            System.out.println(player.getHand().getScore());
            gameManager.calcReturn(i);
            int money = player.getMoney();
            int bet = player.getBet();
            Thread.sleep(1000);
            if(gameManager.getResult(i) == Game.Results.win){
                System.out.println("勝ちました！！！おめでとう！！！");
                System.out.println("お金：" + money + "(+" + bet + ")");
            }
            else if(gameManager.getResult(i) == Game.Results.lose){
                System.out.println("負けました...");
                System.out.println("お金：" + money + "(-" + bet + ")");
                if(player.isGameOver())
                {
                    System.out.println(player.getName() + "、 ゲームオーバー！");
                    gameManager.putRetiredPlayer(player);
                }
            }
            else{
                System.out.println("引き分けです！");
                System.out.println("お金：" + money + "(+-0)");
            }
        }

        System.out.println("----------------------------");
        Thread.sleep(1000);
    }

    public static void showResultBust(Game gameManager) throws InterruptedException{
        System.out.println("------------結果------------");
        for(int i = 0;i < gameManager.getPlayers().size();i++){
            Player player = gameManager.getPlayers().get(i);
            Thread.sleep(1000);
            System.out.print(player.getName()+"の点数：");
            System.out.println(player.getHand().getScore());
            Thread.sleep(1000);
            gameManager.calcReturn(i);
            System.out.println("お金：" + player.getMoney() + "(-" + player.getBet() + ")");
            if(player.isGameOver())
            {
                System.out.println(player.getName() + "、 ゲームオーバー！");
                gameManager.putRetiredPlayer(player);
            }
        }
        System.out.println("----------------------------");
        Thread.sleep(1000);
    }

    /**
     * ゲームの結果を表示する。
     * @throws InterruptedException
     */
    public static void showResultEnd(Game gameManager) throws InterruptedException{
        System.out.print("最終結果");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.println(".");
        int count = 1;
        for(Player p : gameManager.GetAllPlayer().stream().sorted(Comparator.comparing(Player::getMoney).reversed()).collect(Collectors.toList())){
            System.out.print(count + ":" + p.getName() + "   所持金：" + p.getMoney() + "(");
            int delta = p.getMoney() - gameManager.defaultMoney();
            if(delta > 0){
                System.out.print("+");
            }
            else if(delta == 0){
                System.out.print("+-");
            }
            System.out.println(delta + ")");
            Thread.sleep(1000);
            count++;
        }
    }
}
