/**
 * Created by Lukasz Kolacz on 10.04.2017.
 */
public class User {

    private String name;
    private String lastName;
    private String telephoneNumber;

    public User(String name, String lastName, String telephoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
