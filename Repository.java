package Repository;
import Domain.Entity;

import java.util.Map;

public interface Repository<T extends Entity<Id>,Id> {
    void add(T item);

    // Delete an item from the repository by its unique identifier
    void deleteById(Id id);

    void update(Id id,T item);

    T findById(Id id);

    Map<Id,T> getAll();

}