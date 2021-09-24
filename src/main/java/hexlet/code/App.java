package hexlet.code;

import picocli.CommandLine;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.3",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable{

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
