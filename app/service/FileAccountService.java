package app.service;
import app.domain.Account;
import app.exception.UnknownAccountException;
import app.exception.NotEnoughtMoneyException;
import app.factory.RepositoryFactory;
import app.repository.Repository;

public class FileAccountService implements AccountService {
    Repository fr = RepositoryFactory.getFileRepository();

    @Override //снять указанную сумму
    public void withdraw(int accountId, int amount) throws NotEnoughtMoneyException, UnknownAccountException {
        boolean findId = false;
        int foundId = 0;
        for (int i = 0; i < Account.accounts.size(); i++) {
            if (Account.accounts.get(i).getId() == accountId) {
                findId = true;
                foundId = i;
            }
        }
        if (findId) {
            if (Account.accounts.get(foundId).getAmount() >= amount) {
                Account.accounts.get(foundId).setAmount((int) (Account.accounts.get(foundId).getAmount() - amount));
                fr.write(Account.accounts.get(foundId));
            } else throw new NotEnoughtMoneyException();
        } else throw new UnknownAccountException();
    }

    @Override //вывести баланс
    public void balance(int accountId) throws UnknownAccountException {
        boolean findId = false;
        int foundId = 0;
        for (int i = 0; i < Account.accounts.size(); i++) {
            if (Account.accounts.get(i).getId() == accountId) {
                findId = true;
                foundId = i;
            }
        }
        if (findId) {
            System.out.println(Account.accounts.get(foundId).getAmount());
        }
        else throw new UnknownAccountException();
    }

    @Override //внести сумму на указанный счет
    public void deposit(int accountId, int amount) throws UnknownAccountException {
        boolean findId = false;
        int foundId = 0;
        for (int i = 0; i < Account.accounts.size(); i++) {
            if (Account.accounts.get(i).getId() == accountId) {
                findId = true;
                foundId = i;
            }
        }
        if (findId) {
            Account.accounts.get(foundId).setAmount((int) (Account.accounts.get(foundId).getAmount() + amount));
            fr.write(Account.accounts.get(foundId));
        } else throw new UnknownAccountException();
    }

    @Override //перевести сумму с одного на другой
    public void transfer(int accountFrom, int accountTo, int amount) throws NotEnoughtMoneyException, UnknownAccountException {
        boolean findIdFrom = false;
        boolean findIdTo = false;
        int foundIdFrom = 0;
        int foundIdTo = 0;
        for (int i = 0; i < Account.accounts.size(); i++) {
            if (Account.accounts.get(i).getId() == accountFrom) {
                findIdFrom = true;
                foundIdFrom = i;
            }
            if (Account.accounts.get(i).getId() == accountTo) {
                findIdTo = true;
                foundIdTo = i;
            }
        }
        if (findIdFrom & findIdTo) {
            if (Account.accounts.get(foundIdFrom).getAmount() >= amount) {
                Account.accounts.get(foundIdTo).setAmount((int) (Account.accounts.get(foundIdTo).getAmount() + amount));
                Account.accounts.get(foundIdFrom).setAmount((int) (Account.accounts.get(foundIdFrom).getAmount() - amount));
                fr.write(Account.accounts.get(foundIdTo));
                fr.write(Account.accounts.get(foundIdFrom));
            }
            else throw new NotEnoughtMoneyException();
        }
        else throw new UnknownAccountException();
    }
}
