package org.example.toylanguage;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

@Slf4j
public class RunToyLanguage {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        while (true) {
            System.out.print("File to parse: ");
            var input = scanner.nextLine();

            Path sourceFilePath = FileSystems.getDefault().getPath(input);
            if (Files.exists(sourceFilePath)) new ToyLanguage().execute(sourceFilePath);
            else log.info("Could not resolve {} as a valid file.", sourceFilePath);
        }
    }
}
