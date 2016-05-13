package io.github.notze.lffelma.persistent;

/**
 * Created by Notze on 08.05.2016.
 */
public class Ingredient {

    private long id;
    private String name;
    private String teaspoon;
    private String tablespoon;

    public Ingredient(String name, String teaspoon, String tablespoon) {
        this.name = name;
        this.teaspoon = teaspoon;
        this.tablespoon = tablespoon;
    }

    @Override
    public String toString(){
        String out = name + "; " + teaspoon + "; " + tablespoon;
        return out;
    }

    // getter and setter
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getname() {return name;}
    public void setname(String name) {this.name = name;}
    public String getteaspoon() {return teaspoon;}
    public void setteaspoon(String teaspoon) {this.teaspoon = teaspoon;}
    public String gettablespoon() {return tablespoon;}
    public void settablespoon(String tablespoon) {this.tablespoon = tablespoon;}

}
