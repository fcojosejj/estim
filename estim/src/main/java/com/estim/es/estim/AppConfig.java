package com.estim.es.estim;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;

@EmbeddedIdentityStoreDefinition({
        //@Credentials(callerName = "admin", password = "admin", groups = {"ADMINISTRADORES"}),
        //@Credentials(callerName = "cliente", password = "cliente", groups = {"USUARIOS"})
})
@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/login.xhtml?error",
                useForwardToLogin = false
        )
)
@ApplicationScoped
@FacesConfig
public class AppConfig {
}
