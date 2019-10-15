package projetointegrador.security;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import projetointegrador.model.Permission;
import projetointegrador.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class SecurityInterceptor  {

/*    @Around("@annotation(projetointegrador.annotation.Restriction)")
    public Object security(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String role = signature.getMethod().getAnnotation(Restriction.class).value();
       // String role = restriction.value();

        System.out.println(role);

        boolean hasRole = false;
*//*
        System.out.println(context.toString());
        System.out.println(context.getMethod().getAnnotation(Restriction.class).value());


        String role = context.getMethod().getAnnotation(Restriction.class).value();*//*





        return joinPoint.proceed(); //userHasRole(role);

    }*/

    public boolean userHasRole(String role) {
        List<Permission> permissionList = new ArrayList<>();

        UserService.userLogged.getGroups().forEach(grupo -> permissionList.addAll(grupo.getPermissions()));

        return permissionList.stream()
                .map(Permission::getRole).anyMatch(r -> r.equals(role));
    }

}
