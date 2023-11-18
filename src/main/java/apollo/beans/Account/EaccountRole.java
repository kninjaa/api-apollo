package apollo.beans.Account;

public enum EaccountRole {
    ADMIN("admin"),
    ACCOUNT("account");

    private String role;

    EaccountRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}