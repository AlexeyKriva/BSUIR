import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class MyTests {

    public static final String RUN_FILE_BY_USER_COMMAND = "-u %s bash %s";

    public static final String CHECK_USER_ACCESS_TO_FILE_COMMAND = "-u %s test -%s %s && echo \"%s has %s access to %s\" || " +
            "echo \"%s has not %s access to %s\"";
    public static final String[] accesses = {"r", "w", "x"};
    private static final String[] hass = {"has", "not has"};

    public static void checkUserAccessToFile(List<String> userNames, List<Path> fileNames) {
        userNames.add("aliaksei");
        for (Path fileName: fileNames) {
            for (String userName: userNames) {
                for (String access: accesses) {
                    int index = new Random().nextInt(2);
                    MyCommandRunner.runExecCommand(String.format(CHECK_USER_ACCESS_TO_FILE_COMMAND,
                            userName, access, fileName, userName, access, fileName, userName,
                            access, fileName));
                }
            }
        }
    }

    public static void checkUserAccessToDirectories(List<String> userNames, List<Path> directoriesNames) {
        userNames.add("aliaksei");
        for (Path directories: directoriesNames) {
            for (String userName: userNames) {
                for (String access: accesses) {
                    int index = new Random().nextInt(2);
                    MyCommandRunner.runExecCommand(String.format(CHECK_USER_ACCESS_TO_FILE_COMMAND,
                            userName, access, directories, userName, access, directories, userName,
                            access, directories));
                }
            }
        }
    }
}
