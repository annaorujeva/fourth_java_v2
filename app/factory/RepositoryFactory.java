package app.factory;

import app.repository.FileRepository;

public class RepositoryFactory {
    public static FileRepository getFileRepository(){
        return new FileRepository();
    }
}
