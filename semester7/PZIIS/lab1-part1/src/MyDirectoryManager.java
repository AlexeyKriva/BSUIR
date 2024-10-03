import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class MyDirectoryManager {
    public static final String PZS = "pzs1";
    public static final int NUMBER_OF_DIRECTORY = 5;


    public static List<Path> directoryNames;

    static {
        directoryNames = new ArrayList<>();
    }

    public static void createDirectoryNames() {
        directoryNames.add(Paths.get(PZS.replaceAll("1", "")));

        for (int i = 1; i <= NUMBER_OF_DIRECTORY; i++) {
            directoryNames.add(Paths.get(PZS.replaceAll("1", "") + "/" + PZS + i));
        }
    }

    public static void createDirectoriesInSystem() {
        String currentDirectoryName = "default";

        try {
            System.out.println("Starting to create directories...");

            String directoryName = String.valueOf(directoryNames.getFirst());

            System.out.println("Create directory with name " + directoryName);

            currentDirectoryName = directoryName;
            Files.createDirectory(Paths.get(directoryName));

            for (int i = 1; i < directoryNames.size(); i++) {
                directoryName = String.valueOf(directoryNames.get(i));
                currentDirectoryName = directoryName;

                System.out.print("Create directory with name " + directoryName);

                Files.createDirectory(Paths.get(directoryName));

                System.out.print(" and with roots: ");
                if (i % NUMBER_OF_DIRECTORY == 1) {
                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE));

                    Files.setPosixFilePermissions(Path.of(directoryName), EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE));
                }

                if (i % NUMBER_OF_DIRECTORY == 2) {
                    System.out.println(EnumSet.of(PosixFilePermission.GROUP_READ,
                            PosixFilePermission.GROUP_WRITE, PosixFilePermission.GROUP_EXECUTE));

                    Files.setPosixFilePermissions(Path.of(directoryName),
                            EnumSet.of(PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE,
                                    PosixFilePermission.GROUP_EXECUTE));
                }

                if (i % NUMBER_OF_DIRECTORY == 3) {
                    System.out.println(EnumSet.of(PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE));

                    Files.setPosixFilePermissions(Path.of(directoryName), EnumSet.of(PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE));
                }

                if (i % NUMBER_OF_DIRECTORY == 4) {
                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE,
                            PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE,
                            PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE));

                    Files.setPosixFilePermissions(Path.of(directoryName), EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE,
                            PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE,
                            PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE));
                }

                if (i % NUMBER_OF_DIRECTORY == 0) {
                    System.out.println(EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE));

                    Files.setPosixFilePermissions(Path.of(directoryName),
                            EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE,
                                    PosixFilePermission.OWNER_EXECUTE));
                }
            }
        } catch (Exception exception) {
            System.out.println("Cannot create directory name '" + currentDirectoryName + "' because " +
                    exception.getMessage());
        }
    }
    
    public static void addDirectoriesDifferentAccessInSystem() {
        try {
            for (int i = 1; i < directoryNames.size(); i++) {
                String directoryName = String.valueOf(directoryNames.get(i));

                if (i % NUMBER_OF_DIRECTORY == 1) {
                    Files.setPosixFilePermissions(Path.of(directoryName), EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE));
                }

                if (i % NUMBER_OF_DIRECTORY == 2) {
                    Files.setPosixFilePermissions(Path.of(directoryName),
                        EnumSet.of(PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE,
                                PosixFilePermission.GROUP_EXECUTE));
                }

                if (i % NUMBER_OF_DIRECTORY == 3) {
                    Files.setPosixFilePermissions(Path.of(directoryName), EnumSet.of(PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE));
                }

                if (i % NUMBER_OF_DIRECTORY == 4) {
                    Files.setPosixFilePermissions(Path.of(directoryName), EnumSet.of(PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE,
                            PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE,
                            PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.OTHERS_READ,
                            PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE));
                }

                if (i % NUMBER_OF_DIRECTORY == 0) {
                    Files.setPosixFilePermissions(Path.of(directoryName),
                        EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE,
                                PosixFilePermission.OWNER_EXECUTE));
                }
            }
        } catch (Exception exception) {
            System.out.println("Failed because " + exception.getMessage());
        }
    }
}