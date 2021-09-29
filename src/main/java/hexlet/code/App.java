package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.3",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Option(names = {"-f", "--format"}, paramLabel = "format",
            defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}]")
    private String format = "stylish";

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filePath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filePath2;

    @Override
    public final void run() {
        try {
            String file1 = Files.readString(Paths.get(filePath1));
            String file2 = Files.readString(Paths.get(filePath2));

            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Object> map1 = objectMapper.readValue(file1, new TypeReference<>() { });
            Map<String, Object> map2 = objectMapper.readValue(file2, new TypeReference<>() { });

            System.out.println(Differ.generate(map1, map2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
