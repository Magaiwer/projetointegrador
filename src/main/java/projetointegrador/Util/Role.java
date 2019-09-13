package projetointegrador.Util;

public enum Role {
    ROLE_EDIT_USER("ROLE_EDIT_USER"),
    ROLE_DELETE_USER("ROLE_DELETE_USER"),
    ROLE_INSERT_USER("ROLE_INSERT_USER");


    private String description;
    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
