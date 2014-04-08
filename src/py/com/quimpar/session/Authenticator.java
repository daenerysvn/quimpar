package py.com.quimpar.session;

import java.security.Principal;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;



@Name("authenticator")
@Scope(ScopeType.SESSION)
public class Authenticator {
  
  @Logger
  private Log log;
  @In
  Identity identity;
  @In
  Credentials credentials;
//  @In("#{boundaryHelper}")
//  BoundaryHelper bhelper;

  public void checkLogin() {
    // user may already be logged in - check
    final boolean isLoggedIn = identity.isLoggedIn();
    
    if (isLoggedIn) {
      return;
    }
    authenticate();
  }
  
  public boolean authenticate() {
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext ctx = facesContext.getExternalContext();
    
    Principal rawPrincipal = ctx.getUserPrincipal();
    String username = rawPrincipal.getName();
    
    try {
      credentials.setUsername(username);
      credentials.setPassword(username);
//      bhelper.setUsername(username);
      identity.acceptExternallyAuthenticatedPrincipal(ctx.getUserPrincipal());
      identity.authenticate();
      
      /*
       * CARGAR ROLES
       */
      /*EscilaWrapper wrapper = WrapperBuilder.build("saru");
      DirectoryUserDTO user = null;
      wrapper.authorize(this.credentials.getUsername());
      user = wrapper.getUserData();
      List<PermissionDTO> permissions = wrapper.permissions(username);
      
      if(permissions == null || permissions.isEmpty())
    	  throw new Exception("No tiene permisos para utilizar este recurso");
      
      for (PermissionDTO p : permissions) {
        identity.addRole(p.getPropertyValue());
      }*/
      
      return true;
    } catch (Exception e) {
      this.log.error("authenticating Error {0}", e.getMessage());
      FacesMessages.instance().add(Severity.ERROR, e.getMessage());
      return false;
    }
    
  }
  
  public void logout() {
    Identity identity = Identity.instance();
    identity.isLoggedIn(false);
    identity.logout();
    // Session.instance().invalidate();
    
    /*FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext ctx = facesContext.getExternalContext();
    
    try {
      String url = System.getProperty("cas.server.url") + "/cas/logout";
      ctx.redirect(ctx.encodeResourceURL(url));
    } catch (Exception e) {
     
    }*/
    FacesContext.getCurrentInstance().responseComplete();
  }
  
}
