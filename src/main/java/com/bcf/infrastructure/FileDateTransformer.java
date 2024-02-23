package com.bcf.infrastructure;

import com.bcf.application.DateTransformationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDateTransformer {

    public static void main(String[] args) {
        validateInput(args);

        transformFile(args[0], args[1], args[2]);
    }

    private static void validateInput(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: FileDateTransformer <file_path> <source_pattern> <destination_pattern>");
            System.exit(1);
        }
    }

    public static void transformFile(String filePath, String sourcePattern, String destinationPattern) {
        DateTransformationService service = new DateTransformationService(sourcePattern, destinationPattern);

        try {
            String content = getContent(filePath);

            String transformedContent = service.transformContent(content);

            System.out.println(transformedContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getContent(String filePath) throws IOException{
        return Files.readString(Path.of(filePath));
    }
}
