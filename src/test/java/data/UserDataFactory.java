package data;

public final class UserDataFactory {
  private UserDataFactory() {
  }

  public static UserData userAdmin() {
    return new UserData(
        "Usuario Admin",
        "usuario" + "@teste.com",
        "Senha@123",
        "true");
  }

  public static UserData validCommonUser() {
    return new UserData(
        "Usuario Comum",
        "comum" + System.currentTimeMillis() + "@teste.com",
        "Senha@123",
        "false");
  }
}