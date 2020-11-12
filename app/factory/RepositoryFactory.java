package app.factory;

import app.repository.FileRepository;

public class RepositoryFactory {
    public static Repository getFileRepository(){
        return new FileRepository();
    }
}
