package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ゲーム開始のCUI
 */
public class GameStartCui {
    /**
     * 開始時の入力値を取得する
     * @param scanner
     * @return
     */
    public static int getContinue(Scanner scanner){
        System.out.println("続けますか?");
        while(true){
            try{
                System.out.print("続ける:1 終わる:2 →   ");
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
    
}
