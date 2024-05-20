package DomainModel;

public class User {
    private String mail;
    private String name;
    private String surname;
    private String pwd;
    private String telephone;

    public User(String mail, String telephone,String name,String surname,String pwd){
        this.mail=mail;
        this.name=name;
        this.surname=surname;
        this.pwd=pwd;
        this.telephone=telephone;
    }

    public String getMail(){
        return mail;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getPwd(){
        return pwd;
    }
    public String getTelephone(){
        return telephone;
    }

    public String getInfo(){
        String info=String.format("Mail: %s   Nome: %s   Cognome: %s Password: %s Telephone: %s ",
        mail, name, surname, pwd, telephone);
        return info;
    }
}
