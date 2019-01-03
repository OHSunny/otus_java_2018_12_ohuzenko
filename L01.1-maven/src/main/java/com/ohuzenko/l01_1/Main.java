package com.ohuzenko.l01_1;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

            System.out.println(new Main().concatFiles(args));
    }


    private StringBuilder concatFiles(String[] fileNames) throws IOException {
       StringBuilder sb = new StringBuilder();

        for(String name : fileNames){
            List<String> lines = Files.readLines(new File(name), Charsets.UTF_8);

            for (String line: lines) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        }

        return sb;
    }


}
