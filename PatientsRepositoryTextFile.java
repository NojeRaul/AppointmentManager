package Repository;
import Domain.Patient;

import java.io.*;
import java.util.Map;

public class PatientsRepositoryTextFile extends FileRepository<Patient,Integer> {


    public PatientsRepositoryTextFile(String filename)
    {
        super(filename);
    }
    @Override
    public void readFromFile()
    {
        try {
            BufferedReader Reader = new BufferedReader(new FileReader(filename));
            String line=null;
            while( (line=Reader.readLine())!=null)
            {
                String[] tokens = line.split("[,]");

                if(tokens.length!=4)
                {

                    continue;
                }
                else {
                    Integer patient_id = Integer.parseInt(tokens[0].trim());
                    String name_patient = tokens[1].trim();
                    String email = tokens[2].trim();
                    String disease = tokens[3].trim();
                    Patient p = new Patient(patient_id,name_patient,email,disease);
                    super.add(p);

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
            Map<Integer,Patient> map = this.getAll();
            Iterable<Patient> arr = map.values();
            for(Patient p:arr)
            {
                writer.write(p.getId() + " ,"+ p.getName()+ " ,"+ p.getEmail()+ " ," +p.getDisease()+"\n");
            }
            writer.close();
        }
        catch (IOException io) {
            throw new RuntimeException("Exception ocurred");

        }
    }

    @Override
    public void clear(){
        try {
            // Open the file in write mode, which clears the content
            FileWriter fileWriter = new FileWriter(filename);

            // Close the file writer to save the changes
            fileWriter.close();

            //System.out.println("Text file cleared successfully.");
        } catch (IOException e) {
            // Handle exceptions such as file not found or permission issues
            throw new RuntimeException("Exception ocurred");
        }
    }
}