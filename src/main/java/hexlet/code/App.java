package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Map;


@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.3",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Option(names = {"-f", "--format"}, paramLabel = "format",
            defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}]")
    private final String format = "stylish";

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filePath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filePath2;

    @Override
    public final void run() {
        try {

            Map<String, Object> parsedFile1 = Parser.parse(filePath1);
            Map<String, Object> parsedFile2 = Parser.parse(filePath2);

            System.out.println(Differ.generate(parsedFile1, parsedFile2, format));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
