package pers.qingxuan.database0;

/**
 * <p>
 *
 * @author : QingXuan
 * @since Created in 下午10:30 2020/11/17
 */
public class User {
    /**
     * ID
     */
    private Integer uid;
    /**
     * 名字
     */
    private String username;
    /**
     * 年龄
     */
    private Integer age;

    public User() {
    }

    public User(Integer uid, String username, Integer age) {
        this.uid = uid;
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
