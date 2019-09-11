package projetointegrador.Security;

import projetointegrador.annotation.Restriction;
import projetointegrador.model.Permission;
import projetointegrador.service.UsuarioService;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.ArrayList;
import java.util.List;


@Interceptor
@Restriction
public class SecurityInterceptor {

    @AroundInvoke
    public boolean security(InvocationContext context) {

        boolean hasRole = false;
        System.out.println(context.toString());
        System.out.println(context.getMethod().getAnnotation(Restriction.class).value());
        System.out.println(context.getTarget().toString());

        String role = context.getMethod().getAnnotation(Restriction.class).value();


        return userHasRole(role);


    }

    private boolean userHasRole(String role) {
        List<Permission> permissionList = new ArrayList<>();

        UsuarioService.usuarioLogado.getGrupos().forEach(grupo -> permissionList.addAll(grupo.getPermissions()));

        return permissionList.stream()
                .map(Permission::getRole).anyMatch(r -> r.equals(role));
    }
}
