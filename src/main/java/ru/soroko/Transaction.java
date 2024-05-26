package ru.soroko;

public class Transaction implements Runnable {
    private Account src;
    private Account dst;
    private long money;
    private AccountDao accountDao;

    public Transaction(Account src, Account dst, long money, AccountDao accountDao) {
        this.src = src;
        this.dst = dst;
        this.money = money;
        this.accountDao = accountDao;
    }

    public void addMoney() {
        if (src.getBalance() >= money) {
            src.setBalance(src.getBalance() - money);
            dst.setBalance(dst.getBalance() + money);
            accountDao.update(src);
            accountDao.update(dst);
        }
    }

    @Override
    public void run() {
        addMoney();
    }
}
