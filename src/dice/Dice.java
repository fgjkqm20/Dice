package dice;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Dice {
    public void run() {
        int select;
        Score score = new Score();

        while (true) {
            select = showMenu();

            switch (select) {
                case 1:
                    if (score.getMoney() > 0) {
                        start(score);
                    } else {
                        System.out.println("잔액이 없습니다. bye~");
                        System.out.println();
                    }
                    break;
                case 2:
                    showScore(score);
                    break;
                case 3:
                    break;
            }

            if (select == 3)
                break;
        }
    }

    private int showMenu() {
        Scanner sc = new Scanner(System.in);

        int select;

        System.out.println("===== Dice Game =====");
        System.out.println("1. Game Start");
        System.out.println("2. Game Score");
        System.out.println("3. End Game");

        while (true) {
            System.out.print("선택 > ");

            try {
                select = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println();
                System.out.println("잘못입력하셨습니다.");
                System.out.println();
                continue;
            }

            if (select == 1 || select == 2 || select == 3) {
                break;
            } else {
                System.out.println();
                System.out.println("잘못 입력하셨습니다.");
                System.out.println();
            }
        }
        System.out.println();

        return select;
    }

    private void start(Score score) {
        int[] me = new int[3];
        int[] cpu = new int[3];

        int meSum = 0;
        int cpuSum = 0;

        String select;
        int bet;

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        while (true) {
            System.out.println("<< Game Start  >>");

            for (int i = 0; i < me.length; i++) {
                me[i] = r.nextInt(6) + 1;
                System.out.println(i + 1 + "번째 주사의 값 : " + me[i]);
                meSum += me[i];
            }
            System.out.println("내 주사위 총 합 : " + meSum);

            while (true) {
                System.out.println();
                System.out.print("베팅 하시겠습니까?[y/n] : ");
                select = sc.nextLine();

                if (select.equals("y") || select.equals("n")) {
                    break;
                } else {
                    System.out.println();
                    System.out.println("다시 입력주세요.");
                }
            }

            if (select.equals("y")) {
                while (true) {
                    System.out.println();
                    System.out.print("얼마를 베팅하시겠습니까?(현재 내 소지금 " + score.getMoney() + "원) : ");

                    try {
                        bet = sc.nextInt();
                    } catch (InputMismatchException e) {
                        sc.nextLine();
                        System.out.println();
                        System.out.println("잘못입력하셨습니다.");
                        System.out.println();
                        continue;
                    }

                    if (bet <= score.getMoney()) {
                        break;
                    } else {
                        System.out.println();
                        System.out.println("금액이 부족합니다. 다시 입력해주세요.");
                    }
                }

                System.out.println();
                for (int i = 0; i < cpu.length; i++) {
                    cpu[i] = r.nextInt(6) + 1;
                    System.out.println("컴퓨터 " + i + 1 + "번째 주사의 값 : " + cpu[i]);
                    cpuSum += cpu[i];
                }
                System.out.println("컴퓨터 주사위 총 합 : " + cpuSum);

                System.out.println();
                System.out.println("<< 결과 >>");
                if (meSum > cpuSum) {
                    score.addWin();
                    score.addMoney(bet);
                    System.out.println("승리!!!!!");
                    System.out.println("+" + bet + "원!!!");
                } else if (meSum < cpuSum) {
                    score.addLose();
                    score.minMoney(bet);
                    System.out.println("패배!!!!!");
                    System.out.println("-" + bet + "원!!!");
                } else {
                    score.addDraw();
                    System.out.println("무승부!!!!!");
                }

                if (score.getMoney() == 0) {
                    System.out.println("잔액이 없습니다. bye~");
                    System.out.println();
                    break;
                }

                while (true) {
                    System.out.println();
                    sc.nextLine();
                    System.out.print("한 번 더 하시겠습니까?[y/n] : ");
                    select = sc.nextLine();

                    if (select.equals("y") || select.equals("n")) {
                        break;
                    } else {
                        System.out.println();
                        System.out.println("다시 입력주세요.");
                        sc.nextLine();
                    }
                }
                if (select.equals("y")) {
                    System.out.println();
                    continue;
                } else {
                    System.out.println();
                    break;
                }
            } else {
                System.out.println();
                System.out.println("메뉴로 돌아갑니다.");
                System.out.println();
            }
        }
    }

    private void showScore(Score score) {
        System.out.println("<< 당신의 전적 >>");
        System.out.println("WIN : " + score.getWin());
        System.out.println("LOSE : " + score.getLose());
        System.out.println("DRAW : " + score.getDraw());
        System.out.println("내 소지금 : " + score.getMoney());
        System.out.println();
    }
}
