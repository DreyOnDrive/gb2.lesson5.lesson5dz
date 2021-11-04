package gb2.lesson5.lesson5dz;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class AppData {

    private String[] header;
    private int[][] data;

    public AppData() {
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    public void printInfo() {
        System.out.println("В объект AppData записалось следующее");
        System.out.println(Arrays.toString(header));
        for (int[] ints : data) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public File newFile(String PATH) throws IOException {
        File file = new File(PATH);
        file.createNewFile();
        return file;
    }

    public void write(String PATH, int[][] data) throws IOException {
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PATH), StandardCharsets.UTF_8))) {
            out.write("Value1;Value2;Value3");
            out.newLine();
            for (int[] datum : data) {
                String buffer = null;
                for (int i : datum) {
                    String s = String.valueOf(i);
                    if (buffer == null){
                        buffer = s;
                    }else {
                        buffer = buffer + ";" + s;
                    }
                }
                out.write(buffer);
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(String PATH, AppData appData) {

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(PATH), StandardCharsets.UTF_8))) {
            appData.setHeader(in.readLine().split(";"));
            String result;
            int i = 0;
            int[][] array = new int[stringCount(PATH)- 1][];
            while ((result = in.readLine()) != null) {
                String[] tokens = result.split(";");
                array[i] = parserToInt(tokens);
                i++;
            }
            appData.setData(array);
            appData.printInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String PATH, int[][] newData) {
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PATH), StandardCharsets.UTF_8))) {
            out.write("Value1;Value2;Value3");
            out.newLine();
            for (int[] datum : newData) {
                String buffer = null;
                for (int i : datum) {
                    String s = String.valueOf(i);
                    if (buffer == null){
                        buffer = s;
                    }else {
                        buffer = buffer + ";" + s;
                    }
                }
                out.write(buffer);
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int stringCount(String PATH){
        int count = 0;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(PATH), StandardCharsets.UTF_8))) {
            String result;
            while ((result = in.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private static int[] parserToInt(String[] tokens){
        int[] tokensInt = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            tokensInt[i] = Integer.parseInt(tokens[i]);
        }
        return tokensInt;
    }

    public void delete( File file) throws InterruptedException {
        sleep(15000);
        file.deleteOnExit();
    }
}
