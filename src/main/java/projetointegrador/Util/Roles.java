package projetointegrador.Util;

import javax.swing.text.html.parser.Entity;

public interface Roles {

    enum FORM implements Roles {

        ROLE_VIEW_FORMS("ROLE_VIEW_FORMS");

        private String role;

        FORM(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

    enum USER implements Roles {

        ROLE_VIEW_USERS,
        ROLE_VIEW_FORMS,
        ROLE_VIEW_GROUPS,
        ROLE_VIEW_;

    }

    enum AUDIT implements Roles {

        ROLE_VIEW_USERS,
        ROLE_VIEW_FORMS,
        ROLE_VIEW_GROUPS,
        ROLE_VIEW_;

    }

    enum GROUP implements Roles {

        ROLE_VIEW_USERS,
        ROLE_VIEW_FORMS,
        ROLE_VIEW_GROUPS,
        ROLE_VIEW_;

    }

    enum MATERIAL {

        ROLE_VIEW_USERS,
        ROLE_VIEW_FORMS,
        ROLE_VIEW_GROUPS,
        ROLE_VIEW_;


    }

}
