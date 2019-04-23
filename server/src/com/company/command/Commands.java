package com.company.command;

public class Commands {

    /**
     * {request name, command class name}
     */
    public static String[][] commands = {
            {"init", "InitCommand"},
            {"bad_request", "BadRequestCommand"},
            {"session_expired", "SessionExpiredCommand"}
    };

    /**
     * @param index request name
     * @return command class name or null if can't find values with given index.
     */
    public static String get (String index) {
        for (String[] i : commands) {
            if (i[0].equals(index))
                return i[1];
        }

        return null;
    }

}
