package dice;

public class Score {
    private int win = 0;
    private int lose = 0;
    private int draw = 0;
    private int money = 10000;

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public int getDraw() {
        return draw;
    }

    public void addWin() {
        win++;
    }

    public void addLose() {
        lose++;
    }

    public void addDraw() {
        draw++;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int bet) {
        this.money += bet;
    }

    public void minMoney(int bet) {
        this.money -= bet;
    }
}
