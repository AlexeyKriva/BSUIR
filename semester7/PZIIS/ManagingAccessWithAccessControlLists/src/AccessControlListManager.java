import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.nio.file.attribute.PosixFilePermissions;


public class AccessControlListManager {
    public static void main(String[] args) throws Exception {
        execution();
    }

    public static void execution() throws Exception {
        // 1. Создать группы пользователей group_iit1 и group_iit2
        runCommand("sudo dseditgroup -o create group_iit1");
        System.out.println("Создана группа group_iit1");
        runCommand("sudo dseditgroup -o create group_iit2");
        System.out.println("Создана группа group_iit2");

        // 2. Создать пользователей iit11, iit12 и добавить их в группу group_iit1
        runCommand("sudo sysadminctl -addUser iit11 -fullName \"iit11\" -password \"1234\"\n");
        System.out.println("Создан пользователь iit11");
        runCommand("sudo sysadminctl -addUser iit12 -fullName \"iit12\" -password \"1234\"\n");
        System.out.println("Создан пользователь iit12");
        runCommand("sudo dseditgroup -o edit -a iit11 -t user group_iit1\n");
        runCommand("sudo dseditgroup -o edit -a iit12 -t user group_iit1\n");

        // 3. Создать пользователей iit21, iit22 и добавить их в группу group_iit2
        runCommand("sudo dscl . -create /Users/iit21");
        runCommand("sudo dscl . -create /Users/iit22");
        runCommand("sudo dscl . -append /Groups/group_iit2 GroupMembership iit21");
        runCommand("sudo dscl . -append /Groups/group_iit2 GroupMembership iit22");

        // 4. Предоставить пользователю iit21 административные привилегии
        runCommand("sudo dscl . -append /Groups/admin GroupMembership iit21");

        // 5. Создать пользователя iit3
        runCommand("sudo dscl . -create /Users/iit3");

        //////////////////////////////

        // 6. Создать папку pzs
        runCommand("mkdir pzs");

        // 7. Создать папку pzs11 в папке pzs с правами только для владельца
        runCommand("mkdir pzs/pzs11");
        runCommand("chmod 700 pzs/pzs11");
        runCommand("sudo chown iit11 pzs/pzs11"); // Назначаем владельца iit11

        // 8. Создать папку pzs12 в папке pzs с правами только для группы
        runCommand("mkdir pzs/pzs12");
        runCommand("chmod 070 pzs/pzs12");
        runCommand("sudo chown :group_iit1 pzs/pzs12"); // Назначаем группу group_iit1

        // 9. Создать папку pzs13 в папке pzs с правами только для остальных пользователей
        runCommand("mkdir pzs/pzs13");
        runCommand("chmod 007 pzs/pzs13");

        // 10. Создать папку pzs14 в папке pzs с правами для всех пользователей
        runCommand("mkdir pzs/pzs14");
        runCommand("chmod 777 pzs/pzs14");

        // 11. Создать папку pzs15 в папке pzs с правами только для администратора (root)
        runCommand("sudo mkdir pzs/pzs15");
        runCommand("sudo chmod 700 pzs/pzs15");
        runCommand("sudo chown root pzs/pzs15"); // Назначаем владельца root

        // 12. Сменить текущего пользователя на iit11
        runCommand("su - iit11");

        ////////////////////////////////////////
    }

    public static void createDirectory(Path path, Set<PosixFilePermission> permissions) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectory(path);
            Files.setPosixFilePermissions(path, permissions);
            System.out.println("Папка создана: " + path.toAbsolutePath());
        } else {
            System.out.println("Папка уже существует: " + path.toAbsolutePath());
        }
    }

    public static void createFile(Path path, Set<PosixFilePermission> permissions, String content) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.write(path, content.getBytes());
            Files.setPosixFilePermissions(path, permissions);
            System.out.println("Файл создан: " + path.toAbsolutePath());
        } else {
            System.out.println("Файл уже существует: " + path.toAbsolutePath());
        }
    }

    public static void runCommand(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        process.waitFor();
    }

    public static void createFileWithPermissionsAndContent(String filePath, String permissions, String content) throws Exception {
        runCommand("touch " + filePath);  // Создать файл
        runCommand("chmod " + permissions + " " + filePath);  // Установить права доступа
        runCommand("echo \"" + content + "\" > " + filePath);  // Записать содержимое в файл
    }

    // Метод для выполнения системной команды
    private static String executeCommand(String command) throws Exception {
        Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        process.waitFor();
        return output.toString();
    }

    // Метод для проверки прав доступа к файлу для конкретного пользователя
    private static void checkPermissionsForUser(String user, String filePath) {
        try {
            System.out.println("Проверка прав доступа для пользователя: " + user + " к файлу: " + filePath);

            // Проверка на чтение
            String readCommand = "su -c 'cat " + filePath + "' " + user;
            String readResult = executeCommand(readCommand);
            if (readResult.isEmpty()) {
                System.out.println("Чтение: запрещено");
            } else {
                System.out.println("Чтение: разрешено");
            }

            // Проверка на запись
            String writeCommand = "su -c 'echo \"test\" >> " + filePath + "' " + user;
            String writeResult = executeCommand(writeCommand);
            if (writeResult.isEmpty()) {
                System.out.println("Запись: запрещено");
            } else {
                System.out.println("Запись: разрешена");
            }

            // Проверка на выполнение
            String execCommand = "su -c './" + filePath + "' " + user;
            String execResult = executeCommand(execCommand);
            if (execResult.isEmpty()) {
                System.out.println("Выполнение: запрещено");
            } else {
                System.out.println("Выполнение: разрешено");
            }

            System.out.println("-----------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
