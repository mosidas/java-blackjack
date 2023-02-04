package UI;
import java.util.Scanner;

import gameObject.Game;
import gameObject.GameSettings;

/**
 * ブラックジャックのCUIクラス
 */
public class BlackjackCui {
    private Game game;

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
        game = new Game(gs);
        // ゲーム終了するまでループ
        while(true) {
            // 掛け金を決める。
            PlayersActionCui.setPlayersBet(scanner, game);
            // 最初の手札を配る。
            DealersActionCui.dealInitialHand(game);
            // 各プレイヤーのターンを実行する。
            PlayersActionCui.doPlayersTurn(scanner, game);
            // 全員バーストしてたら終了する。
            if(game.allPlayerHandsIsBust()){
                // 結果を表示する。
                GameResultCui.showResultBust(game);
                if(game.allPlayerIsGameOver()){
                    break;
                }
                // 続けるか選ぶ。
                int input = GameStartCui.getContinue(scanner, game);
                if(input == 2){
                    break;
                }
                continue;
            }
            // ディーラーのターンを実行する。
            DealersActionCui.doDealerTurns(game);
            // 結果を表示する。
            GameResultCui.showResult(game);
            // 続けるか選ぶ。
            int input = GameStartCui.getContinue(scanner, game);
            if(input == 2){
                break;
            }
        }
        // 最終結果を表示する。
        GameResultCui.showResultEnd(game);
        System.out.println("Good bye.");
    }
}
