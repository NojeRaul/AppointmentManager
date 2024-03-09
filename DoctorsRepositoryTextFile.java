package Repository;

import Domain.Doctor;

import java.io.*;
import java.util.Map;

public class DoctorsRepositoryTextFile extends FileRepository<Doctor,Integer> {
    public DoctorsRepositoryTextFile(String filename)
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
                    Integer doctor_id = Integer.parseInt(tokens[0].trim());
                    String name = tokens[1].trim();
                    String speciality = tokens[2].trim();
                    Double grade = Double.parseDouble(tokens[3].trim());
                    Doctor d = new Doctor(doctor_id,name,speciality,grade);
                    super.add(d);

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
            Map<Integer,Doctor> map = this.getAll();
            Iterable<Doctor> arr = map.values();
            for(Doctor d:arr)
            {
                writer.write(d.getId() + " ,"+ d.getName()+ " ,"+ d.getSpeciality()+ " ," +d.getGrade() + "\n");
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