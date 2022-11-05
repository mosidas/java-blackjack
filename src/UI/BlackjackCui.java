package UI;
import java.util.Scanner;

import gameObject.Game;
import gameObject.GameSettings;

/**
 * ブラックジャックのCUIクラス
 */
public class BlackjackCui {
    private Game gameManager;

    /**
     * ゲームプレイ
     * @throws Exception
     */
    public void play() throws Exception{
        // タイトル表示
        GameTitleCui.showTitle();
        // 標準入力
        Scanner scanner = new Scanner(System.in);
        // ゲーム設定
        GameSettings gs = GameSettingCui.getGameSettings(scanner);
        gameManager = new Game(gs);
        // ゲーム終了するまでループ
        while(true) {
            // 掛け金を決める。
            PlayersActionCui.setPlayersBet(scanner, gameManager);
            // 最初の手札を配る。
            DealersActionCui.dealInitialHand(gameManager);
            // 各プレイヤーのターンを実行する。
            PlayersActionCui.doPlayersTurn(scanner, gameManager);
            // 全員バーストしてたら終了する。
            if(gameManager.allPlayerHandsIsBust()){
                // 結果を表示する。
                GameResultCui.showResultBust(gameManager);
                // 続けるか選ぶ。
                int input = GameStartCui.getContinue(scanner, gameManager);
                if(input == 2){
                    break;
                }
                continue;
            }
            // ディーラーのターンを実行する。
            DealersActionCui.doDealerTurns(gameManager);
            // 結果を表示する。
            GameResultCui.showResult(gameManager);
            // 続けるか選ぶ。
            int input = GameStartCui.getContinue(scanner, gameManager);
            if(input == 2){
                break;
            }
        }
        // 最終結果を表示する。
        GameResultCui.showResultEnd(gameManager);
        System.out.println("Good bye.");
    }
}
