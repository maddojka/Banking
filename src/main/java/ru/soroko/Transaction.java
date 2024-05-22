package ru.soroko;

public class Transaction implements Runnable {
    private Account src;
    private Account dst;
    private long money;

    public Transaction(Account src, Account dst, long money) {
        this.src = src;
        this.dst = dst;
        this.money = money;
    }

    public void addMoney() {
        if (src.getBalance() >= money)
            src.setBalance(src.getBalance() - money);
        dst.setBalance(dst.getBalance() + money);
    }

    @Override
    public void run() {
        addMoney();
    }
}
