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
    };


    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
