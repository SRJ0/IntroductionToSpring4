package sample;

public class Member {

    private int id;
    private String loginId;
    private String password;

    public Member(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPW, String newPW) {
        if (!password.equals(oldPW)) {
            throw new IdPasswordNotMatchingException("check your account info!");
        }
    }

}
