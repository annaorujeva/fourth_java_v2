package app;
import app.exception.NotEnoughtMoneyException;
import app.exception.UnknownAccountException;
import app.service.*;

public class Main {
    public static void main(String[] args) throws NotEnoughtMoneyException, UnknownAccountException {
        Menu mn = new Menu();
        for (;;){
            mn.start();
            if (mn.flagexit) break;
        }
    }
}
