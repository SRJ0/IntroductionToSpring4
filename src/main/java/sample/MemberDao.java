package sample;

import java.util.*;
public class MemberDao {
    private static int nextId = 0;
    private Map<String, Member> map = new HashMap<>();

    public Member selectByLoginId(String loginId) {
        return map.get(loginId);
    }

    public void insert(Member member) {
        member.setId(++nextId);
        map.put(member.getLoginId(), member);
    }

    public void update(Member member) {
        map.put(member.getLoginId(), member);
    }
}
