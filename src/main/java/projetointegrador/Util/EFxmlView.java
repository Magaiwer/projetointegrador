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
    AUDITORIA_LIST {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("audit.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/audit-list.fxml";
        }
    },
    MATERIAL {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("material.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/material-form.fxml";
        }
    },
    MATERIAL_TABLE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("material.table");
        }

        @Override
        public String getFxmlFile() {
            return "/views/table-material.fxml";
        }
    },
    MATERIAL_ABSORTANCIA {
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
    },
    PERMISSIONS {
        @Override
        public String getTitle() {
            return null;
        }

        @Override
        public String getFxmlFile() {
            return null;
        }
    },
    PERMISSIONS_TABLE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("permissions.table");
        }

        @Override
        public String getFxmlFile() {
            return "/views/table-group-permissions.fxml";
        }
    },
    PROJECT {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("project.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/moc.fxml";
        }
    },
    PROJECT_TABLE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("project.table");
        }

        @Override
        public String getFxmlFile() {
            return "/views/table-project.fxml";
        }
    },
    DASHBOARDS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("dashboards.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/dashboard.fxml";
        }
    },
    PROJECT_DETAIL {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("project.detail");
        }

        @Override
        public String getFxmlFile() {
            return "/views/detail-project.fxml";
        }
    },
    EMAIL {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("email.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/email.fxml";
        }
    },
    FILE_TRANSFER {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("file.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/file-transfer-form.fxml";
        }
    };

    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
