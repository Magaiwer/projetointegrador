package projetointegrador.Util;

import java.util.ResourceBundle;

public enum EFxmlView {

    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/login.fxml";
        }
    },
    HOME {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("home.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/home.fxml";
        }
    },
    USER {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("user.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/user-form.fxml";
        }
    },
    USER_TABLE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("user.table");
        }

        @Override
        public String getFxmlFile() {
            return "/views/table-user.fxml";
        }
    },
    GROUP_TABLE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("group.table");
        }

        @Override
        public String getFxmlFile() {
            return "/views/table-group.fxml";
        }
    },
    GROUP {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("group.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/group-form.fxml";
        }
    },
    FORM {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("form.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/forms-form.fxml";
        }
    },
    FORM_TABLE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("form.table");
        }

        @Override
        public String getFxmlFile() {
            return "/views/table-form.fxml";
        }
    },
    AUDITORIA_LIST{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("audit.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/audit-list.fxml";
        }
    },
    MATERIAL_LIST{
        @Override
        public  String getTitle() { return getStringFromResourceBundle("material.title"); }

        @Override
        public String getFxmlFile() { return  "/views/table-material.fxml"; }
    },
    MATERIAL_ABSORTANCIA{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("material-absortancia.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/material-absortancia-form.fxml";
        }
    },
    MATERIAL_ABSORTANCIA_TABLE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("material-absortancia.table");
        }

        @Override
        public String getFxmlFile() {
            return "/views/table-material-absortancia.fxml";
        }
    };

    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
