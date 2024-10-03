import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class MyFileManager {
    public static final String FILE_NAME = "%s/file%d%d.sh";
    public static final int NUMBER_OF_FILES_IN_DIRECTORY = 5;
    public static final String FILES_X5_TEMPLATE = "read testVariable";
    public static final String OTHER_FILES_TEMPLATE = "echo \"Hello World\"";

    public static List<Path> fileNames;

    static {
        fileNames = new ArrayList<>();
    }

    public static void createFileNames(List<Path> directoryNames) {
        for (int i = 1; i <= NUMBER_OF_FILES_IN_DIRECTORY; i++) {
            for (int j = 1; j <= NUMBER_OF_FILES_IN_DIRECTORY; j++) {
                fileNames.add(Paths.get(String.format(FILE_NAME, directoryNames.get(i), i, j)));
            }
        }
    }

    public static void createFilesWithDifferentAccessInSystem() {
        String currentFileName = "default";

        System.out.println("Starting to create files...");

        try {
            for (int i = 0; i < fileNames.size(); i++) {
                currentFileName = String.valueOf(fileNames.get(i));

                System.out.print("Create file with name " + fileNames.get(i) + " and with roots: ");
                if (currentFileName.contains("5")) {
                    Files.write(fileNames.get(i), FILES_X5_TEMPLATE.getBytes());
                } else {
                    Files.write(fileNames.get(i), OTHER_FILES_TEMPLATE.getBytes());
                }

                if (i == 0) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_READ));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_READ));
                }

                if (i == 1) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE));
                }

                if (i == 2) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_WRITE));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_WRITE));
                }

                if (i == 3) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_WRITE,
                            PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_EXECUTE));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_WRITE,
                            PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_EXECUTE));
                }

                if (i == 4) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_EXECUTE));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_EXECUTE));
                }

                if (i == 5) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.GROUP_READ));

                    System.out.println(EnumSet.of(PosixFilePermission.GROUP_READ));
                }

                if (i == 6) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.GROUP_READ,
                            PosixFilePermission.GROUP_WRITE));

                    System.out.println(EnumSet.of(PosixFilePermission.GROUP_READ,
                            PosixFilePermission.GROUP_WRITE));
                }

                if (i == 7) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.GROUP_WRITE));

                    System.out.println(EnumSet.of(PosixFilePermission.GROUP_WRITE));
                }

                if (i == 8) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.GROUP_READ,
                            PosixFilePermission.GROUP_WRITE, PosixFilePermission.GROUP_EXECUTE));

                    System.out.println(EnumSet.of(PosixFilePermission.GROUP_READ,
                            PosixFilePermission.GROUP_WRITE, PosixFilePermission.GROUP_EXECUTE));
                }

                if (i == 9) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.GROUP_EXECUTE));

                    System.out.println(EnumSet.of(PosixFilePermission.GROUP_EXECUTE));
                }

                if (i == 10) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OTHERS_READ));

                    System.out.println(EnumSet.of(PosixFilePermission.OTHERS_READ));
                }

                if (i == 11) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_WRITE));

                    System.out.println(EnumSet.of(PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_WRITE));
                }

                if (i == 12) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OTHERS_WRITE));

                    System.out.println(EnumSet.of(PosixFilePermission.OTHERS_WRITE));
                }

                if (i == 13) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE));

                    System.out.println(EnumSet.of(PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE));
                }

                if (i == 14) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OTHERS_EXECUTE));

                    System.out.println(EnumSet.of(PosixFilePermission.OTHERS_EXECUTE));
                }

                if (i == 15) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_READ));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_READ));
                }

                if (i == 16) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OWNER_WRITE, PosixFilePermission.GROUP_WRITE,
                            PosixFilePermission.OTHERS_WRITE));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OWNER_WRITE, PosixFilePermission.GROUP_WRITE,
                            PosixFilePermission.OTHERS_WRITE));
                }

                if (i == 17) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_WRITE,
                            PosixFilePermission.GROUP_WRITE, PosixFilePermission.OTHERS_WRITE));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_WRITE,
                            PosixFilePermission.GROUP_WRITE, PosixFilePermission.OTHERS_WRITE));
                }

                if (i == 18) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE,
                            PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE,
                            PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE,
                            PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE,
                            PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE));
                }

                if (i == 19) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_EXECUTE,
                            PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.OTHERS_EXECUTE));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_EXECUTE,
                            PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.OTHERS_EXECUTE));
                }

                if (i == 20) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_READ));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_READ));
                }

                if (i == 21) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE));
                }

                if (i == 22) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_WRITE));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_WRITE));
                }

                if (i == 23) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_WRITE,
                            PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_EXECUTE));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_WRITE,
                            PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_EXECUTE));
                }

                if (i == 24) {
                    Files.setPosixFilePermissions(fileNames.get(i), EnumSet.of(PosixFilePermission.OWNER_EXECUTE));

                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_EXECUTE));
                }
            }
        } catch (Exception exception) {
            System.out.println("Cannot create directory name '" + currentFileName + "' because " +
                    exception.getMessage());
        }
    }
}