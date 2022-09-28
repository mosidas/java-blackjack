package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import gameObject.Card;
import gameObject.Game;
import gameObject.Hand;
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
    public static void setPlayersBet(Scanner scanner, Game gameManager) {
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
     * 各プレイヤーのターンを実行する。
     * @param scanner 標準入力
     * @param gameManager ゲーム
     * @throws InterruptedException
     */
    public static void doPlayersTurn(Scanner scanner, Game gameManager) throws InterruptedException{
        for(Player player : gameManager.getPlayers()){
            if(player.isGameOver()){
                continue;
            }
            // プレイヤーのターンを実行する。
            doPlayerTurns(scanner, gameManager, player);
        }
    }

    /**
     * プレイヤーターンを実行する。
     * @param scanner
     * @throws InterruptedException
     */
    private static void doPlayerTurns(Scanner scanner, Game gameManager, Player player) throws InterruptedException {
        boolean stand = false;

        System.out.println(player.getName() + "のターン！");
        System.out.print(player.getName() + "の手札：");
        Hand hand = player.getHands().get(0);
        for(Card card : hand.toList()){
            System.out.print(card.getText() + " ");
        }
        System.out.println("");

        // バーストするか、スタンドするか、山札がなくなるまでヒットかスタンドかを選ぶ
        while(!hand.isBust() && !stand && gameManager.getDeck().size() > 0){
            int input = getPlayerAction(scanner, gameManager, player);
            if(input == 1)
            {
                // ヒット
                hand.hit(gameManager.getDeck());
            }
            else if(input == 2)
            {
                // スタンド
                stand = true;
            }
            else if(input == 3)
            {
                // サレンダー
            }
            else if(input == 4)
            {
                // スプリット
                player.split();
                doSplitedPlayerTurns(scanner, gameManager, player);
                return;
            }

            System.out.print(player.getName() + "の手札：");
            for(Card card : hand.toList()){
                System.out.print(card.getText() + " ");
            }
            System.out.println("");
        }

        // バーストしてたらゲームオーバー。
        if(hand.isBust()) {
            showResultBust(gameManager, player, hand);
        }
    }

    /**
     * スプリットしたプレイヤーのターンを実行する。
     * @param scanner
     * @param gameManager
     * @param player
     * @throws InterruptedException
     */
    private static void doSplitedPlayerTurns(Scanner scanner, Game gameManager, Player player) throws InterruptedException {
        System.out.println(player.getName() + "はスプリットした！");
        int i = 1;
        for(Hand hand : player.getHands()){
            boolean stand = false;
            System.out.print(player.getName() + "の手札"+ i + "：");
            for(Card card : hand.toList()){
                System.out.print(card.getText() + " ");
            }
            System.out.println("");
            // バーストするか、スタンドするか、山札がなくなるまでヒットかスタンドかを選ぶ
            while(!hand.isBust() && !stand && gameManager.getDeck().size() > 0){
                int input = getPlayerAction(scanner, gameManager, player);
                if(input == 1)
                {
                    // ヒット
                    hand.hit(gameManager.getDeck());
                }
                else if(input == 2)
                {
                    // スタンド
                    stand = true;
                }
                System.out.print(player.getName() + "の手札"+ i + "：");
                for(Card card : hand.toList()){
                    System.out.print(card.getText() + " ");
                }
                System.out.println("");
            }
            // バーストしてたらゲームオーバー。
            if(hand.isBust()) {
                showResultBust(gameManager, player, hand);
            }
            i++;
        }
    }

    /**
     * ヒット or スタンド の選択を取得する。
     * ヒット:1 スタンド:2
     * @param scanner
     * @return
     */
    private static int getPlayerAction(Scanner scanner, Game gameManager, Player player){
        while(true){
            try{
                System.out.print("どうする? ヒット:1 スタンド:2");
                if(canSurrender(gameManager, player))
                {
                    System.out.print(" サレンダー:3");
                }
                if(player.canSplit())
                {
                    System.out.print(" スプリット:4");
                }
                if(canDoubledown(gameManager, player))
                {
                    System.out.print(" ダブルダウン:5");
                }
                System.out.print(" →   ");
                int input = scanner.nextInt();
                if(input == 1
                    || input == 2
                    || (player.canSplit() && input == 4)){
                    return input;
                }
                System.out.println("選択可能な数値を入力してください");
            }
            catch(InputMismatchException e){
                System.out.println("選択可能な数値を入力してください");
                scanner.next();
            }
        }
    }

    private static boolean canDoubledown(Game gameManager, Player player) {
        // TODO: 実装
        return false;
    }

    private static boolean canSurrender(Game gameManager, Player player) {
        // TODO: 実装
        return false;
    }

    /**
     * ゲームの結果を表示する。(バースト時)
     * @throws InterruptedException
     */
    private static void showResultBust(Game gameManager, Player player, Hand hand) throws InterruptedException {
        System.out.print(player.getName() + "の点数：");
        System.out.println(hand.getScore());
        Thread.sleep(1000);
        System.out.println("バースト！");
        Thread.sleep(1000);
    }
}
