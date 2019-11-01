package com.example.mestudent;

import android.provider.BaseColumns;

public class schema {

    private schema () {}

    public static class dtbaseCONSTS implements BaseColumns {

        public static final int DATABASE_VERSION = 3;
        public static final String DATABASE_NAME = "MeStudent.db";

        public static final String LOGIN_TABLE_NAME = "login";
        public static final String LOGIN_COLUMN_NAME = "login";
        public static final String PASSWORD_COLUMN_NAME = "password";

        public static final String DISCIPLINE_TABLE_NAME = "discipline";
        public static final String DISCIPLINE_COLUMN_NAME = "discipline";
        public static final String TEACHER_COLUMN_NAME = "teacher";
        public static final String CLASSROOM_COLUMN_NAME = "classroom";
        public static final String DATE_COLUMN_NAME = "date";

        public static final String GRADE_TABLE_NAME = "grades";
        public static final String GRADE_DISCIPLINE_COLUMN_NAME = "discipline";
        public static final String GRADE_COLUMN_NAME = "grade";
    }
}
