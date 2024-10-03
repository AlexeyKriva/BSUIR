//sudo dscl . -delete /Groups/
//dscl . -list /Groups
//dscl . -read /Groups/
//dscl . -read /Groups/admin
//dscacheutil -q group -a name admin
//sudo dscl . -delete /Groups/admin GroupMembership iit21
//chmod +x /Users/aliaksei/Desktop/MyOwn/lab1-part1/pzs/pzs11/file11.sh

public class MySystemManager {

    public static void main(String[] args) throws InterruptedException {
        launchMySystemManager();
    }

    private static void launchMySystemManager() throws InterruptedException {
        //Создание групп пользователей
        MyUserGroupManager.createUserGroupsNames();
        MyUserGroupManager.createUserGroupsInSystem();

        printDivider();

        //Создание пользователей
        MyUserManager.createUserNames();
        MyUserManager.createUsersInSystem();

        printDivider();

        //Создание директорий
        MyDirectoryManager.createDirectoryNames();
        MyDirectoryManager.createDirectoriesInSystem();

        printDivider();

        Thread.sleep(3000);

        //Замена пользователя
        MyUserManager.changeUser();

        printDivider();

        //Создание файлов
        MyFileManager.createFileNames(MyDirectoryManager.directoryNames);
        MyFileManager.createFilesWithDifferentAccessInSystem();

        printDivider();

//        System.out.println("Start testing...\n");
//
//        printDivider();
//
//        System.out.println("Task 14:");
//
//        MyTests.checkUserAccessToFile(MyUserManager.userNames, MyFileManager.fileNames);
//
//        printDivider();
//
//        System.out.println("Task 16:");
//
//        MyTests.checkUserAccessToDirectories(MyUserManager.userNames, MyDirectoryManager.directoryNames);
    }

    private static void printDivider() {
        System.out.println("\n===========================================================================\n");
    }
}