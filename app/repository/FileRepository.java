package app.repository;

import app.domain.Account;
import java.io.*;

public class FileRepository implements Repository {
    public String line;
    public int acc_id;
    public int acc_amount;
    public String acc_holder;
    public String[] data;
    public String directory = "C:/Users/Anna/lr4_rv/";


    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }

    public void write(Account accounts) {
        try (FileWriter fw = new FileWriter(directory + accounts.getId() + ".txt")){
            fw.write(accounts.getId()+" " + accounts.getAmount() + " "+ accounts.getHolder());
            fw.flush();
        }
        catch (IOException ex){
            System.out.println("Не удалось сохранить файл" + ex.getMessage());
        }
    }

    @Override
    public void read() {
        startofwork();
            try {
                File file = new File(directory);
                File[] listofFiles = file.listFiles();
                for (int i = 0; i < listofFiles.length; i++) {
                    File infile = new File(directory + listofFiles[i].getName());
                    FileReader fr = new FileReader(infile);
                    BufferedReader reader = new BufferedReader(fr);
                    line = reader.readLine();
                    data = line.split(" ");
                    acc_id = Integer.parseInt(data[0]);
                    acc_amount = (int) Float.parseFloat(data[1]);
                    acc_holder = data[2];
                    Account user = new Account(acc_id, acc_holder, acc_amount);
                    Account.accounts.add(user);
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    public boolean isEmptyDirectory(String aDirectory){
        File directory = new File(aDirectory);
        String files[] = directory.list();
        return files.length == 0;
    }

    public boolean isDirectoryExists(String nameOfDirectory){
        File dir = new File(nameOfDirectory);
        if (dir.exists()){
            System.out.println("Директория существует");
            return true;
        }
        else return false;
    }

    public void createDirectory(String nameOfDirectory){
        File dir = new File(nameOfDirectory);
        if(isDirectoryExists(nameOfDirectory)==false){
            dir.mkdir();
            System.out.println("Директория создана");
        }
    }

    public void fillArray(){
        for (int i =1; i<11; i++){
            Account user = new Account(i, "holder" + i, 1000*i);
            Account.accounts.add(user);
            write(user);
        }
        for (int i=0;i<Account.accounts.size();i++){
            System.out.println(Account.accounts.get(i).getId()+" "+Account.accounts.get(i).getAmount()+" "+Account.accounts.get(i).getHolder());
        }
        System.out.println("Директория заполнена");
    }

    public void startofwork() {
        if (isDirectoryExists(directory)) {
            if (isEmptyDirectory(directory)) {
                fillArray();
            }
        } else {
            createDirectory(directory);
            fillArray();
        }
    }
}
