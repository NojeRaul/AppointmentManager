package Repository;

import Domain.Entity;

public abstract class FileRepository<T extends Entity<Id>,Id> extends MemoryRepository<T,Id> {

    protected String filename;

    public FileRepository(String filename)
    {
        this.filename=filename;
        readFromFile();
    }

    public abstract void readFromFile();
    public abstract void writeToFile();
    public abstract void clear();
    @Override
    public void add(T item) {
        super.add(item);
        writeToFile();
    }

    @Override
    public void update(Id id,T item) {
        super.update(id, item);
        writeToFile();
    }

    @Override
    public void deleteById(Id id) {
        super.deleteById(id);
        writeToFile();
    }
}