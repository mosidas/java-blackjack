package UI;

import java.util.Scanner;

import gameObject.GameSettings;

/** 
 * ゲーム設定のCUI
*/
public class GameSettingCui {

    public static GameSettings getGameSettings(Scanner scanner){
        // プレイヤー人数設定
        int playerCount = GameSettingCui.setPlayersNumber(scanner);
         // デッキ数設定
        int deckCount = GameSettingCui.setDeckNumber(scanner);
        // ソフト17ルール設定
        boolean soft17 = GameSettingCui.setHitSoft17(scanner);
        // 所持金設定
        int defaultMoney = GameSettingCui.setDefaultMoney(scanner);
        
        return new GameSettings(playerCount, deckCount, soft17, defaultMoney);
    }


    private static int setPlayersNumber(Scanner scanner){
        while(true){
            try{
                System.out.print("プレイヤー人数:1～8 →   ");
                int input = scanner.nextInt();
                if(1 <= input && input <= 8){
                    return input;
                }
                System.out.println("1～8の整数値を入力してください");
            }
            catch(Exception e){
                System.out.println("1～8の整数値を入力してください");
                scanner.next();
            }
        }
    }

    private static int setDeckNumber(Scanner scanner){
        while(true){
            try{
                System.out.print("デッキ数:1～8 →   ");
                int input = scanner.nextInt();
                if(1 <= input && input <= 8){
                    return input;
                }
                System.out.println("1～8の整数値を入力してください");
            }
            catch(Exception e){
                System.out.println("1～8の整数値を入力してください");
                scanner.next();
            }
        }

    }

    private static boolean setHitSoft17(Scanner scanner){
        while(true){
            try{
                System.out.print("ソフト17(1:ヒットする  2:スタンドする):→   ");
                int input = scanner.nextInt();
                if(input == 1){
                    return true;
                }
                else if(input == 2){
                    return false;
                }
                System.out.println("1か2を入力してください");
            }
            catch(Exception e){
                System.out.println("1か2を入力してください");
                scanner.next();
            }
        }
    }

    private static int setDefaultMoney(Scanner scanner){
        while(true){
            try{
                System.out.print("最初の所持金(10～100000):→   ");
                int input = scanner.nextInt();
                if(10 <= input && input <= 100000){
                    return input;
                }
                System.out.println("10～100000の整数値を入力してください");
            }
            catch(Exception e){
                System.out.println("10～100000の整数値を入力してください");
                scanner.next();
            }
        }
    }
}
