import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by samu on 25/04/17.
 */
public class ParserCSV {

    void parser(String filePath) {
        List<String []> file;
        StringBuilder sb;

        try{
            file = readFile(filePath);
        }catch(IOException ioe) {
            System.out.println("No se ha podido leer el fichero");
            return;
        }

        sb = transformFile(file);

        try {
            writeFile(sb);
        }catch(IOException ioe) {
            System.out.println("No se ha podido escribir el fichero");
            return;
        }

        System.out.println("CONSEGUIDO");
    }

    /**
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    List<String []> readFile(String filePath) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        List<String []> list;

        list = null;

        try {
            String line = br.readLine();
            list = new ArrayList<String []>();

            while (line != null) {
                String [] token = line.split(",");
                list.add(token);
                line = br.readLine();
            }
        } finally {
            br.close();
        }

        return list;
    }


    /**
     *
     * @return
     */
    StringBuilder transformFile(List<String []> file) {
        Iterator<String []> it;
        StringBuilder sb;

        sb = new StringBuilder();

        it = file.iterator();
        while(it.hasNext()){
            String [] line = it.next();
            sb.append(line[0]).append(",").append(getScore(line[1], line[2])).append("\n");
        }

        return sb;
    }


    private int getScore(String system, String scoreHack) {
        byte[] sco = scoreHack.getBytes();
        int score = 0;

        for(int i = 0 ; i < sco.length ; i++) {
            score += system.indexOf(sco[sco.length - 1 - i]) * Math.pow(system.length(), i);
        }

        System.out.println(score);

        return score;
    }


    private void writeFile(StringBuilder sb) throws IOException{
        FileOutputStream out = new FileOutputStream("the-file-name.csv");
        out.write(sb.toString().getBytes());
        out.close();
    }




    /**
     *
     * @param args
     */
    public static void main (String [] args) {
        ParserCSV parser = new ParserCSV();

        parser.parser(args[0]);
    }

}
