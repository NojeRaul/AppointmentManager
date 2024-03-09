package Repository;
import Domain.Appointment;

import java.io.*;
import java.util.Map;

public class AppointmentFileRepo extends FileRepository<Appointment,Integer> {

    public AppointmentFileRepo(String filename)
    {
        super(filename);
    }

    @Override
    public void readFromFile() {
        try {
            BufferedReader Reader = new BufferedReader(new FileReader(filename));
            String line = null;
            while ((line = Reader.readLine()) != null) {
                String[] tokens = line.split("[,]");

                if (tokens.length != 4) {
                    continue;
                } else {
                    Integer app_id = Integer.parseInt(tokens[0].trim());
                    String date = tokens[1].trim();
                    Integer patient_id = Integer.parseInt(tokens[2].trim());
                    Integer doctor_id = Integer.parseInt(tokens[3].trim());
                    Appointment app = new Appointment(app_id,date,patient_id,doctor_id);
                    super.add(app);
                }

            }
            Reader.close();
        }
        catch(FileNotFoundException f){
            throw new RuntimeException("File not found");

        }
        catch(IOException io){
            throw new RuntimeException("");
        }

    }

    @Override
    public void writeToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            Map<Integer,Appointment> map = this.getAll();
            Iterable<Appointment> arr = map.values();
            for(Appointment a:arr)
            {
                writer.write(a.getId() + " ,"+a.getDate()+ " ,"+ a.getPatient_id()+ " ," +a.getDoctor_id() + "\n");
            }

            writer.close();
        }
        catch (IOException io) {
            throw new RuntimeException("Exception ocurred");

        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void add(Appointment item) {
        super.add(item);
    }

    @Override
    public void update(Integer integer, Appointment item) {
        super.update(integer, item);
    }

    @Override
    public void deleteById(Integer integer) {
        super.deleteById(integer);
    }
}