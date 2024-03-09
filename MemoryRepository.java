package Repository;

import Domain.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class MemoryRepository<T extends Entity<Id>,Id> implements Repository<T,Id> {
    protected Map<Id, T> data = new HashMap<>();

    public MemoryRepository(Map<Id, T> data) {
        this.data = data;
    }

    public MemoryRepository() {
    }

    @Override
    public void add(T item) {

        data.put(item.getId(),item);
    }

    @Override
    public void deleteById(Id id) {
        data.remove(id);
    }

    @Override
    public Map<Id, T> getAll() {
        return data;
    }
    @Override
    public T findById(Id id)
    {
        for (Entry<Id,T> entry : data.entrySet()) {
            Id key = entry.getKey();
            T value = entry.getValue();
            if(key==id)
                return value;
        }
        return null;

    }
    public void update(Id id,T item)
    {
        deleteById(id);
        add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemoryRepository<?, ?> that = (MemoryRepository<?, ?>) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}