
 
import cn.drizzle.entity.Sysuser;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

 
@ManagedBean
public class RequestContextView {
   
    private Sysuser user;
     
    @PostConstruct
    public void init() {
        user = new Sysuser();
         
        if(!FacesContext.getCurrentInstance().isPostback()) {
            RequestContext.getCurrentInstance().execute("alert('This onload script is added from backing bean.')");
        }
    }
 
    public Sysuser getUser() {
        return user;
    }
 
    public void setUser(Sysuser user) {
        this.user = user;
    }
 
    public void save() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("saved", true);    //basic parameter  //pojo as json
        context.addCallbackParam("user", user);   
         
        //execute javascript oncomplete
        context.execute("PrimeFaces.info('Hello from the Backing Bean');");
         
        //update panel
        context.update("form:panel");
         
        //scroll to panel
        context.scrollTo("form:panel");
         
        //add facesmessage
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Success"));
    }
}