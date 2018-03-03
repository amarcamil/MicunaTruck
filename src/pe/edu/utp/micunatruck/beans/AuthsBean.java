package pe.edu.utp.micunatruck.beans;

import jdk.nashorn.internal.objects.annotations.Constructor;
import pe.edu.utp.micunatruck.models.User;
import pe.edu.utp.micunatruck.models.UserType;
import pe.edu.utp.micunatruck.services.MicunaTruckService;
import pe.edu.utp.micunatruck.utils.SessionUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

@Named
//@ManagedBean
@SessionScoped
public class AuthsBean implements Serializable {
    private MicunaTruckService micunaTruckService;
    private User user;
    private UserType userType;
    private Connection connection;
    private String msjError = "";
    private String email;
    private String password;

    @Constructor
    protected void authInit(){
        micunaTruckService = new MicunaTruckService();
        micunaTruckService.setConnection(getConnection());
    }

    @PostConstruct
    protected void init() {
        micunaTruckService = new MicunaTruckService();
        micunaTruckService.setConnection(getConnection());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setMsjError(String msj){
        this.msjError = msj;
    }

    public String getMsjError(){
        return  this.msjError;
    }

    public String signIn(){
        this.setUser(new User());
        return "success";
    }

    public String authentication() {
        try{
            this.setUser(micunaTruckService.findUserByEmailAndPassword(this.getEmail(), this.getPassword()));

            if(getUser() == null) {
                this.setMsjError("Incorrect email or password.");
                return "error";
            }
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user.getName());
            session.setAttribute("user", user);
            return "success";
        }
        catch(Exception e){
            e.printStackTrace();
            this.setMsjError("An error occurred. Please, contact the administrator.");
            return  "error";
        }
    }

    private Connection getConnection() {
        try{
            if(connection == null) {
                try {
                    InitialContext ctx = new InitialContext();
                    DataSource dataSource = (DataSource)ctx.lookup("jdbc/MySQLDataSourceMicunaTruck");
                    connection = dataSource.getConnection();

                } catch (NamingException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "success";
    }
}