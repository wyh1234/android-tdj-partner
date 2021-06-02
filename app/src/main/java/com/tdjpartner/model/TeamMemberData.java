package com.tdjpartner.model;

import java.util.List;

public class TeamMemberData {

    public String title;
    public List<Data> teamMembers;

    public static class Data {
        public int userId; //2023810
        public int parentId; //null,
        public int type; //1,
        public int grade; //1,
        public int size; //5,
        public boolean other; //false,
        public String abbreviation; //"M2",
        public String roleName; //"市场经理",
        public String nickName; //"ltM2",
        public String phone; //"17799770910",
        public String pathIds; //"2023806"

        @Override
        public String toString() {
            return "Data{" +
                    "userId=" + userId +
                    ", parentId=" + parentId +
                    ", type=" + type +
                    ", grade=" + grade +
                    ", size=" + size +
                    ", other=" + other +
                    ", abbreviation='" + abbreviation + '\'' +
                    ", roleName='" + roleName + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", phone='" + phone + '\'' +
                    ", pathIds='" + pathIds + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TeamMemberData{" +
                "title='" + title + '\'' +
                ", teamMembers=" + teamMembers +
                '}';
    }
}
