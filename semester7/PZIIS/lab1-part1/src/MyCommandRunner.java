import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.List;

public class MyCommandRunner {
    private static final String COMMAND_WITH_PASSWORD_FORMAT = "echo %s | sudo -S %s";
    private static final String SYSTEM_PASSWORD = "98479847";

    public static void runExecCommand(String execCommand) {
        try {
            String fullExecCommandWithPassword = String.format(COMMAND_WITH_PASSWORD_FORMAT, SYSTEM_PASSWORD,
                    execCommand);

            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", fullExecCommandWithPassword});
            process.waitFor();

            if (execCommand.contains("||")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception exception) {
            System.out.println("Cannot run '" + execCommand + "' because " + exception.getMessage());
        }
    }

    public static void runMultiExecCommand(String execCommand, List<String> properties) {
        List<Integer> pids = new ArrayList<>();

        try {
            String fullExecCommandWithPassword = String.format(COMMAND_WITH_PASSWORD_FORMAT, SYSTEM_PASSWORD,
                    execCommand);

            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", fullExecCommandWithPassword});

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Пропускаем заголовок
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length > 1) {
                    int pid = Integer.parseInt(parts[0]);
                    pids.add(pid);
                }
            }

            process.waitFor();
        } catch (Exception exception) {
            System.out.println("Cannot run '" + execCommand + "' because " + exception.getMessage());
        }
    }
}
