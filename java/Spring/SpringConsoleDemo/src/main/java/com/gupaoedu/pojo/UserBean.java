package com.gupaoedu.pojo;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class UserBean {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

    private Cat cat;

    private String[] favourites;

    private List<Cat> cats;

    private Map<String, Object> map;

    private Properties props;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public String[] getFavourites() {
        return favourites;
    }

    public void setFavourites(String[] favourites) {
        this.favourites = favourites;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public void say(){
        System.out.println("say......");
    }

    /**
     * Need to have a no parameter constructor(or no constructor at all) to let container load this bean.
     */
    public UserBean(){
        System.out.println("UserBean default constructor executed...");
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }

    /**
     * provide constructor for properties for constructor injection
     * @param id
     * @param userName
     */
    public UserBean(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
