package app.repository;

import app.domain.Account;

public interface Repository<E>{
    void write(Account out) ;
    void read();
}
