package edu.pkch.clazz;

public class Clazz {
    public static class StaticMemberClazz {
        public static final String NAME = "static member class";
    }

    class MemberClazz {
        public String upper() {
            Class<? extends Clazz> clazz = Clazz.this.getClass();
            return clazz.getName();
        }
    }
}