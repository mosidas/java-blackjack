package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import gameObject.Card;
import gameObject.GameManager;
import gameObject.Player;

/**
 * プレイヤー動作のCUI
 */
public class PlayersActionCui {
    /**
     * ベットする金額を設定する
     * @param scanner 標準入力
     * @param gameManager ゲーム
     */
    public static void setPlayersBet(Scanner scanner, GameManager gameManager) {
        for(Player player : gameManager.getPlayers()){
            while(true){
                try{
                    if(player.isGameOver()){
                        break;
                    }
                    System.out.print(player.getName() + "のベット(現在の所持金：" + player.getMoney() + ")：");
                    int input = scanner.nextInt();
                    if(1 <= input && input <= player.getMoney()){
                        player.setBet(input);
                        break;
                    }
                    System.out.println("1以上、所持金以下の整数値を入力してください");
                }
                catch(Exception e){
                    System.out.println("1以上、所持金以下の整数値を入力してください");
                    scanner.next();
                }
            }
        }
    }

    /**
     * 各プレイヤーのターンを表示する。
     * @param scanner 標準入力
     * @param gameManager ゲーム
     * @throws InterruptedException
     */
    public static void doPlayersTurn(Scanner scanner, GameManager gameManager) throws InterruptedException{
        for(Player player : gameManager.getPlayers()){
            if(player.isGameOver()){
                continue;
            }
            // プレイヤーのターンの内容を表示する。
            showPlayerTurns(scanner, gameManager, player);
            // バーストしてたらゲームオーバー。
            if(player.getHand().isBust()) {
                showResultBust(gameManager, player);
                continue;
            }
        }
    }

    /**
     * プレイヤーターンを表示する。
     * @param scanner
     */
    private static void showPlayerTurns(Scanner scanner, GameManager gameManager, Player player) {
        boolean stand = false;

        System.out.println(player.getName() + "のターン！");
        System.out.print(player.getName() + "の手札：");
        for(Card card : player.getHand().toList()){
            System.out.print(card.getText() + " ");
        }
        System.out.println("");

        // バーストするか、スタンドするか、山札がなくなるまでヒットかスタンドかを選ぶ
        while(!player.getHand().isBust() && !stand && gameManager.getDeck().size() > 0){
            int input = getPlayerAction(gameManager, scanner);
            if(input == 1)
            {
                player.hit(gameManager.getDeck());
            }
            else if(input == 2)
            {
                stand = true;
            }

            System.out.print(player.getName() + "の手札：");
            for(Card card : player.getHand().toList()){
                System.out.print(card.getText() + " ");
            }
            System.out.println("");
        }
    }

    /**
     * ヒット or スタンド の選択を取得する。
     * ヒット:1 スタンド:2
     * @param scanner
     * @return
     */
    private static int getPlayerAction(GameManager gameManager, Scanner scanner){
        while(true){
            try{
                System.out.print("どうする? ヒット:1 スタンド:2 →   ");
                int input = scanner.nextInt();
                if(input == 1 || input == 2){
                    return input;
                }
                System.out.println("1か2を入力してください");
            }
            catch(InputMismatchException e){
                System.out.println("1か2を入力してください");
                scanner.next();
            }
        }
    }

    /**
     * ゲームの結果を表示する。(バーストで敗北時)
     * @throws InterruptedException
     */
    private static void showResultBust(GameManager gameManager, Player player ) throws InterruptedException {
    	System.out.print(player.getName() + "の点数：");
        System.out.println(player.getHand().getScore());
        Thread.sleep(1000);
        System.out.println("バースト！");
        Thread.sleep(1000);
    }
    
}
