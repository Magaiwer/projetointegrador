package projetointegrador.security;

import javafx.scene.Parent;
import projetointegrador.model.Permission;
import projetointegrador.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;

public class Security {

    private static boolean userHasRole(String role) {
        List<Permission> permissionList = new ArrayList<>();


        UsuarioService.usuarioLogado.getGrupos().forEach(grupo -> permissionList.addAll(grupo.getPermissions()));

        return permissionList.stream()
                .map(Permission::getRole).anyMatch(r -> r.equals(role));
    }

    public static void setPermissionForm(Parent node) {
        if (UsuarioService.usuarioLogado != null) {
            node.lookupAll("JFXButton").forEach(btn -> {
               // btn.setDisable(!userHasRole(btn.getId()));
            });
        }
    }
}
