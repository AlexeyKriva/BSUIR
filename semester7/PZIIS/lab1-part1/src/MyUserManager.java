import java.util.ArrayList;
import java.util.List;

public class MyUserManager {
    public static final String IIT = "iit";
    public static final String CREATE_USER_COMMAND = "dscl . -create /Users/%s";
    public static final String CREATE_USER_SHELL_COMMAND = "dscl . -create /Users/%s UserShell /bin/bash";
    public static final String CREATE_USER_REAL_NAME_COMMAND = "dscl . -create /Users/%s RealName i_am_%s";
    public static final String CREATE_USER_UNIQUE_ID_COMMAND = "dscl . -create /Users/%s UniqueID %d";
    public static final String CREATE_USER__PRIMARY_GROUP_ID_COMMAND = "dscl . -create /Users/%s PrimaryGroupID %d";
    public static final String CREATE_USER_HOME_DIRECTORY_COMMAND = "dscl . -create /Users/%s NFSHomeDirectory /Users/%s";
    public static final String CREATE_USER_PASSWORD_COMMAND = "dscl . -create /Users/%s password %s";
    public static final String GIVE_USER_ADMIN_PRIVILEGES_COMMAND = "dscl . -append /Groups/admin" +
            " GroupMembership %s";
    public static final String CHANGE_USER_COMMAND = "su - %s";
    public static final int LAST_USER_INDEX = 3;
    public static int uniqueID = 533;
    public static int GROUP_ID = 1000;
    public static final String PASSWORD = "98479847";

    public static List<String> userNames;

    static {
        userNames = new ArrayList<>();
    }

    public static void createUserNames() {
        for (int i = 1; i < LAST_USER_INDEX; i++) {
            for (int j = 1; j < LAST_USER_INDEX; j++) {
                userNames.add(IIT + i + j);
            }
        }

        userNames.add(IIT + LAST_USER_INDEX);
    }

    public static void createUsersInSystem() {
        System.out.println("Starting to create users...");

        int groupCounter = 0;

        for (String userName: userNames) {
            System.out.print("Process to create user with name " + userName);

            MyCommandRunner.runExecCommand(String.format(CREATE_USER_COMMAND, userName));

            MyCommandRunner.runExecCommand(String.format(CREATE_USER_SHELL_COMMAND, userName));

            MyCommandRunner.runExecCommand(String.format(CREATE_USER_REAL_NAME_COMMAND, userName, "i_am_" + userName));

            System.out.print(" and with id " + uniqueID);

            MyCommandRunner.runExecCommand(String.format(CREATE_USER_UNIQUE_ID_COMMAND, userName, uniqueID++));

            if (!userName.equals("iit3")) {
                System.out.print(" and add to user group with id " + GROUP_ID);

                MyCommandRunner.runExecCommand(String.format(CREATE_USER__PRIMARY_GROUP_ID_COMMAND, userName, GROUP_ID));
            }

            MyCommandRunner.runExecCommand(String.format(CREATE_USER_HOME_DIRECTORY_COMMAND, userName, userName));

            System.out.print(" and with password " + PASSWORD);

            MyCommandRunner.runExecCommand(String.format(CREATE_USER_PASSWORD_COMMAND, userName, PASSWORD));

            if (userName.equals("iit21")) {
                System.out.print(" and add admin privileges");

                MyCommandRunner.runExecCommand(String.format(GIVE_USER_ADMIN_PRIVILEGES_COMMAND,
                        userName));
            }

            System.out.println();

            groupCounter++;

            if (groupCounter == 2) {
                GROUP_ID++;
            }
        }
    }

    public static void changeUser() {
        System.out.println("Change current user to iit11");
        MyCommandRunner.runExecCommand(String.format(CHANGE_USER_COMMAND, "iit11"));
    }
}