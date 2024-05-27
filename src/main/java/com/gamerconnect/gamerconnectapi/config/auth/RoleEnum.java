package com.gamerconnect.gamerconnectapi.config.auth;

public enum RoleEnum {
    ROLE_GUEST("GUEST"),
    ROLE_USER("USER"),
    ROLE_TESTER("TESTER"),
    ROLE_ADMIN("ADMIN");

    private final String permissao;

    RoleEnum(String permissao) {
        this.permissao = permissao;
    }

    public String getPermissao() {
        return permissao;
    }
}
