import java.util.ArrayList;
import java.util.List;

public class MyUserGroupManager {
    public static final String GROUP_IIT = "group_iit";
    public static final int NUMBER_OF_USER_GROUPS = 2;
    public static final String CREATE_USER_GROUP_COMMAND = "dscl . -create /Groups/%s gid %d";
    public static List<String> userGroupNames;
    public static int GIT_VALUE = 1000;

    static {
        userGroupNames = new ArrayList<>();
    }

    public static void createUserGroupsNames() {
        for (int i = 1; i <= NUMBER_OF_USER_GROUPS; i++) {
            userGroupNames.add(GROUP_IIT + i);
        }
    }

    public static void createUserGroupsInSystem() {
        System.out.println("Starting to create user groups...");

        for (String userGroupName: userGroupNames) {
            System.out.println("Process to create user group with name " + userGroupName + " and with GID " +
                    GIT_VALUE);

            MyCommandRunner.runExecCommand(String.format(CREATE_USER_GROUP_COMMAND, userGroupName, GIT_VALUE++));
        }
    }
}