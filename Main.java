package gb2.lesson5.lesson5dz;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        final String PATH = "./src/file.csv";

        AppData appData = new AppData();

        File file = appData.newFile(PATH);

        int [][] data = {{100, 200, 300},{400, 500, 600},{700, 800, 900}};

        appData.write(PATH, data);

        System.out.println("Начальные значения в файле");

        appData.read(PATH, appData);

        int [][] newData = {{111, 222, 333},{444, 555, 666},{777, 888, 999}};

        appData.save(PATH, newData);

        System.out.println("Новые значения в файле");

        appData.read(PATH, appData);

        appData.delete(file);
    }
}
