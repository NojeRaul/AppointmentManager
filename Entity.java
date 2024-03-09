package Domain;

import java.io.Serializable;

public interface Entity<Id>  {

    public Id getId();

    public void setId(Id id);

}