package Repository;

import Domain.Doctor;
import Domain.Patient;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DoctorsBinaryRepository extends FileRepository<Doctor,Integer> implements Serializable {


    public DoctorsBinaryRepository(String filename)
    {
        super(filename);
    }
    @Override
    public void readFromFile() {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
                out.writeObject(new HashMap<>());  // Initialize with an empty map
                out.close();
            } catch (IOException i) {
                throw new RuntimeException(i);
            }
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            data = (Map<Integer, Doctor>) in.readObject();
        } catch (EOFException eof) {
            // Handle EOFException if needed
            // For example, consider initializing the data with an empty map
            data = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            // Handle other exceptions
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(data);
        } catch (IOException e) {
            // Handle IOException
            throw new RuntimeException(e);
        }
    }
    @Override
    public void clear()
    {
        try {
            // Open the file in read-write mode
            RandomAccessFile file = new RandomAccessFile(filename, "rw");

            // Set the file length to 0 to truncate the content
            file.setLength(0);

            // Close the file to save the changes
            file.close();

            System.out.println("Binary file cleared successfully.");
        } catch (IOException e) {
            // Handle exceptions such as file not found or permission issues
            throw new RuntimeException("Exception occured");
        }
    }

}