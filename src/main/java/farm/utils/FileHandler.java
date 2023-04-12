package farm.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.SimpleFormatter;

class FileHandler {

    void log (String message, String className) {
        java.util.logging.Logger lg = java.util.logging.Logger.getLogger(className);
        try{
            java.util.logging.FileHandler fh = new java.util.logging.FileHandler("target/buglogs/buglog");
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            lg.addHandler(fh);
            lg.severe(message);
        }
        catch(SecurityException | IOException e){
            e.printStackTrace();
        }
    }

    ArrayList<String[]> load (String filename) {
        ArrayList<String[]> result = new ArrayList<>();
        try (BufferedReader loadIn = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename)))) {
            String s;
            while ((s = loadIn.readLine()) != null) {
                result.add(s.split(";"));
            }
        } catch (Exception e) {
            System.out.println("Internal error. We`re fixing it");
            this.log(e.getMessage(), this.getClass().getName());
            System.exit(1);
        }
        return result;
    }

}
